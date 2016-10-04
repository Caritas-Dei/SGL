/*
 * The MIT License
 *
 * Copyright 2016 link.
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
 * Created file on 9/23/16 at 7:17 PM.
 *
 * This file is part of XUGL
 */
package sgl.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author link
 */
public enum Shutdown {
	;

	private static final List<Runnable> shutdownHooks = new ArrayList<>();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			shutdownHooks.forEach(Runnable::run);
		}, "shutdown.hooks"));
	}

	/**
	 * Adds a shutdown hook to the list of hooks.
	 * <p>
	 * This class provides a list of shutdown hooks so that only a single thread
	 * is needed to run shutdown hooks instead of creating a thread for each
	 * hook.
	 * </p>
	 *
	 * @param hook
	 */
	public static void addHook(Runnable hook) {
		shutdownHooks.add(hook);
	}

	/**
	 * Equivalent to {@code Runtime.getRuntime().addShutdownHook(thread)}
	 *
	 * @param thread
	 */
	public static void addHook(Thread thread) {
		Runtime.getRuntime().addShutdownHook(thread);
	}

	/**
	 * Equivalent to {@code Runtime.getRuntime().exit(status)}
	 *
	 * @param status the exit code
	 */
	public static void now(int status) {
		Runtime.getRuntime().exit(status);
	}
}
