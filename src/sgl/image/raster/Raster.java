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

import sgl.buffer.Buffer;
import sgl.image.color.Color;

/**
 * A Raster for an Image. This object contains the actual data for an image.
 *
 * @author link
 */
public interface Raster extends Buffer {

	/**
	 * Gets a range of colors from the given <em>offset</em> to <em>offset +
	 * size</em> in this Raster and places them in the given Color array.
	 *
	 * @param offset the offset into this Raster
	 * @param size   the size of the Color[] array
	 * @param color  the array to write to
	 */
	void get(int offset, int size, final Color[] color);

	/**
	 * Sets a range of colors from the given <em>offset</em> to <em>offset +
	 * size</em> in the given Color array and places them in this Raster.
	 *
	 * @param offset the offset into this Raster
	 * @param size   the size of the Color[] array
	 * @param color  the array to read from
	 */
	void set(int offset, int size, final Color[] color);

}
