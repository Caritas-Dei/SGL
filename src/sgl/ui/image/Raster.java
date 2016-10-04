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
 * Created file on 9/19/16 at 1:20 PM.
 *
 * This file is part of XUGL
 */
package sgl.ui.image;

import sgl.ui.canvas.Canvas;
import sgl.ui.image.color.Color;

/**
 * @author link
 */
public class Raster<C extends Canvas> implements Image<C> {

	protected Color[] pixels;

	public Raster() {
		this(0);
	}

	public Raster(int size) {
		pixels = new Color[size];

	}

	@Override
	public int getSize() {
		return pixels.length;
	}

	@Override
	public Color get(int index) {
		return pixels[index];
	}

	@Override
	public void set(int index, Color color) {
		pixels[index] = color;
	}

	@Override
	public Color[] get() {
		return new Color[0];
	}

	@Override
	public void set(Color[] colors) {

	}

	@Override
	public int[] values() {
		return new int[0];
	}

	@Override
	public void values(int[] values) {

	}

	@Override
	public void draw(C canvas) {

	}
}
