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
 * Created file on 9/23/16 at 7:06 PM.
 *
 * This file is part of XUGL
 */
package sgl.gl.canvas;

import sgl.gl.OpenGL;
import sgl.ui.canvas.Canvas;
import sgl.ui.image.Image;
import sgl.ui.image.color.Color;

/**
 * @author link
 */
public final class GLCanvas implements Canvas {

	private Canvas glCanvas;

	public GLCanvas() {
		this((GLCanvas) OpenGL.getContext().getDisplay().getCanvas());
	}

	public GLCanvas(GLCanvas canvas) {
		glCanvas = canvas;
	}

	@Override
	public void draw(Image image, int x, int y) {
		glCanvas.draw(image, x, y);
	}

	@Override
	public void draw(Color color, int x, int y) {
		glCanvas.draw(color, x, y);
	}
}
