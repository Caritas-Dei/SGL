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
 * Created file on 5/17/16 at 12:24 PM.
 *
 * This file is part of xugl
 */
package xugl.log;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author link
 */
public final class Log {

	private final List<Entry> entries = Collections.synchronizedList(new ArrayList<>());

	public void addEntry(Entry entry) {
		entries.add(entry);
	}

	public Entry getEntry(int entry) {
		synchronized (entries) {
			return entries.get(entry);
		}
	}

	public Entry getFirstEntry() {
		return getEntry(0);
	}

	public Entry getLastEntry() {
		synchronized (entries) {
			return entries.get(entries.size() - 1);
		}
	}

	public List<Entry> getEntries() {
		return Collections.unmodifiableList(entries);
	}

	public enum Level {
		INFO, WARNING, ERROR
	}

	/**
	 * An Entry for a Log
	 */
	public static final class Entry {

		private final Throwable t;
		private final Level level;
		private final PrintStream ps;
		private final LocalDateTime timeStamp;
		private final String entry;

		/**
		 * Creates a new Entry for the Logger
		 *
		 * @param level
		 * 		the level of this entry
		 * @param ps
		 * 		the printstream to write this entry to
		 * @param entry
		 * 		the entry to write
		 */
		public Entry(Level level, PrintStream ps, String entry) {
			this.t = null;
			this.level = level;
			this.ps = ps;
			this.timeStamp = LocalDateTime.now();
			this.entry = entry;
		}

		public Entry(Throwable t, Level level, PrintStream ps, String entry) {
			this.t = t;
			this.level = level;
			this.ps = ps;
			this.timeStamp = LocalDateTime.now();
			this.entry = entry;
		}

		public Throwable getError() {
			return t;
		}

		public Level getLevel() {
			return level;
		}

		public PrintStream getPrintStream() {
			return ps;
		}

		public LocalDateTime getTimeStamp() {
			return timeStamp;
		}

		public String getEntry() {
			return entry;
		}

	}
}
