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
package sgl.opengl.image.color;

import sgl.image.color.Color;

/**
 * @author link
 */
public class GLColor implements Color {

	protected final float r, g, b, a;

	public GLColor(int r, int g, int b, int a) {
		this(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f);
	}

	public GLColor(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	@Override
	public float red() {
		return r;
	}

	@Override
	public float green() {
		return g;
	}

	@Override
	public float blue() {
		return b;
	}

	@Override
	public float alpha() {
		return a;
	}

	@Override
	public int rgba() {
		int red = (int) (r * 255) << 24;
		int green = (int) (g * 255) << 16;
		int blue = (int) (b * 255) << 8;
		int alpha = (int) (a * 255);
		return red | green | blue | alpha;
	}

	@Override
	public int argb() {
		int alpha = (int) (a * 255) << 24;
		int red = (int) (r * 255) << 16;
		int green = (int) (g * 255) << 8;
		int blue = (int) (b * 255);
		return alpha | red | green | blue;
	}

	public class Ref implements Color {

		protected int buffer, index;

		public Ref(int buffer, int index) {
			this.buffer = buffer;
			this.index = index;
		}

		@Override
		public float red() {
			return 0;
		}

		@Override
		public float green() {
			return 0;
		}

		@Override
		public float blue() {
			return 0;
		}

		@Override
		public float alpha() {
			return 0;
		}

		@Override
		public int rgba() {
			return 0;
		}

		@Override
		public int argb() {
			return 0;
		}

		public int getBuffer() {
			return buffer;
		}

		public int getIndex() {
			return index;
		}

	}
}
