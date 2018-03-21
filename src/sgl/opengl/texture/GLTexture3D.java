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

package sgl.opengl.texture;

import sgl.buffer.Buffer3D;

import java.nio.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.glTexSubImage3D;
import static org.lwjgl.system.MemoryUtil.memAddress;

/**
 * @author link
 */
public class GLTexture3D extends GLTexture2D implements Buffer3D {

	protected int depth;

	public GLTexture3D() {
		this(0, 0, 0);
	}

	public GLTexture3D(int width, int height, int depth) {
		this(Target.TEXTURE_3D, width, height, depth);
	}

	public GLTexture3D(Target target, int width, int height, int depth) {
		this(target, GL_RGBA, width, height, depth);
	}

	public GLTexture3D(Target target, int internalFormat, int width, int height, int depth) {
		this(target, internalFormat, width, height, depth, GL_FLOAT);
	}

	public GLTexture3D(Target target, int internalFormat, int width, int height, int depth, int format) {
		this(target, internalFormat, width, height, depth, format, GL_UNSIGNED_INT);
	}

	public GLTexture3D(Target target, int internalFormat, int width, int height, int depth, int format, int type) {
		this(target, 0, internalFormat, width, height, depth, format, type);
	}

	public GLTexture3D(Target target, int level, int internalFormat, int width, int height, int depth, int format, int type) {
		this(target, level, internalFormat, width, height, depth, 0, format, type);
	}

	public GLTexture3D(Target target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type) {
		this(glGenTextures(), target.glEnumConstant, level, internalFormat, width, height, depth, border, format, type);
	}

	public GLTexture3D(int texture, int target, int level, int internalFormat, int width, int height, int depth, int border, int format, int type) {
		super(texture, target, level, internalFormat, width, height, border, format, type);
		this.depth = depth;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public void set(int x, int y, int z, int width, int height, int depth, ByteBuffer read) {
		glTexSubImage3D(target, level, x, y, z, width, height, depth, format, type, read);
	}

	@Override
	public void set(int x, int y, int z, int width, int height, int depth, ShortBuffer read) {
		glTexSubImage3D(target, level, x, y, z, width, height, depth, format, type, read);
	}

	@Override
	public void set(int x, int y, int z, int width, int height, int depth, IntBuffer read) {
		glTexSubImage3D(target, level, x, y, z, width, height, depth, format, type, read);
	}

	@Override
	public void set(int x, int y, int z, int width, int height, int depth, LongBuffer read) {
		glTexSubImage3D(target, level, x, y, z, width, height, depth, format, type, memAddress(read));
	}

	@Override
	public void set(int x, int y, int z, int width, int height, int depth, FloatBuffer read) {
		glTexSubImage3D(target, level, x, y, z, width, height, depth, format, type, read);
	}

	@Override
	public void set(int x, int y, int z, int width, int height, int depth, DoubleBuffer read) {
		glTexSubImage3D(target, level, x, y, z, width, height, depth, format, type, read);
	}

}
