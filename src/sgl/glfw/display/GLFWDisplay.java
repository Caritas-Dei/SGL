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
package sgl.glfw.display;

import org.lwjgl.system.NativeResource;
import sgl.canvas.Renderer;
import sgl.display.AbstractDisplay;
import sgl.display.Display;
import sgl.display.Mode;
import sgl.glfw.io.GLFWKeyboard;
import sgl.glfw.log.GLFWLogger;
import sgl.hardware.Screen;
import sgl.io.Keyboard;
import sgl.opengl.OpenGL;
import sgl.util.jni.NativeAccessible;
import sgl.util.log.Logger;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * @author link
 */
public final class GLFWDisplay extends AbstractDisplay implements NativeAccessible, NativeResource {

	private static final String vsSrc = "#version 150\n" + "\n" + "in vec2 position;\n" + "\n" + "void main()\n" + "{\n" + "    gl_Position = vec4(position, 0.0, 1.0);\n" + "}";
	private static final String fsSrc = "#version 150\n" + "\n" + "out vec4 outColor;\n" + "\n" + "void main()\n" + "{\n" + "    outColor = vec4(1.0, 1.0, 1.0, 1.0);\n" + "}";
	private static final int vshader, fshader, defaultProgram;

	// init GLFW
	static {
		if (!glfwInit())
			throw new IllegalStateException("Failed to initialize GLFW");
	}

	// init OpenGL and create default Shaders
	static {
		vshader = glCreateShader(GL_VERTEX_SHADER);
		fshader = glCreateShader(GL_FRAGMENT_SHADER);
		defaultProgram = glCreateProgram();

		glShaderSource(vshader, vsSrc);
		glShaderSource(fshader, fsSrc);

		glCompileShader(vshader);
		glCompileShader(fshader);

		glAttachShader(defaultProgram, vshader);
		glAttachShader(defaultProgram, fshader);

		glLinkProgram(defaultProgram);
		glUseProgram(defaultProgram);
	}

	// separate shader create/init and vertex attrib linking to simplify debugging
	static {
		int pos = glGetAttribLocation(defaultProgram, "position");
		glVertexAttribPointer(pos, 2, GL_FLOAT_VEC2, false, 0, 0);
		glEnableVertexAttribArray(pos);
	}

	private final long window;
	private final GLFWKeyboard keyboard;
	private final Logger logger;
	private Mode mode;

	public GLFWDisplay(int x, int y, int width, int height, String title, Renderer renderer) {
		this(x, y, width, height, title, renderer, NULL, NULL);
	}

	private GLFWDisplay(int x, int y, int width, int height, String title, Renderer renderer, long monitor, long windowHandle) {
		super(x, y, width, height, title, renderer);
		// logging
		logger = new GLFWLogger("Display");

		logger.log("Creating GLFWDisplay...");
		// create window
		window = glfwCreateWindow(this.width, this.height, title, monitor, windowHandle);
		// this initializes an OpenGL context with the current Thread and window
		glfwMakeContextCurrent(window);
		// create caps
		OpenGL.initialize();
		// setfv the error callback
		glfwSetErrorCallback((GLFWLogger) logger);
		// create keyboard and setfv the callback for the window
		keyboard = new GLFWKeyboard(this);
		glfwSetKeyCallback(window, keyboard);
		// set window size
		glfwSetWindowSize(window, width, height);
		// set the window refresh callback (to handle syscalls and refresh callbacks)
		glfwSetWindowRefreshCallback(window, (_this_window_) -> refresh());
		logger.log("GLFWDisplay initialized.");

	}

	@Override
	public void setX(int x) {
		glfwSetWindowPos(window, this.x = x, y);
	}

	@Override
	public void setY(int y) {
		glfwSetWindowPos(window, x, this.y = y);
	}

	@Override
	public void setWidth(int width) {
		glfwSetWindowSize(window, this.width = width, height);
	}

	@Override
	public void setHeight(int height) {
		glfwSetWindowSize(window, width, this.height = height);
	}

	@Override
	public void setSize(int width, int height) {
		glfwSetWindowSize(window, this.width = width, this.height = height);
	}

	@Override
	public Mode getMode() {
		return mode;
	}

	@Override
	public void setMode(Mode mode) {
		if (this.mode != mode)
			switch (mode) {
				case WINDOWED:
					glfwSetWindowMonitor(window, glfwGetPrimaryMonitor(), 0, 0, Screen.getWidth(), Screen.getHeight(), GLFW_DONT_CARE);
					break;
				case FULLSCREEN:
					glfwSetWindowMonitor(window, NULL, x, y, width, height, GLFW_DONT_CARE);
					break;
				default:
					throw new IllegalArgumentException("This should not have been thrown!");
			}
	}

	@Override
	public void show() {
		glfwShowWindow(window);
	}

	@Override
	public void hide() {
		glfwHideWindow(window);
	}

	@Override
	public void free() {
		glfwDestroyWindow(window);
	}

	@Override
	public void close() {
		glfwSetWindowShouldClose(window, true);
	}

	@Override
	public void refresh() {
		glfwSwapBuffers(window);
	}

	@Override
	public Display createChild(int x, int y, int width, int height, String title) {
		return new GLFWDisplay(x, y, width, height, title, super.renderer, NULL, NULL);
	}

	@Override
	public Keyboard getKeyboard() {
		return keyboard;
	}


//	@Override
//	public Mouse getMouse() {
//		return null;
//	}

	public long ptr() {
		return window;
	}

}
