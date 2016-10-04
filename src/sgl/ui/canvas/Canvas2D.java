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
 * Created file on 9/30/16 at 9:27 AM.
 *
 * This file is part of XUGL
 */
package sgl.ui.canvas;

import sgl.math.point.Point2D;
import sgl.ui.canvas.shape.Shape;
import sgl.ui.image.Image;
import sgl.ui.image.color.Color;

/**
 * @author link
 */
public interface Canvas2D extends Canvas {

	int getWidth();

	void setWidth(int width);

	int getHeight();

	void setHeight(int height);

	default void offset(double x, double y) {
		// canvas[x][y] \mapsto canvas[x + y * getWidth()]
		offset(x + y * getWidth());
	}

	void draw(Color color, double x, double y);

	default void draw(Color color, Point2D point) {
		draw(color, point.getX(), point.getY());
	}

	void draw(Image image, double x, double y);

	default void draw(Image image, Point2D point) {
		draw(image, point.getX(), point.getY());
	}

	void draw(Shape shape, double x, double y);

	default void draw(Shape shape, Point2D point) {
		draw(shape, point.getX(), point.getY());
	}

}
