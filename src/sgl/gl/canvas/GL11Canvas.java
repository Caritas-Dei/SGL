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
 * Created file on 8/27/16 at 9:43 AM.
 *
 * This file is part of XUGL
 */
package sgl.gl.canvas;

import sgl.ui.canvas.Canvas;
import sgl.ui.image.Image;
import sgl.ui.image.color.Color;

import static org.lwjgl.opengl.GL11.*;

/**
 * OpenGL 1.1 Canvas
 *
 * @author link
 */
public class GL11Canvas implements Canvas {

	@Override
	public void draw(Image image, int x, int y) {
		glRasterPos2i(x, y);
		glDrawPixels(800, 600, GL_RGB, GL_UNSIGNED_INT, image.values());
	}

	@Override
	public void draw(Color color, int x, int y) {
		glRasterPos2i(x, y);
		glDrawPixels(1, 1, GL_RGBA, GL_UNSIGNED_INT, new int[]{color.rgba()});
	}

}
