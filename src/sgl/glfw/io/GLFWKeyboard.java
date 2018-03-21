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
package sgl.glfw.io;

import org.lwjgl.glfw.GLFWKeyCallback;
import sgl.display.Display;
import sgl.glfw.display.GLFWDisplay;
import sgl.glfw.log.GLFWLogger;
import sgl.io.KeyMap;
import sgl.io.KeyMaps;
import sgl.io.Keyboard;
import sgl.util.log.Logger;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * A Keyboard.
 * <p>
 * <p>
 * A Keyboard contains an array of states that represent key states. There are
 * three possible states for a key:</p>
 * <pre>
 * {@code GLFW_PRESS}, {@code GLFW_RELEASE}, and {@code GLFW_REPEAT}.
 * </pre>
 *
 * @author link
 */
public final class GLFWKeyboard extends GLFWKeyCallback implements Keyboard {

	private static final KeyMap GLFW_KEYMAP = KeyMaps.getMap(GLFWKeyMap.GLFW_GENERIC);

	private final Logger logger;
	// the display
	private final Display display;
	// the display handle
	private final long displayHandle;
	private final Object keyLock = new Object();
	// states of all the keys. short because value never goes
	// higher than 348 (GLFW_KEY_LAST) or 402 (KeyEvent.KEY_LAST)
	private volatile int[] keyState;
	/**
	 * previous and current key
	 */
	private volatile int previous, current;
	/**
	 * Last known key states
	 */
	private volatile int pstate, cstate;

	/**
	 * The modifier keys pressed down
	 */
	private int[] modifiers = new int[3];

	private final List<Runnable> invoke = new ArrayList<>();

	/**
	 * Creates a new Keyboard with the specified display as its parent.
	 *
	 * @param display
	 * 		the parent display that sends key events
	 */
	public GLFWKeyboard(Display display) {
		logger = new GLFWLogger("Keyboard");
		this.display = display;
		// if the display is a GLFWDisplay, get it's handle, otherwise we use the hashCode of the type as handle.
		displayHandle = display instanceof GLFWDisplay ? ((GLFWDisplay) display).ptr() : NULL;
		keyState = new int[display instanceof GLFWDisplay ? GLFW_KEY_LAST : KeyEvent.KEY_LAST];
	}

	@Override
	public void invoke(long display, int key, int scancode, int action,
	                   int mods) {
		synchronized (keyLock) {
			keyState[key] = (byte) action;
			previous = current;
			current = key;

			logger.log("Key Pressed: " + current);
			logger.log("\tPrevious State: " + keyState[previous]);
			logger.log("\tCurrent State: " + keyState[current]);
		}
	}

	/**
	 * <p>
	 * Checks whether the given key is pressed down.
	 * </p>
	 *
	 * @param glfwKey
	 * 		the key to check for
	 *
	 * @return true if the key is pressed down or false if not
	 */
	public boolean isKeyDown(int glfwKey) {
		synchronized (keyLock) {
			return glfwKey == current && keyState[current] == GLFW_PRESS;
		}
	}

	/**
	 * <p>
	 * Checks whether the given key is up.
	 * </p>
	 *
	 * @param glfwKey
	 * 		the key to check for
	 *
	 * @return true if the key is not up or false if it is down
	 */
	public boolean isKeyUp(int glfwKey) {
		synchronized (keyLock) {
			return glfwKey == current && keyState[current] == GLFW_RELEASE;
		}
	}

	/**
	 * <p>
	 * Checks whether the given key was just pressed and released
	 * </p>
	 *
	 * @param glfwKey
	 * 		the key to check
	 *
	 * @return true if the key was pressed, false otherwise
	 */
	public boolean isKeyTyped(int glfwKey) {
		synchronized (keyLock) {
			return previous == glfwKey && current == glfwKey && keyState[glfwKey] == GLFW_RELEASE;
		}
	}

	@Override
	public int getKeyCount() {
		return 0;
	}

	public boolean isKeyRepeating(int glfwKey) {
		synchronized (keyLock) {
			return keyState[glfwKey] == GLFW_REPEAT;
		}
	}

	/**
	 * <p>
	 * Gets the actual state for a given key.
	 * </p>
	 *
	 * @param glfwKey
	 * 		the key to get the state for
	 *
	 * @return the state of the given key
	 */
	public int getKeyState(int glfwKey) {
		synchronized (keyLock) {
			return keyState[glfwKey];
		}
	}

	@Override
	public KeyMap getKeyMap() {
		return GLFW_KEYMAP;
	}


	/**
	 * Gets the previously invoked key
	 *
	 * @return the previous key
	 */
	public int getPreviousKey() {
		synchronized (keyLock) {
			return previous;
		}
	}

	/**
	 * Gets the last key that was invoked invoked.
	 *
	 * @return the last key
	 */
	public int getLastKey() {
		synchronized (keyLock) {
			return current;
		}
	}

}
