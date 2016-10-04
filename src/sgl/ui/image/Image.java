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
 * Created file on 5/27/16 at 6:55 AM.
 *
 * This file is part of XUGL
 */
package sgl.ui.image;

import sgl.ui.canvas.Canvas;
import sgl.ui.canvas.Renderable;
import sgl.ui.image.color.Color;

import java.nio.Buffer;

/**
 * @author link
 */
public interface Image<C extends Canvas, B extends Buffer> extends Renderable<C> {

	int getSize();

	Color get(int index);

	void set(int index, Color color);

	default B getValues() {
		return getValues(0, getSize() - 1);
	}

	B getValues(int start, int end);

	default void setValues(B values) {
		setValues(values, 0, getSize() - 1);
	}

	void setValues(B values, int start, int end);

}
