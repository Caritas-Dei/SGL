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
 * Created file on 12/27/15 at 4:08 PM.
 *
 * This file is part of jGUI
 */

package xugl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import xugl.glfw.io.GLFWKeyboard;
import xugl.glfw.log.GLFWLogger;
import xugl.glfw.ui.display.GLFWDisplay;
import xugl.io.Keyboard;
import xugl.log.Logger;
import xugl.ui.display.Display;

/**
 * @author link
 */
public class Main {

	private static final Logger LOGGER = new GLFWLogger("Main Logger");

	public static void main(String... args) {
		LOGGER.log("Started XUGL Test");

		Display display = new GLFWDisplay(0, 0, 800, 600, "Display Test");
		Keyboard keyboard = display.getKeyboard();
		display.show();

		GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

		while (!GLFW.glfwWindowShouldClose(((GLFWDisplay) display).getHandle((GLFWKeyboard) keyboard))) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GLFW.glfwSwapBuffers(((GLFWDisplay) display).getHandle((GLFWKeyboard) keyboard));
			GLFW.glfwPollEvents();
			if (keyboard.isKeyTyped(GLFW.GLFW_KEY_ESCAPE)) {
				display.close();
			}
		}
	}
}
