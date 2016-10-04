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
 * Created file on 5/10/16 at 5:06 PM.
 *
 * This file is part of xGUI
 */
package sgl.io;

import java.util.HashMap;
import java.util.Map;

/**
 * @author link
 */
public enum KeyMaps {

	;
	private static final Map<String, KeyMap> KEY_MAPS = new HashMap<>(1, 0.01f);
	private final String name;

	KeyMaps(String name) {
		this.name = name;
	}

	public static void addMap(String name, KeyMap keyMap) {
		KEY_MAPS.putIfAbsent(name, keyMap);
	}

	public static KeyMap getMap(String name) {
		return KEY_MAPS.get(name);
	}

}
