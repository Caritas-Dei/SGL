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
package sgl;

import sgl.display.Display;
import sgl.display.Mode;
import sgl.glfw.display.GLFWDisplay;
import sgl.glfw.log.GLFWLogger;
import sgl.image.color.Color;
import sgl.image.color.RGBAColor;
import sgl.io.Keyboard;
import sgl.opengl.texture.GLTexture2D;
import sgl.util.log.Logger;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author link
 */
public class Main {

	private static final Logger LOGGER = new GLFWLogger("Main Logger");

	public static void main(String... args) {

		LOGGER.log("Started SGL Test");

		Display display = new GLFWDisplay(0, 0, 800, 600, "GLFW Display", null);
		Keyboard keyboard = display.getKeyboard();
		display.show();
		GLTexture2D texture = new GLTexture2D(800, 600);
		texture.set(0, 1, new Color[]{new RGBAColor(255, 255, 255, 255)});
		Color[] colors = new Color[1];
		texture.get(0, 1, colors);
		System.out.println(Arrays.toString(colors));
		// set the clear color for the buffer
		glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

		// the initial vertex shader
		//int vsh = glCreateShader(GL_VERTEX_SHADER);

		// the initial fragment shader
		//int fsh = glCreateShader(GL_FRAGMENT_SHADER);
		while (!glfwWindowShouldClose(((GLFWDisplay) display).ptr())) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glfwWaitEvents();

			if (keyboard.isKeyTyped(GLFW_KEY_ESCAPE)) {
				display.close();
			} else if (keyboard.isKeyTyped(GLFW_KEY_R)) {
				display.refresh();
			} else if (keyboard.isKeyTyped(GLFW_KEY_F11)) {
				display.setMode(Mode.FULLSCREEN);
			}
			display.refresh();
		}
	}

}
