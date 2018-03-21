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
public interface Buffer3D extends Buffer2D {

	/**
	 * Gets the depth of this Buffer3D.
	 *
	 * @return the depth of this Buffer3D
	 */
	int getDepth();

	/**
	 * Sets the depth of this Buffer3D.
	 *
	 * @param depth the depth of this Buffer3D
	 */
	void setDepth(int depth);


	default void get(int x, int y, int z, int width, int height, int depth, ByteBuffer write) {
		get(x + y + z * height, width * height * depth, write);
	}

	default void set(int x, int y, int z, int width, int height, int depth, ByteBuffer read) {
		set(x + y + z * height, width * height * depth, read);
	}

	default void get(int x, int y, int z, int width, int height, int depth, ShortBuffer write) {
		get(x + y + z * height, width * height * depth, write);
	}

	default void set(int x, int y, int z, int width, int height, int depth, ShortBuffer read) {
		set(x + y + z * height, width * height * depth, read);
	}

	default void get(int x, int y, int z, int width, int height, int depth, IntBuffer write) {
		get(x + y + z * height, width * height * depth, write);
	}

	default void set(int x, int y, int z, int width, int height, int depth, IntBuffer read) {
		set(x + y + z * height, width * height * depth, read);
	}

	default void get(int x, int y, int z, int width, int height, int depth, LongBuffer write) {
		get(x + y + z * height, width * height * depth, write);
	}

	default void set(int x, int y, int z, int width, int height, int depth, LongBuffer read) {
		set(x + y + z * height, width * height * depth, read);
	}

	default void get(int x, int y, int z, int width, int height, int depth, FloatBuffer write) {
		get(x + y + z * height, width * height * depth, write);
	}

	default void set(int x, int y, int z, int width, int height, int depth, FloatBuffer read) {
		set(x + y + z * height, width * height * depth, read);
	}

	default void get(int x, int y, int z, int width, int height, int depth, DoubleBuffer write) {
		get(x + y + z * height, width * height * depth, write);
	}

	default void set(int x, int y, int z, int width, int height, int depth, DoubleBuffer read) {
		set(x + y + z * height, width * height * depth, read);
	}

}
