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
package sgl.buffer;

import java.nio.*;

/**
 * @author link
 */
public interface Buffer2D extends Buffer {

	/**
	 * Gets the size of this Buffer object
	 *
	 * @return the size of this Buffer object
	 */
	int getWidth();

	/**
	 * Sets the size of this Buffer object
	 *
	 * @param width the size of this Buffer object
	 */
	void setWidth(int width);

	/**
	 * Gets the height of this Buffer object
	 *
	 * @return the height of this Buffer object
	 */
	int getHeight();

	/**
	 * Sets the height of this Buffer object
	 *
	 * @param height the height of this Buffer object
	 */
	void setHeight(int height);

	/*
	 * (x, y) -> (index) conversion (for real coordinate pairs):
	 *
	 * size = 3, height = 4
	 * [0,  1,  2]
	 * [3,  4,  5]
	 * [6,  7,  8]
	 * [9, 10, 11]
	 *
	 *  (x - 1) + (y - 1) * size = index
	 */


	/**
	 * Gets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data in the given ByteBuffer.
	 *
	 * @param x         the x coordinate
	 * @param y         the y coordinate
	 * @param width     the size of the ByteBuffer
	 * @param height    the height of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	default void get(int x, int y, int width, int height, ByteBuffer dataWrite) {
		get(x + y * width, width * height, dataWrite);
	}

	/**
	 * Sets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data from the given ByteBuffer into this Buffer.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param width  the size of the ByteBuffer
	 * @param height the height of the ByteBuffer
	 * @param data   the ByteBuffer to write to
	 */
	default void set(int x, int y, int width, int height, ByteBuffer data) {
		set(x + y * width, width * height, data);
	}

	/**
	 * Gets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data in the given ByteBuffer.
	 *
	 * @param x         the x coordinate
	 * @param y         the y coordinate
	 * @param width     the size of the ByteBuffer
	 * @param height    the height of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	default void get(int x, int y, int width, int height, ShortBuffer dataWrite) {
		get(x + y * width, width * height, dataWrite);
	}

	/**
	 * Sets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data from the given ByteBuffer into this Buffer.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param width  the size of the ByteBuffer
	 * @param height the height of the ByteBuffer
	 * @param data   the ByteBuffer to write to
	 */
	default void set(int x, int y, int width, int height, ShortBuffer data) {
		set(x + y * width, width * height, data);
	}

	/**
	 * Gets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data in the given ByteBuffer.
	 *
	 * @param x         the x coordinate
	 * @param y         the y coordinate
	 * @param width     the size of the ByteBuffer
	 * @param height    the height of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	default void get(int x, int y, int width, int height, IntBuffer dataWrite) {
		get(x + y * width, width * height, dataWrite);
	}

	/**
	 * Sets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data from the given ByteBuffer into this Buffer.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param width  the size of the ByteBuffer
	 * @param height the height of the ByteBuffer
	 * @param data   the ByteBuffer to write to
	 */
	default void set(int x, int y, int width, int height, IntBuffer data) {
		set(x + y * width, width * height, data);
	}

	/**
	 * Gets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data in the given ByteBuffer.
	 *
	 * @param x         the x coordinate
	 * @param y         the y coordinate
	 * @param width     the size of the ByteBuffer
	 * @param height    the height of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	default void get(int x, int y, int width, int height, LongBuffer dataWrite) {
		get(x + y * width, width * height, dataWrite);
	}

	/**
	 * Sets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data from the given ByteBuffer into this Buffer.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param width  the size of the ByteBuffer
	 * @param height the height of the ByteBuffer
	 * @param data   the ByteBuffer to write to
	 */
	default void set(int x, int y, int width, int height, LongBuffer data) {
		set(x + y * width, width * height, data);
	}

	/**
	 * Gets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data in the given ByteBuffer.
	 *
	 * @param x         the x coordinate
	 * @param y         the y coordinate
	 * @param width     the size of the ByteBuffer
	 * @param height    the height of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	default void get(int x, int y, int width, int height, FloatBuffer dataWrite) {
		get(x + y * width, width * height, dataWrite);
	}

	/**
	 * Sets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data from the given ByteBuffer into this Buffer.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param width  the size of the ByteBuffer
	 * @param height the height of the ByteBuffer
	 * @param data   the ByteBuffer to write to
	 */
	default void set(int x, int y, int width, int height, FloatBuffer data) {
		set(x + y * width, width * height, data);
	}

	/**
	 * Gets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data in the given ByteBuffer.
	 *
	 * @param x         the x coordinate
	 * @param y         the y coordinate
	 * @param width     the size of the ByteBuffer
	 * @param height    the height of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	default void get(int x, int y, int width, int height, DoubleBuffer dataWrite) {
		get(x + y * width, width * height, dataWrite);
	}

	/**
	 * Sets the data starting at (x, y), and ending at (x + size, y + height),
	 * then places the data from the given ByteBuffer into this Buffer.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param width  the size of the ByteBuffer
	 * @param height the height of the ByteBuffer
	 * @param data   the ByteBuffer to write to
	 */
	default void set(int x, int y, int width, int height, DoubleBuffer data) {
		set(x + y * width, width * height, data);
	}


}
