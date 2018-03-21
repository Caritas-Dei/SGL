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
package sgl.opengl.buffer;

import org.lwjgl.opengl.*;
import sgl.opengl.GLObject;
import sgl.opengl.OpenGL;
import sgl.opengl.OpenGL.Feature;
import sgl.opengl.error.UnsupportedFeatureException;
import sgl.opengl.error.UnsupportedProfileException;

import java.nio.*;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.memAlloc;
import static org.lwjgl.system.MemoryUtil.memFree;

/**
 * Represents a general-purpose, OpenGL buffer implementation which uses the
 * available
 * buffer implementation. This data is stored on the GPU.
 * <p>
 * If the OpenGL version is 1.4 or lower, this class uses texture names and
 * operations to substitute buffers. This buffer will effectively become a 1D GL
 * texture object.
 * </p>
 *
 * @author link
 */
public class GLVertexBuffer implements GLObject, sgl.buffer.Buffer {

	static {
		if (!OpenGL.supports(Feature.EXT_VERTEX_ARRAY))
			throw new UnsupportedProfileException(GLVertexBuffer.class, "Unsupported feature", new UnsupportedFeatureException(GLVertexBuffer.class, Feature.EXT_VERTEX_ARRAY));
	}

	protected int buffer, size, target, usage;

	public GLVertexBuffer() {
		this(0);
	}

	public GLVertexBuffer(int size) {
		this(size, GL_DYNAMIC_DRAW);
	}

	public GLVertexBuffer(int size, int usage) {
		this(GL_ARRAY_BUFFER, size, usage);
	}

	public GLVertexBuffer(int target, int size, int usage) {
		this(glGenBuffers(), size, target, usage);
	}

	protected GLVertexBuffer(int buffer, int target, int size, int usage) {
		this.buffer = buffer;
		this.size = size;
		this.target = target;
		this.usage = usage;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the target category for this GLVertexBuffer. The returned values may be
	 * one of:
	 * <table><tr><td>{@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link
	 * GL15#GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link
	 * GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link
	 * GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link
	 * GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link
	 * GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link
	 * GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link
	 * GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link
	 * GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link
	 * GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link
	 * GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link
	 * GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link
	 * GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link
	 * ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
	 *
	 * @return the target category for this GLVertexBuffer
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * Sets the target category for this GLVertexBuffer. The allowed values may be
	 * one of:
	 * <table><tr><td>{@link GL15#GL_ARRAY_BUFFER ARRAY_BUFFER}</td><td>{@link
	 * GL15#GL_ELEMENT_ARRAY_BUFFER ELEMENT_ARRAY_BUFFER}</td><td>{@link
	 * GL21#GL_PIXEL_PACK_BUFFER PIXEL_PACK_BUFFER}</td><td>{@link
	 * GL21#GL_PIXEL_UNPACK_BUFFER PIXEL_UNPACK_BUFFER}</td></tr><tr><td>{@link
	 * GL30#GL_TRANSFORM_FEEDBACK_BUFFER TRANSFORM_FEEDBACK_BUFFER}</td><td>{@link
	 * GL31#GL_UNIFORM_BUFFER UNIFORM_BUFFER}</td><td>{@link
	 * GL31#GL_TEXTURE_BUFFER TEXTURE_BUFFER}</td><td>{@link
	 * GL31#GL_COPY_READ_BUFFER COPY_READ_BUFFER}</td></tr><tr><td>{@link
	 * GL31#GL_COPY_WRITE_BUFFER COPY_WRITE_BUFFER}</td><td>{@link
	 * GL40#GL_DRAW_INDIRECT_BUFFER DRAW_INDIRECT_BUFFER}</td><td>{@link
	 * GL42#GL_ATOMIC_COUNTER_BUFFER ATOMIC_COUNTER_BUFFER}</td><td>{@link
	 * GL43#GL_DISPATCH_INDIRECT_BUFFER DISPATCH_INDIRECT_BUFFER}</td></tr><tr><td>{@link
	 * GL43#GL_SHADER_STORAGE_BUFFER SHADER_STORAGE_BUFFER}</td><td>{@link
	 * ARBIndirectParameters#GL_PARAMETER_BUFFER_ARB PARAMETER_BUFFER_ARB}</td></tr></table>
	 */
	public void setTarget(int target) {
		this.target = target;
	}

	/**
	 * Gets the usage hint for this GLVertexBuffer. The allowed values may be one of:
	 *
	 * @return the usage hint for this GLVertexBuffer
	 */
	public int getUsage() {
		return usage;
	}

	public void setUsage(int usage) {
		this.usage = usage;
	}


	@Override
	public void get(int start, int end, final ByteBuffer result) {
		result.limit(result.capacity() - 1 - (end - start));
		glGetBufferSubData(target, start, result);
	}

	@Override
	public void set(int start, int end, final ByteBuffer data) {
		data.position(0);
		data.limit(end - start);
		glBufferSubData(target, start, data);
	}

	@Override
	public void get(int start, int end, final ShortBuffer result) {
		result.limit(result.capacity() - 1 - (end - start));
		glGetBufferSubData(target, 2 * start, result);
	}

	@Override
	public void set(int start, int end, final ShortBuffer data) {
		data.position(0);
		data.limit(end - start);
		glBufferSubData(target, 2 * start, data);
	}

	@Override
	public void get(int start, int end, final IntBuffer result) {
		result.limit(result.capacity() - 1 - (end - start));
		glGetBufferSubData(target, 4 * start, result);
	}

	@Override
	public void set(int start, int end, final IntBuffer data) {
		data.position(0);
		data.limit(end - start);
		glBufferSubData(target, 4 * start, data);
	}

	@Override
	public void get(int start, int end, final LongBuffer result) {
		final ByteBuffer temp = memAlloc(8 * (size - (end - start)));
		glGetBufferSubData(target, start, temp);
		final LongBuffer tempLong = temp.asLongBuffer();
		result.position(0);
		result.put(tempLong);
		memFree(temp);
	}

	@Override
	public void set(int start, int end, final LongBuffer data) {
		final ByteBuffer temp = memAlloc(8 * (size - (end - start)));
		glBufferSubData(target, start, temp);
		final LongBuffer tempLong = temp.asLongBuffer();
		data.position(0);
		data.put(tempLong);
		memFree(temp);
	}

	@Override
	public void get(int start, int end, final FloatBuffer result) {
		result.limit(result.capacity() - 1 - (end - start));
		glGetBufferSubData(target, start, result);
	}

	@Override
	public void set(int start, int end, final FloatBuffer data) {
		data.position(0);
		data.limit(end - start);
		glBufferSubData(target, 4 * start, data);
	}

	@Override
	public void get(int start, int end, final DoubleBuffer result) {
		result.limit(result.capacity() - 1 - (end - start));
		glGetBufferSubData(target, start, result);
	}

	@Override
	public void set(int start, int end, final DoubleBuffer data) {
		data.position(0);
		data.limit(end - start);
		glBufferSubData(target, 4 * start, data);
	}


	@Override
	public final int glName() {
		return buffer;
	}

	@Override
	public final void bind() {
		glBindBuffer(target, buffer);
	}
}
