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
 *
 * Created file on 12/27/15 at 6:28 PM.
 *
 * This file is part of jGUI
 */
package xugl.glfw.ui.display;

import org.lwjgl.opengl.GL;
import xugl.glfw.io.GLFWKeyboard;
import xugl.glfw.log.GLFWLogger;
import xugl.io.Keyboard;
import xugl.log.Logger;
import xugl.ui.canvas.Canvas;
import xugl.ui.display.AbstractDisplay;
import xugl.ui.display.Display;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * @author link
 */
public final class GLFWDisplay extends AbstractDisplay {

	static {
		if (glfwInit())
			throw new IllegalStateException("Failed to initialize GLFW");
	}

	private final long window;
	private final GLFWKeyboard keyboard;
	private final Logger logger;


	public GLFWDisplay(int x, int y, int width, int height, String title) {
		this(x, y, width, height, title, NULL, NULL);
	}

	private GLFWDisplay(int x, int y, int width, int height, String title, long monitor, long windowHandle) {
		super(x, y, width, height, title);
		// logging
		logger = new GLFWLogger("Display");

		logger.log("Creating GLFWDisplay...");

		// create window
		window = glfwCreateWindow(this.width, this.height, title, monitor, windowHandle);
		glfwMakeContextCurrent(window);
		// create caps
		GL.createCapabilities();
		// set the error callback
		glfwSetErrorCallback((GLFWLogger) logger);
		// create keyboard and set the callback for the window
		keyboard = new GLFWKeyboard(this);
		glfwSetKeyCallback(window, keyboard);
		// set window size
		glfwSetWindowSize(window, width, height);
		logger.log("GLFWDisplay initialized.");

	}

	public long getHandle(GLFWKeyboard accessor) {
		return accessor == this.keyboard ? window : NULL;
	}

	@Override
	public void setX(int x) {
		glfwMakeContextCurrent(window);
		glfwSetWindowPos(window, this.x = x, y);
	}

	@Override
	public void setY(int y) {
		glfwMakeContextCurrent(window);
		glfwSetWindowPos(window, x, this.y = y);
	}

	@Override
	public void show() {
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
	}

	@Override
	public void hide() {
		glfwHideWindow(window);
	}

	@Override
	public void close() {
		glfwMakeContextCurrent(window);
		glfwSetWindowShouldClose(window, true);
	}

	@Override
	public void destroy() {
		glfwMakeContextCurrent(window);
		glfwDestroyWindow(window);
	}

	@Override
	public void render(Canvas canvas) {
		glfwMakeContextCurrent(window);
		glfwSwapBuffers(window);
	}

	@Override
	public void refresh() {
	}

	@Override
	public Display createChild(int x, int y, int width, int height, String title) {
		return new GLFWDisplay(x, y, width, height, title, 0, window);
	}

	@Override
	public Keyboard getKeyboard() {
		return keyboard;
	}

}
