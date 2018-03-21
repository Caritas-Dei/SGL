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
package sgl.opengl.canvas;

import sgl.canvas.Canvas;
import sgl.image.Image;
import sgl.image.color.Color;
import sgl.opengl.image.GLImage;
import sgl.shape.Shape;

/**
 * @author link
 */
public class GLCanvas implements Canvas {

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public void setSize(int size) {

	}

	@Override
	public void offset(double offset) {

	}

	@Override
	public void draw(Color color) {
	}

	@Override
	public void draw(Image image) {
		if (image instanceof GLImage) {

		}
	}

	@Override
	public void draw(Shape shape) {

	}

	@Override
	public void draw(double offset, Color color) {

	}

	@Override
	public void draw(double offset, Image image) {

	}

	@Override
	public void draw(double offset, Shape shape) {

	}

	@Override
	public void fill(double offset, double size, Color color) {

	}
}
