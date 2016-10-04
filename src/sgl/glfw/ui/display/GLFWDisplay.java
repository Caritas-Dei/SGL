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
package sgl.glfw.ui.display;

import sgl.gl.GLObject;
import sgl.gl.OpenGL;
import sgl.gl.image.GLImage;
import sgl.glfw.io.GLFWKeyboard;
import sgl.glfw.log.GLFWLogger;
import sgl.io.Keyboard;
import sgl.log.Logger;
import sgl.ui.canvas.Canvas;
import sgl.ui.display.AbstractDisplay;
import sgl.ui.display.Display;
import sgl.ui.image.Image;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * @author link
 */
public final class GLFWDisplay extends AbstractDisplay implements GLObject {

	private static final String vsSrc = "#getVersion 150\n" +
			                                    "\n" +
			                                    "in vec2 position;\n" +
			                                    "\n" +
			                                    "void main()\n" +
			                                    "{\n" +
			                                    "    gl_Position = vec4(position, 0.0, 1.0);\n" +
			                                    "}",
			fsSrc = "#getVersion 150\n" +
					        "\n" +
					        "out vec4 outColor;\n" +
					        "\n" +
					        "void main()\n" +
					        "{\n" +
					        "    outColor = vec4(1.0, 1.0, 1.0, 1.0);\n" +
					        "}";
	private static final int vshader, fshader, defaultProgram;

	// init GLFW
	static {
		if (!glfwInit())
			throw new IllegalStateException("Failed to initialize GLFW");
	}

	// init OpenGL and create default Shaders
	static {
		vshader = OpenGL.shader(GL_VERTEX_SHADER);
		fshader = OpenGL.shader(GL_FRAGMENT_SHADER);
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

	public GLFWDisplay(int x, int y, int width, int height, String title,
	                   Canvas canvas) {
		this(x, y, width, height, title, canvas, NULL, NULL);
	}

	private GLFWDisplay(int x, int y, int width, int height, String title,
	                    Canvas canvas, long monitor, long windowHandle) {
		super(x, y, width, height, title, canvas);
		// logging
		logger = new GLFWLogger("Display");

		logger.log("Creating GLFWDisplay...");
		// create window
		window = glfwCreateWindow(this.width, this.height, title, monitor, windowHandle);
		glfwMakeContextCurrent(window);
		// create caps
		OpenGL.initialize();
		// setfv the error callback
		glfwSetErrorCallback((GLFWLogger) logger);
		// create keyboard and setfv the callback for the window
		keyboard = new GLFWKeyboard(this);
		glfwSetKeyCallback(window, keyboard);
		// setfv window size
		glfwSetWindowSize(window, width, height);
		logger.log("GLFWDisplay initialized.");

	}

	private static void glRender(Image buffer, int vbo) {
		int texture = new GLImage(GL_TEXTURE_2D, 0, 0, buffer).gl_ptr();

		glBindTexture(GL_TEXTURE_2D, texture);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		// invisible border: render only the image
		glTexParameterfv(GL_TEXTURE_2D, GL_TEXTURE_BORDER_COLOR, new float[]{0.0f, 0.0f, 0.0f, 0.0f});

		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glDrawArrays(GL_TRIANGLES, 0, 6);

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
		glfwMakeContextCurrent(window);
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
	public void refresh() {
//		glRender(buffer, vbo);
		glfwMakeContextCurrent(window);
		glfwSwapBuffers(window);
	}

	@Override
	public Display createChild(int x, int y, int width, int height,
	                           String title, Canvas canvas) {
		return new GLFWDisplay(x, y, width, height, title, canvas, 0, window);
	}

	@Override
	public Keyboard getKeyboard() {
		return keyboard;
	}

	@Override
	public int gl_ptr() {
		return GL_NULL;
	}

	@Override
	public long ptr() {
		return window;
	}
}
