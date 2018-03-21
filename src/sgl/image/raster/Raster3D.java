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

import sgl.buffer.Buffer3D;
import sgl.image.color.Color;

/**
 * @author link
 */
public interface Raster3D extends Raster2D, Buffer3D {

	/**
	 * Gets a cubic region of colors from this Raster3D with the given x, y, z, width, height, and depth, where (0, 0, 0) is the
	 * front-top-left corner of the region.
	 *
	 * @param x      the x coordinate of the region
	 * @param y      the y coordinate of the region
	 * @param z      the z coordinate of the region
	 * @param width  the width of the region
	 * @param height the height of the region
	 * @param depth  the depth of the region
	 * @param colors the colors to put the read color data into
	 */
	default void get(int x, int y, int z, int width, int height, int depth, Color[] colors) {
		get(x + y + z * height, width * height * depth, colors);
	}

	/**
	 * Sets a cubic region of colors in this Raster2D with the given x, y, z, width, height, depth, and colors, where (0, 0, 0) is the
	 * front-top-left corner of the region.
	 *
	 * @param x      the x coordinate of the region
	 * @param y      the y coordinate of the region
	 * @param z      the z coordinate of the region
	 * @param width  the size of the region
	 * @param height the height of the region
	 * @param depth  the depth of the region
	 * @param colors the colors to put into this Raster3D
	 */
	default void set(int x, int y, int z, int width, int height, int depth, Color[] colors) {
		set(x + y + z * height, width * height * depth, colors);
	}

}
