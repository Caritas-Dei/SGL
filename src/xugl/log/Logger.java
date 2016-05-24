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
 * Created file on 5/17/16 at 12:26 PM.
 *
 * This file is part of xugl
 */
package xugl.log;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * Provisional Logging.
 * <p>
 * TODO - fix concurrency
 *
 * @author link
 */
@FunctionalInterface
public interface Logger {

	void log(Log.Entry entry);

	default String getName() {
		return toString();
	}

	default void log(Log.Entry... entries) {
		log(Arrays.asList(entries));
	}

	default void log(List<Log.Entry> list) {
		list.forEach(this::log);
	}

	default void log(String entry) {
		log(Log.Level.INFO, entry);
	}

	default void log(Log.Level level, String entry) {
		log(level, System.out, entry);
	}

	default void log(Log.Level level, PrintStream printer, String entry) {
		log(new Log.Entry(level, printer, entry));
	}

	default void log(Throwable t, String entry) {
		log(t, Log.Level.ERROR, entry);
	}

	default void log(Throwable t, Log.Level level, String entry) {
		log(t, level, System.err, entry);
	}

	default void log(Throwable t, Log.Level level, PrintStream printer, String entry) {
		log(new Log.Entry(t, level, printer, entry));
	}


}
