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

import sgl.image.raster.Raster2D;
import sgl.opengl.OpenGL;
import sgl.opengl.OpenGL.Feature;
import sgl.opengl.error.UnsupportedFeatureException;
import sgl.opengl.error.UnsupportedProfileException;

import java.nio.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.memAddress;

/**
 * @author link
 */
public class GLTexture2D extends GLTexture implements Raster2D {

	static {
		if (!OpenGL.supports(Feature.EXT_TEXTURE))
			throw new UnsupportedProfileException(GLTexture2D.class, "Unsupported feature", new UnsupportedFeatureException(GLTexture2D.class, Feature.EXT_TEXTURE));
	}

	protected int width, height;

	public GLTexture2D() {
		this(0, 0);
	}

	public GLTexture2D(int width, int height) {
		this(Target.TEXTURE_2D, width, height);
	}

	public GLTexture2D(Target target, int width, int height) {
		this(target, GL_RGBA, width, height);
	}

	public GLTexture2D(Target target, int internalFormat, int width, int height) {
		this(target, internalFormat, width, height, GL_RGBA);
	}

	public GLTexture2D(Target target, int internalFormat, int width, int height, int format) {
		this(target, internalFormat, width, height, format, GL_UNSIGNED_INT);
	}

	public GLTexture2D(Target target, int internalFormat, int width, int height, int format, int type) {
		this(target, 0, internalFormat, width, height, format, type);
	}

	public GLTexture2D(Target target, int level, int internalFormat, int width, int height, int format, int type) {
		this(target, level, internalFormat, width, height, 0, format, type);
	}

	public GLTexture2D(Target target, int level, int internalFormat, int width, int height, int border, int format, int type) {
		this(glGenTextures(), target.glEnumConstant, level, internalFormat, width, height, border, format, type);
	}

	protected GLTexture2D(int texture, int target, int level, int internalFormat, int width, int height, int border, int format, int type) {
		super(texture, target, level, internalFormat, width * height, border, format, type);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void set(int x, int y, int width, int height, ByteBuffer data) {
		glTexSubImage2D(target, level, x, y, width, height, format, type, data);
	}

	@Override
	public void set(int x, int y, int width, int height, ShortBuffer data) {
		glTexSubImage2D(target, level, x, y, width, height, format, type, data);
	}

	@Override
	public void set(int x, int y, int width, int height, IntBuffer data) {
		glTexSubImage2D(target, level, x, y, width, height, format, type, data);
	}

	@Override
	public void set(int x, int y, int width, int height, LongBuffer data) {
		glTexSubImage2D(target, level, x, y, width, height, format, type, memAddress(data));
	}

	@Override
	public void set(int x, int y, int width, int height, FloatBuffer data) {
		glTexSubImage2D(target, level, x, y, width, height, format, type, data);
	}

	@Override
	public void set(int x, int y, int width, int height, DoubleBuffer data) {
		glTexSubImage2D(target, level, x, y, width, height, format, type, data);
	}

}
