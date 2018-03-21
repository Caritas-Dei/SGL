/*
 * The MIT License
 *
 * Copyright ${year} Andrew Porter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * Created file on ${date} at ${time}.
 *
 * This file is part of SGL
 */
package sgl.glfw.log;

import org.lwjgl.glfw.GLFWErrorCallback;
import sgl.glfw.error.*;
import sgl.util.log.Log;
import sgl.util.log.Logger;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Queue;

import static org.lwjgl.glfw.GLFW.*;

/**
 * FIXME concurrency
 *
 * @author link
 */
public final class GLFWLogger extends GLFWErrorCallback implements Logger, Thread.UncaughtExceptionHandler {

	// locks
	private static final Object PRINT = new Object();

	private final String name;
	private Log log = new Log();

	private final PrintStream ps;

	private volatile boolean running;
	private volatile boolean print;

	private final Thread logger;
	private final Queue<Log.Entry> printQueue = new ArrayDeque<>(3);

	public GLFWLogger(String name) {
		this(name, System.out);
	}

	public GLFWLogger(String name, PrintStream printStream) {
		this.name = name;
		this.ps = printStream == null ? System.out : printStream;

		logger = new Thread(() -> {
			while (running) {
				synchronized (PRINT) {
					while (!print || printQueue.peek() == null) try {
						PRINT.wait();
					} catch (InterruptedException e) {
						// ignore
					}

					synchronized (ps) {
						for (Log.Entry entry = null; (entry = printQueue.poll()) != null; entry = null)
							printEntry(this, entry);
					}
					print = false;

					PRINT.notifyAll();
				}
			}
		});

		logger.setName(name);
		logger.setPriority(Thread.MAX_PRIORITY - 3);
		logger.setDaemon(true);
		running = true;
		logger.start();
	}

	private static Throwable formatGLFWError(int error, long description) {
		switch (error) {
			case GLFW_NO_WINDOW_CONTEXT:
				return new GLFWNoWindowContextException(description);
			case GLFW_PLATFORM_ERROR:
				return new GLFWPlatformError(description);
			case GLFW_FORMAT_UNAVAILABLE:
				return new GLFWFormatUnavailableException(description);
			case GLFW_API_UNAVAILABLE:
				return new GLFWAPIUnavailableError(description);
			case GLFW_VERSION_UNAVAILABLE:
				return new GLFWVersionUnavailableError(description);
			case GLFW_INVALID_VALUE:
				return new GLFWInvalidValueException(description);
			case GLFW_OUT_OF_MEMORY:
				return new GLFWOutOfMemoryError(description);
			case GLFW_NO_CURRENT_CONTEXT:
				return new GLFWNoCurrentContextException(description);
			case GLFW_INVALID_ENUM:
				return new GLFWInvalidEnumError(description);
			case GLFW_NOT_INITIALIZED:
				return new GLFWNotInitializedError(description);
			default:
				return new GLFWError(error, description);
		}
	}

	private static void printEntry(final GLFWLogger logger, final Log.Entry entry) {
		LocalDateTime time = entry.getTimeStamp();
		Throwable t; // lazy init
		logger.ps.println("[" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + "][" + entry.getLevel().name() + "]" + ((t = entry.getError()) != null ? "[" + t.getClass().getSimpleName() + ":\"" + t.getLocalizedMessage() + "\" " : " ") + entry.getEntry().trim());
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		log(e, e.getLocalizedMessage());
	}

	@Override
	public void invoke(int error, long description) {
		log(formatGLFWError(error, description), "An internal GLFW Error occured");
	}

	public String getName() {
		return name;
	}

	@Override
	public void log(Log.Entry entry) {
		log.addEntry(entry);
		printQueue.add(entry);
		if (!print) print = true;
	}

}
