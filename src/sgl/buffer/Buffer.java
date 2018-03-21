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
public interface Buffer {

	/**
	 * Gets the size of this Buffer.
	 *
	 * @return the size of this Buffer
	 */
	int getSize();

	/**
	 * Sets the size of this Buffer.
	 *
	 * @param size the size of this Buffer
	 */
	void setSize(int size);

	/**
	 * Gets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset    the offset into this Buffer
	 * @param size      the size of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	void get(int offset, int size, final ByteBuffer dataWrite);

	/**
	 * Sets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset the offset into this Buffer
	 * @param size   the size of the ByteBuffer
	 * @param data   the ByteBuffer to read from
	 */
	void set(int offset, int size, final ByteBuffer data);

	/**
	 * Gets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset    the offset into this Buffer
	 * @param size      the size of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	void get(int offset, int size, final ShortBuffer dataWrite);

	/**
	 * Sets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset the offset into this Buffer
	 * @param size   the size of the ByteBuffer
	 * @param data   the ByteBuffer to read from
	 */
	void set(int offset, int size, final ShortBuffer data);

	/**
	 * Gets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset    the offset into this Buffer
	 * @param size      the size of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	void get(int offset, int size, final IntBuffer dataWrite);

	/**
	 * Sets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset the offset into this Buffer
	 * @param size   the size of the ByteBuffer
	 * @param data   the ByteBuffer to read from
	 */
	void set(int offset, int size, final IntBuffer data);

	/**
	 * Gets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset    the offset into this Buffer
	 * @param size      the size of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	void get(int offset, int size, final LongBuffer dataWrite);

	/**
	 * Sets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset the offset into this Buffer
	 * @param size   the size of the ByteBuffer
	 * @param data   the ByteBuffer to read from
	 */
	void set(int offset, int size, final LongBuffer data);

	/**
	 * Gets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset    the offset into this Buffer
	 * @param size      the size of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	void get(int offset, int size, final FloatBuffer dataWrite);

	/**
	 * Sets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset the offset into this Buffer
	 * @param size   the size of the ByteBuffer
	 * @param data   the ByteBuffer to read from
	 */
	void set(int offset, int size, final FloatBuffer data);

	/**
	 * Gets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset    the offset into this Buffer
	 * @param size      the size of the ByteBuffer
	 * @param dataWrite the ByteBuffer to write to
	 */
	void get(int offset, int size, final DoubleBuffer dataWrite);

	/**
	 * Sets the data at the given offset from <em>offset</em> to <em>offset +
	 * size</em> directly into the given ByteBuffer.
	 *
	 * @param offset the offset into this Buffer
	 * @param size   the size of the ByteBuffer
	 * @param data   the ByteBuffer to read from
	 */
	void set(int offset, int size, final DoubleBuffer data);

}
