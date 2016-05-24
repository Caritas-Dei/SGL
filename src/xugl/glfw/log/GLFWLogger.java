/*
 * The MIT License
 *
 * Copyright 2015 link.
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
 */
package xugl.glfw.log;

import org.lwjgl.glfw.GLFWErrorCallback;
import xugl.glfw.error.*;
import xugl.log.Log;
import xugl.log.Logger;

import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.lwjgl.glfw.GLFW.*;

/**
 * FIXME concurrency
 *
 * @author link
 */
public final class GLFWLogger extends GLFWErrorCallback implements Logger, Thread.UncaughtExceptionHandler {

	private final String name;
	private Log log = new Log();
	private PrintStream printStream;

	private volatile boolean running;
	private volatile boolean print;

	private final Thread logger = new Thread(() -> {
		while (running) {
			try {
				synchronized (printStream) {
					printStream.wait();
					if (print)
						printEntry(this, log.getLastEntry());
					print = false;
					printStream.notifyAll();
				}
			} catch (InterruptedException e) {
				// ignore
			}
		}
	});

	{
		logger.setPriority(Thread.MAX_PRIORITY - 3);
		logger.setDaemon(true);
		running = true;
		logger.start();
	}

	public GLFWLogger(String name) {
		this.name = name;
		this.printStream = System.out;
		logger.setName(name);

	}

	public GLFWLogger(String name, PrintStream printStream) {
		this(name);
		this.printStream = printStream;
	}

	private static Throwable glfwFormatError(int error, long description) {
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
		Throwable t;
		logger.printStream.println("[" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + "][" + entry.getLevel().name() + "]" + ((t = entry.getError()) != null ? "[" + t.getClass().getSimpleName() + ":\"" + t.getLocalizedMessage() + "\" " : " ") + entry.getEntry().trim());
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		log(e, e.getLocalizedMessage());
	}

	@Override
	public void invoke(int error, long description) {
		log(glfwFormatError(error, description), "An internal GLFW Error occured");
	}

	public String getName() {
		return name;
	}

	@Override
	public void log(Log.Entry entry) {
		synchronized (printStream) {
			log.addEntry(entry);
			print = true;
			printStream.notify();
		}
	}

}
