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

package sgl.image.raster;

import sgl.buffer.Buffer2D;
import sgl.image.color.Color;

/**
 * @author link
 */
public interface Raster2D extends Raster, Buffer2D {

	/**
	 * Gets a rectangular region of colors from this Raster2D with the given x, y, size, and height, where (0, 0) is the
	 * top left corner of the region.
	 *
	 * @param x      the x coordinate of the region
	 * @param y      the y coordinate of the region
	 * @param width  the size of the region
	 * @param height the height of the region
	 * @param colors the colors to put the read color data into
	 */
	default void get(int x, int y, int width, int height, final Color[] colors) {
		get(x + y * width, width * height, colors);
	}

	/**
	 * Sets a rectangular region of colors from this Raster2D with the given x, y, size, and height, where (0, 0) is the
	 * top left corner of the region.
	 *
	 * @param x      the x coordinate of the region
	 * @param y      the y coordinate of the region
	 * @param width  the size of the region
	 * @param height the height of the region
	 * @param colors the colors to put the read color data into
	 */
	default void set(int x, int y, int width, int height, final Color[] colors) {
		set(x + y * width, width * height, colors);
	}

}
