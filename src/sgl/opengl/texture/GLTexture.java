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

import sgl.image.color.Color;
import sgl.image.raster.Raster;
import sgl.opengl.GLObject;
import sgl.opengl.OpenGL;
import sgl.opengl.OpenGL.Feature;
import sgl.opengl.error.UnsupportedFeatureException;
import sgl.opengl.error.UnsupportedProfileException;
import sgl.opengl.image.color.GLColor;

import java.nio.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL21.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL40.GL_PROXY_TEXTURE_CUBE_MAP_ARRAY;
import static org.lwjgl.opengl.GL40.GL_TEXTURE_CUBE_MAP_ARRAY;
import static org.lwjgl.opengl.GL41.GL_RGB565;
import static org.lwjgl.opengl.GL42.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.system.MemoryUtil.*;
import static sgl.opengl.texture.GLTexture.Target.TEXTURE_1D;

/**
 * @author link
 */
public class GLTexture implements GLObject, Raster {

	static {
		if (!OpenGL.supports(Feature.EXT_TEXTURE))
			throw new UnsupportedProfileException(GLTexture.class, "Unsupported feature", new UnsupportedFeatureException(GLTexture.class, Feature.EXT_TEXTURE));
	}

	protected int texture, size;
	protected final int target, level, internalFormat, border, format, type;

	public GLTexture() {
		this(0);
	}

	public GLTexture(int size) {
		this(TEXTURE_1D, size);
	}

	public GLTexture(Target target, int size) {
		this(target, GL_FLOAT, size);
	}

	public GLTexture(Target target, int internalFormat, int size) {
		this(target, internalFormat, size, GL_RGBA8);
	}

	public GLTexture(Target target, int internalFormat, int size, int format) {
		this(target, internalFormat, size, format, GL_FLOAT);
	}

	public GLTexture(Target target, int internalFormat, int size, int format, int type) {
		this(target, 0, internalFormat, size, format, type);
	}

	public GLTexture(Target target, int level, int internalFormat, int size, int format, int type) {
		this(target, level, internalFormat, size, 0, format, type);
	}

	public GLTexture(Target target, int level, int internalFormat, int size, int border, int format, int type) {
		this(glGenTextures(), target.glEnumConstant, level, internalFormat, size, border, format, type);
	}

	protected GLTexture(int texture, int target, int level, int internalFormat, int size, int border, int format, int type) {
		this.texture = texture;
		this.target = target;
		this.level = level;
		this.internalFormat = internalFormat;
		this.size = size;
		this.border = border;
		this.format = format;
		this.type = type;
	}


	@Override
	public final int glName() {
		return texture;
	}

	@Override
	public final int getSize() {
		return size;
	}

	@Override
	public void setSize(int size) {
		this.size = size;
		glTexImage1D(target, level, internalFormat, this.size, border, format, type, NULL);
	}

	@Override
	public void bind() {
		glBindTexture(target, texture);
	}

	public final int getTarget() {
		return target;
	}

	public final int getLevel() {
		return level;
	}

	public final int getInternalFormat() {
		return internalFormat;
	}

	public final int getBorder() {
		return border;
	}

	public final int getFormat() {
		return format;
	}

	public final int getType() {
		return type;
	}

	@Override
	public void get(int offset, int size, ByteBuffer dataWrite) {
		final ByteBuffer temp = memAlloc(size);
		glGetTexImage(texture, level, format, type, temp);
		temp.position(offset);
		final ByteBuffer slice = temp.slice();
		dataWrite.put(slice);
		memFree(temp);
	}

	@Override
	public void set(int offset, int size, ByteBuffer data) {
		glTexSubImage1D(target, level, offset, size, format, type, data);
	}

	@Override
	public void get(int offset, int size, ShortBuffer dataWrite) {
		final ShortBuffer temp = memAllocShort(2 * size);
		glGetTexImage(texture, level, format, type, temp);
		temp.position(offset);
		final ShortBuffer slice = temp.slice();
		dataWrite.put(slice);
		memFree(temp);
	}

	@Override
	public void set(int offset, int size, ShortBuffer data) {
		glTexSubImage1D(target, level, offset, size, format, type, data);
	}

	@Override
	public void get(int offset, int size, IntBuffer dataWrite) {
		final IntBuffer temp = memAllocInt(4 * size);
		glGetTexImage(texture, level, format, type, temp);
		temp.position(offset);
		final IntBuffer slice = temp.slice();
		dataWrite.put(slice);
		memFree(temp);
	}

	@Override
	public void set(int offset, int size, IntBuffer data) {
		glTexSubImage1D(target, level, offset, size, format, type, data);
	}

	@Override
	public void get(int offset, int size, LongBuffer dataWrite) {
		final ByteBuffer temp = memAlloc(8 * size);
		glGetTexImage(texture, level, format, type, temp);
		temp.position(offset);
		final LongBuffer slice = temp.slice().asLongBuffer();
		dataWrite.put(slice);
		memFree(temp);
	}

	@Override
	public void set(int offset, int size, LongBuffer data) {
		glTexSubImage1D(target, level, offset, size, format, type, memAddress(data));
	}

	@Override
	public void get(int offset, int size, FloatBuffer dataWrite) {
		final FloatBuffer temp = memAllocFloat(4 * size);
		glGetTexImage(texture, level, format, type, temp);
		temp.position(offset);
		final FloatBuffer slice = temp.slice();
		dataWrite.put(slice);
		memFree(temp);
	}

	@Override
	public void set(int offset, int size, FloatBuffer data) {
		glTexSubImage1D(target, level, offset, size, format, type, data);
	}

	@Override
	public void get(int offset, int size, DoubleBuffer dataWrite) {
		final DoubleBuffer temp = memAllocDouble(8 * size);
		glGetTexImage(texture, level, format, type, temp);
		temp.position(offset);
		final DoubleBuffer slice = temp.slice();
		dataWrite.put(slice);
		memFree(temp);
	}

	@Override
	public void set(int offset, int size, DoubleBuffer data) {
		glTexSubImage1D(target, level, offset, size, format, type, data);
	}

	// [type-specific methods]

	@Override
	public void get(int offset, int size, Color[] colors) {
		if (colors.length >= size) switch (type) {
			case GL_INT:
			case GL_UNSIGNED_INT:
				getInt(offset, size, colors);
				return;
			case GL_BYTE:
			case GL_UNSIGNED_BYTE:
				getByte(offset, size, colors);
				return;
			case GL_SHORT:
			case GL_UNSIGNED_SHORT:
				getShort(offset, size, colors);
				return;
			case GL_FLOAT:
				getFloat(offset, size, colors);
				return;
			case GL_DOUBLE:
				getDouble(offset, size, colors);
				return;
			default:
				if (format == GL_RGBA16) getLong(offset, size, colors);

		}
	}


	private void getByte(int offset, int size, Color[] colors) {
		ByteBuffer data = memAlloc(size);
		get(offset, size, data);

		data.position(0);
		int pos = data.position();
		while (data.remaining() < size) {
			float r = data.get() / 255, g = data.get() / 255, b = data.get() / 255, a = data.get() / 255;
			colors[pos] = new GLColor(r, g, b, a);
			pos++;
		}
	}

	private void getShort(int offset, int size, Color[] colors) {

		ShortBuffer data = memAllocShort(size);
		get(offset, size, data);

		data.position(0);
		int pos = data.position();
		while (data.remaining() < size) {
			float r = data.get() / 255, g = data.get() / 255, b = data.get() / 255, a = data.get() / 255;
			colors[pos] = new GLColor(r, g, b, a);
			pos++;
		}
	}

	private void getInt(int offset, int size, Color[] colors) {

		IntBuffer data = memAllocInt(size);
		get(offset, size, data);

		data.position(0);
		int pos = data.position();
		while (data.remaining() < size) {
			float r = data.get() / 255, g = data.get() / 255, b = data.get() / 255, a = data.get() / 255;
			colors[pos] = new GLColor(r, g, b, a);
			pos++;
		}
	}

	private void getLong(int offset, int size, Color[] colors) {

		LongBuffer data = memAllocLong(size);
		get(offset, size, data);

		data.position(0);
		int pos = data.position();
		while (data.remaining() < size) {
			float r = data.get() / 255, g = data.get() / 255, b = data.get() / 255, a = data.get() / 255;
			colors[pos] = new GLColor(r, g, b, a);
			pos++;
		}
	}

	private void getFloat(int offset, int size, Color[] colors) {

		FloatBuffer data = memAllocFloat(size);
		get(offset, size, data);

		data.position(0);
		int pos = data.position();
		while (data.remaining() < size) {
			float r = data.get(), g = data.get(), b = data.get(), a = data.get();
			colors[pos] = new GLColor(r, g, b, a);
			pos++;
		}
	}

	private void getDouble(int offset, int size, Color[] colors) {

		DoubleBuffer data = memAllocDouble(size);
		get(offset, size, data);

		data.position(0);
		int pos = data.position();
		while (data.remaining() < size) {
			float r = (float) data.get(), g = (float) data.get(), b = (float) data.get(), a = (float) data.get();
			colors[pos] = new GLColor(r, g, b, a);
			pos++;
		}
	}


	@Override
	public void set(int offset, int size, Color[] colors) {
		if (colors.length >= size) switch (type) {
			case GL_UNSIGNED_INT:
				setInt(offset, size, colors);
			case GL_UNSIGNED_BYTE:
				setByte(offset, size, colors);
			case GL_UNSIGNED_SHORT:
				setShort(offset, size, colors);
			case GL_FLOAT:
				setFloat(offset, size, colors);
			case GL_DOUBLE:
				setDouble(offset, size, colors);
			default:
				if (format == GL_RGBA16) setLong(offset, size, colors);

		}
	}


	private void setByte(int offset, int size, Color[] colors) {

		ByteBuffer data = memAlloc(size * 4);

		for (Color color : colors) {
			data.put((byte) (color.red() * (byte) 255));
			data.put((byte) (color.green() * (byte) 255));
			data.put((byte) (color.blue() * (byte) 255));
			data.put((byte) (color.alpha() * (byte) 255));
		}

		glTexSubImage1D(target, level, offset, this.size, format, type, data);
	}

	private void setShort(int offset, int size, Color[] colors) {

		ShortBuffer data = memAllocShort(size * 4);

		for (Color color : colors) {
			data.put((short) (color.red() * (byte) 255));
			data.put((short) (color.green() * (byte) 255));
			data.put((short) (color.blue() * (byte) 255));
			data.put((short) (color.alpha() * (byte) 255));
		}

		glTexSubImage1D(target, level, offset, this.size, format, type, data);
	}

	private void setInt(int offset, int size, Color[] colors) {

		IntBuffer data = memAllocInt(size);

		for (Color color : colors) {
			data.put(color.rgba());
		}

		glTexSubImage1D(target, level, offset, this.size, format, type, data);
	}

	private void setLong(int offset, int size, Color[] colors) {

		LongBuffer data = memAllocLong(size);

		for (Color color : colors) {
			data.put((long) (color.red() * (byte) 255));
		}

		glTexSubImage1D(target, level, offset, this.size, format, type, memAddress(data));
	}

	private void setFloat(int offset, int size, Color[] colors) {

		ByteBuffer data = memAlloc(size * 4);

		for (Color color : colors) {
			data.put((byte) (color.red() * (byte) 255));
			data.put((byte) (color.green() * (byte) 255));
			data.put((byte) (color.blue() * (byte) 255));
			data.put((byte) (color.alpha() * (byte) 255));
		}

		glTexSubImage1D(target, level, offset, this.size, format, type, data);
	}

	private void setDouble(int offset, int size, Color[] colors) {

		ByteBuffer data = memAlloc(size * 4);

		for (Color color : colors) {
			data.put((byte) (color.red() * (byte) 255));
			data.put((byte) (color.green() * (byte) 255));
			data.put((byte) (color.blue() * (byte) 255));
			data.put((byte) (color.alpha() * (byte) 255));
		}

		glTexSubImage1D(target, level, offset, this.size, format, type, data);
	}

	// [/type-specific methods]

	// GL-specific texture methods

	public int getBaseLevel() {
		return glGetTexParameteri(target, GL_TEXTURE_BASE_LEVEL);
	}

	public void setBaseLevel(int baseLevel) {
		glTexParameteri(target, GL_TEXTURE_BASE_LEVEL, baseLevel);
	}

	public int getBorderColori() {
		return glGetTexParameteri(target, GL_TEXTURE_BORDER_COLOR);
	}

	public void setBorderColori(int borderColor) {
		glTexParameteri(target, GL_TEXTURE_BORDER_COLOR, borderColor);
	}

	public int getCompareMode() {
		return glGetTexParameteri(target, GL_TEXTURE_COMPARE_MODE);
	}

	public void setCompareMode(int compareMode) {
		glTexParameteri(target, GL_TEXTURE_COMPARE_MODE, compareMode);
	}

	public int getCompareFunc() {
		return glGetTexParameteri(target, GL_TEXTURE_COMPARE_FUNC);
	}

	public void setCompareFunc(int compareFunc) {
		glTexParameteri(target, GL_TEXTURE_COMPARE_FUNC, compareFunc);
	}

	public int getLODBias() {
		return glGetTexParameteri(target, GL_TEXTURE_LOD_BIAS);
	}

	public void setLODBias(int lodBias) {
		glTexParameteri(target, GL_TEXTURE_LOD_BIAS, lodBias);
	}

	public int getMagFilter() {
		return glGetTexParameteri(target, GL_TEXTURE_MAG_FILTER);
	}

	public void setMagFilter(int magFilter) {
		glTexParameteri(target, GL_TEXTURE_MAG_FILTER, magFilter);
	}

	public int getMaxLevel() {
		return glGetTexParameteri(target, GL_TEXTURE_MAX_LEVEL);
	}

	public void setMaxLevel(int maxLevel) {
		glTexParameteri(target, GL_TEXTURE_MAX_LEVEL, maxLevel);
	}

	public int getMaxLOD() {
		return glGetTexParameteri(target, GL_TEXTURE_MAX_LOD);
	}

	public void setMaxLOD(int lod) {
		glTexParameteri(target, GL_TEXTURE_MAX_LOD, lod);
	}

	public int getMinFilter() {
		return glGetTexParameteri(target, GL_TEXTURE_MIN_FILTER);
	}

	public void setMinFilter(int minFilter) {
		glTexParameteri(target, GL_TEXTURE_MIN_FILTER, minFilter);
	}

	public int getMinLOD() {
		return glGetTexParameteri(target, GL_TEXTURE_MIN_LOD);
	}

	public void setMinLOD(int lod) {
		glTexParameteri(target, GL_TEXTURE_MIN_LOD, lod);
	}

	public int getPriority() {
		return glGetTexParameteri(target, GL_TEXTURE_PRIORITY);
	}

	public void setPriority(int priority) {
		glTexParameteri(target, GL_TEXTURE_PRIORITY, priority);
	}

	public float getSwizzleR() {
		return glGetTexParameterf(target, GL_TEXTURE_SWIZZLE_R);
	}

	public void setSwizzleR(float swizzleR) {
		glTexParameterf(target, GL_TEXTURE_SWIZZLE_R, swizzleR);
	}

	public float getSwizzleG() {
		return glGetTexParameterf(target, GL_TEXTURE_SWIZZLE_G);
	}

	public void setSwizzleG(float swizzleG) {
		glTexParameterf(target, GL_TEXTURE_SWIZZLE_G, swizzleG);
	}

	public float getSwizzleB() {
		return glGetTexParameterf(target, GL_TEXTURE_SWIZZLE_B);
	}

	public void setSwizzleB(float swizzleB) {
		glTexParameterf(target, GL_TEXTURE_SWIZZLE_B, swizzleB);
	}

	public float getSwizzleA() {
		return glGetTexParameterf(target, GL_TEXTURE_SWIZZLE_A);
	}

	public void setSwizzleA(int swizzleA) {
		glTexParameterf(target, GL_TEXTURE_SWIZZLE_A, swizzleA);
	}

	public float[] getSwizzleRGBA() {
		float[] swizzle = new float[4];
		glGetTexParameterfv(target, GL_TEXTURE_SWIZZLE_RGBA, swizzle);
		return swizzle;
	}

	public void setSwizzleRGBA(float[] rgba) {
		glTexParameterfv(target, GL_TEXTURE_SWIZZLE_RGBA, rgba);
	}

	public void setSwizzleRGBA(float r, float g, float b, float a) {
		setSwizzleRGBA(new float[]{r, g, b, a});
	}

	public int getWrapS() {
		return glGetTexParameteri(target, GL_TEXTURE_WRAP_S);
	}

	public void setWrapS(int wrapS) {
		glTexParameteri(target, GL_TEXTURE_WRAP_S, wrapS);
	}

	public int getWrapT() {
		return glGetTexParameteri(target, GL_TEXTURE_WRAP_T);
	}

	public void setWrapT(int wrapT) {
		glTexParameteri(target, GL_TEXTURE_WRAP_T, wrapT);
	}

	public int getWrapR() {
		return glGetTexParameteri(target, GL_TEXTURE_WRAP_R);
	}

	public void setWrapR(int wrapR) {
		glTexParameteri(target, GL_TEXTURE_WRAP_R, wrapR);
	}

	public int getDepthMode() {
		return glGetTexParameteri(target, GL_DEPTH_TEXTURE_MODE);
	}

	public void setDepthMode(int depthMode) {
		glTexParameteri(target, GL_DEPTH_TEXTURE_MODE, depthMode);
	}


	/* END GL-specific texture methods */


	public enum Target {


		// actual GL glEnumConstant names
		TEXTURE_1D(GL_TEXTURE_1D), PROXY_TEXTURE_1D(GL_PROXY_TEXTURE_1D), TEXTURE_2D(GL_TEXTURE_2D), PROXY_TEXTURE_2D(GL_PROXY_TEXTURE_2D), TEXTURE_1D_ARRAY(GL_TEXTURE_1D_ARRAY), PROXY_TEXTURE_1D_ARRAY(GL_PROXY_TEXTURE_1D_ARRAY), TEXTURE_RECTANGLE(GL_TEXTURE_RECTANGLE), PROXY_TEXTURE_RECTANGLE(GL_PROXY_TEXTURE_RECTANGLE), TEXTURE_CUBE_MAP(GL_TEXTURE_CUBE_MAP), PROXY_TEXTURE_CUBE_MAP(GL_PROXY_TEXTURE_CUBE_MAP), TEXTURE_3D(GL_TEXTURE_3D), PROXY_TEXTURE_3D(GL_PROXY_TEXTURE_3D), TEXTURE_2D_ARRAY(GL_TEXTURE_2D_ARRAY), PROXY_TEXTURE_2D_ARRAY(GL_PROXY_TEXTURE_2D_ARRAY), TEXTURE_CUBE_MAP_ARRAY(GL_TEXTURE_CUBE_MAP_ARRAY), PROXY_TEXTURE_CUBE_MAP_ARRAY(GL_PROXY_TEXTURE_CUBE_MAP_ARRAY),;

		public static final Target DEFAULT = TEXTURE_2D;

		// access to GLTexture inheritors
		protected final int glEnumConstant;

		Target(int target) {
			this.glEnumConstant = target;
		}

		public final int getGLEnumConstant() {
			return glEnumConstant;
		}
	}

	/**
	 * Represents the internalFormat of a GLTexture.
	 * <p>
	 * This enum value represents what a texel's bit format is. This is how
	 * OpenGL knows how to handle your data. If you use the wrong
	 * internalFormat, the texels may render with incorrect colors.
	 * </p>
	 * <p>
	 * The most common internalFormat is RGBA32UI.
	 * </p>
	 */
	public enum InternalFormat {
		/**
		 * defines as R
		 */
		RED(GL_RED), /**
		 * defines as RG
		 */
		RG(GL_RG), /**
		 * defines as RGB
		 */
		RGB(GL_RGB), /**
		 * defines as R 8-bit
		 */
		R8(GL_R8), /**
		 * defines as R 8-bit with s-norm format
		 */
		R8_SNORM(GL_R8_SNORM), /**
		 * defines as R 16-bit
		 */
		R16(GL_R16), /**
		 * defines as RG 16-bits (8-bits per color) with s-norm format
		 */
		RG16(GL_RG16), /**
		 * defines as RG 16-bits (8-bits per color) with s-norm format
		 */
		RG16_SNORM(GL_RG16_SNORM), /**
		 * defines as byte with red 3-bit, green 3-bit, and blue 2-bit
		 */
		R3_G3_B2(GL_R3_G3_B2), /**
		 * defines as RGB 8-bits per color
		 */
		RGB8(GL_RGB8), /**
		 * defines as RGB 8-bits per color with s-norm format
		 */
		RGB8_SNORM(GL_RGB8_SNORM), /**
		 * defines as RGB 10-bit
		 */
		RGB10(GL_RGB10), /**
		 * defines as RGBA 32-bit (8-bits per color, 2-bit alpha)
		 */
		RGBA2(GL_RGBA2), /**
		 * defines as RGBA 32-bit (8-bits per color, 4-bit alpha)
		 */
		RGBA4(GL_RGBA4), /**
		 * defines as RGBA 16-bit (5-bits per color, 1-bit alpha)
		 */
		RGB5_A1(GL_RGB5_A1), /**
		 * defines as RGBA 32-bit, unsigned (10-bits per color, 2-bit alpha)
		 */
		RGB10_A2UI(GL_RGB10_A2), /**
		 * defines as RGBA 64-bit (12-bits per color, 12-bit alpha)
		 */
		RGBA12(GL_RGBA12), /**
		 * defines as RGBA 64-bit (16-bits per color, 16-bit alpha)
		 */
		RGBA16(GL_RGBA16), /**
		 * defines as R 16-bit floating-point
		 */
		R16F(GL_R16F), /**
		 * defines as RG 16-bit floating-point
		 */
		RG16F(GL_RG16F), /**
		 * defines as RGB 16-bit floating-point
		 */
		RGB16F(GL_RGB16F), /**
		 * defines as RGB 32-bit floating-point
		 */
		RGB32F(GL_RGB32F), /**
		 * defines as RGBA 32-bit floating-point
		 */
		RGBA32F(GL_RGBA32F), /**
		 * defines as RGB 32-bit (11-bit red, 11-bit green, 10-bit blue)
		 * floating-point
		 */
		R11F_G11F_B10F(GL_R11F_G11F_B10F), /**
		 * defines as R 16-bit integer
		 */
		R16I(GL_R16I), /**
		 * defines as R 16-bit unsigned integer
		 */
		R16UI(GL_R16UI), /**
		 * defines as R 32-bit integer
		 */
		R32I(GL_R32I), /**
		 * defines as RG 16-bit integer
		 */
		RG16I(GL_RG16I), /**
		 * defines as RG 16-bit unsigned integer
		 */
		RG16UI(GL_RG16UI), /**
		 * defines as RG 32-bit integer
		 */
		RG32I(GL_RG32I), /**
		 * defines as RGB 16-bit integer
		 */
		RGB16I(GL_RGB16I), /**
		 * defines as RGB 16-bit unsigned integer
		 */
		RGB16UI(GL_RGB16UI), /**
		 * defines as RGB 32-bit integer
		 */
		RGB32I(GL_RGB32I), /**
		 * defines as RGBA 16-bit integer
		 */
		RGBA16I(GL_RGBA16I), /**
		 * defines as RGBA 16-bit unsigned integer
		 */
		RGBA16UI(GL_RGBA16UI), /**
		 * defines as RGBA 32-bit integer
		 */
		RGBA32I(GL_RGBA32I), /**
		 * defines as gl_depth_component 32-bit
		 */
		DEPTH_COMPONENT32(GL_DEPTH_COMPONENT32), /**
		 * defines as gl_depth24_stencil8 32-bit (24-bit depth, 8-bit stencil)
		 */
		DEPTH24_STENCIL8(GL_DEPTH24_STENCIL8), /**
		 * defines as gl_depth_component32f (32-bit float)
		 */
		DEPTH_COMPONENT32F(GL_DEPTH_COMPONENT32F), /**
		 * defines as compressed RGB
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RGB(GL_COMPRESSED_RGB), /**
		 * defines as compressed RGBA
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RGBA(GL_COMPRESSED_RGBA), /**
		 * defines as compressed sRGB
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SRGB(GL_COMPRESSED_SRGB), /**
		 * defines as compressed RG-RGTC2
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RG_RGTC2(GL_COMPRESSED_RG_RGTC2), /**
		 * defines as compressed signed RG-RGTC2
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SIGNED_RG_RGTC2(GL_COMPRESSED_SIGNED_RG_RGTC2), /**
		 * defines as compressed RGBA-BPTC-UNORM
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RGBA_BPTC_UNORM(GL_COMPRESSED_RGBA_BPTC_UNORM), /**
		 * defines as compressed RGB-ETC2 (8-bits per color)
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RGB8_ETC2(GL_COMPRESSED_RGB8_ETC2), /**
		 * defines as compressed SRGB-ETC2 (8-bits per color)
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SRGB8_ETC2(GL_COMPRESSED_SRGB8_ETC2), /**
		 * defines as compressed RGB-PUNCHTHROUGH-ALPHA-ETC2 (8-bits per color,
		 * 1-bit alpha)
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RGB8_PUNCHTRHOUGH_ALPHA1_ETC2(GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2), /**
		 * defines as compressed R-EAC (11-bits red)
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_R11_EAC(GL_COMPRESSED_R11_EAC), /**
		 * defines as compressed signed R-EAC (11-bits red)
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SIGNED_R11_EAC(GL_COMPRESSED_SIGNED_R11_EAC), /**
		 * defines as compressed RG-EAC (11-bits per color)
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RG11_EAC(GL_COMPRESSED_RG11_EAC), /**
		 * defines as R 16-bit with s-norm format
		 */
		R16_SNORM(GL_R16_SNORM), /**
		 * defines as RG 16-bit (8-bits per color)
		 */
		RG8(GL_RG8), /**
		 * defines as RG 16-bit (8-bits per color) with s-norm format
		 */
		RG8_SNORM(GL_RG8_SNORM), /**
		 * defines as RGB 16-bit (4-bits per color)
		 */
		RGB4(GL_RGB4), /**
		 * defines as RGB 16-bit (5-bits per color)
		 */
		RGB5(GL_RGB5), /**
		 * defines as RGB 16-bit (5-bits red, 6-bits green, 5-bits blue)
		 */
		RGB565(GL_RGB565), /**
		 * defines as RGB 16-bit (4-bits per color)
		 */
		RGB12(GL_RGB12), /**
		 * defines as RGB 16-bit (5-bits per color)
		 */
		RGB16(GL_RGB16), /**
		 * defines as RGB 16-bit (5-bits per color) with s-norm format
		 */
		RGB16_SNORM(GL_RGB16_SNORM), /**
		 * defines as RGBA 32-bit (8-bits per color, 8-bit alpha)
		 */
		RGBA8(GL_RGBA8), /**
		 * defines as RGBA 32-bit (8-bits per color, 8-bit alpha) with s-norm
		 * format
		 */
		RGBA8_SNORM(GL_RGBA8_SNORM), /**
		 * defines as RGB 32-bit (10-bits per color, 2-bit alpha)
		 */
		RGB10_A2(GL_RGB10_A2), /**
		 * defines as RGBA 16-bit (4-bits per color, 4-bit alpha) with s-norm
		 * format
		 */
		RGBA16_SNORM(GL_RGBA16_SNORM), /**
		 * defines as SRGB 32-bit (8-bits per color)
		 */
		SRGB8(GL_SRGB8), /**
		 * defines as SRGB 32-bit (8-bits per color, 8-bit alpha)
		 */
		SRGB8_ALPHA8(GL_SRGB8_ALPHA8), /**
		 * defines as RGBA 16-bit floating-point
		 */
		RGBA16F(GL_RGBA16F), /**
		 * defines as R 32-bit floating-point
		 */
		R32F(GL_R32F), /**
		 * defines as RG 32-bit floating-point
		 */
		RG32F(GL_RG32F), /**
		 * defines as RGBE 32-bit (9-bits per color, 5-bits E component)
		 * FIXME what is E component?
		 */
		RGB9_E5(GL_RGB9_E5), /**
		 * defines as R 8-bit integer
		 */
		R8I(GL_R8I), /**
		 * defines as R 8-bit unsigned integer
		 */
		R8UI(GL_R8UI), /**
		 * defines as R 32-bit unsigned integer
		 */
		R32UI(GL_R32UI), /**
		 * defines as RG 16-bit integer (8-bits per color)
		 */
		RG8I(GL_RG8I), /**
		 * defines as RG 16-bit unsigned integer (8-bits per color)
		 */
		RG8UI(GL_RG8UI), /**
		 * defines as RG 32-bit unsigned integer (16-bits per color)
		 */
		RG32UI(GL_RG32UI), /**
		 * defines as RGB 32-bit integer (8-bits per color)
		 */
		RGB8I(GL_RGB8I), /**
		 * defines as RGB
		 */
		RGB8UI(GL_RGB8UI), /**
		 * defines as RGB 32-bit unsigned integer (8-bits per color)
		 */
		RGB32UI(GL_RGB32UI), /**
		 * defines as RGBA 32-bit integer (8-bits per color, 8-bit alpha)
		 */
		RGBA8I(GL_RGBA8I), /**
		 * defines as RGBA 32-bit unsigned integer (8-bits per color, 8-bit
		 * alpha)
		 */
		RGBA8UI(GL_RGBA8UI), /**
		 * defines as RGBA 32-bit unsigned integer
		 */
		RGBA32UI(GL_RGBA32UI), /**
		 * defines as gl_depth_component (16-bit)
		 */
		DEPTH_COMPONENT16(GL_DEPTH_COMPONENT16), /**
		 * defines as gl_depth_component (24-bit)
		 */
		DEPTH_COMPONENT24(GL_DEPTH_COMPONENT24), /**
		 * defines as gl_depth32f_stencil8 (32-bit floating-point depth, 8-bit
		 * stencil)
		 */
		DEPTH32F_STENCIL8(GL_DEPTH32F_STENCIL8), /**
		 * defines as compressed RED
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RED(GL_COMPRESSED_RED), /**
		 * defines as compressed RG
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RG(GL_COMPRESSED_RG), /**
		 * defines as compressed SRGB with alpha
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SRGB_ALPHA(GL_COMPRESSED_SRGB_ALPHA), /**
		 * defines as compressed RED-RGTC1
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RED_RGTC1(GL_COMPRESSED_RED_RGTC1), /**
		 * defines as compressed signed RED-RGTC1
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SIGNED_RED_RGTC1(GL_COMPRESSED_SIGNED_RED_RGTC1), /**
		 * defines as compressed SRGB-BPTC-UNORM with alpha
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SRGB_ALPHA_BPTC_UNORM(GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM), /**
		 * defines as compressed RGB-BPTC (signed float)
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RGB_BPTC_SIGNED_FLOAT(GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT), /**
		 * defines as compressed RGB-BPTC (unsigned float)
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT(GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT), /**
		 * defines as compressed SRGB8-PUNCHTHROUGH-ALPHA1-ETC2
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2(GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2), /**
		 * defines as compressed RGBA8-ETC2-EAC
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_RGBA8_ETC2_EAC(GL_COMPRESSED_RGBA8_ETC2_EAC), /**
		 * defines as compressed SRGB8-ALPHA8-ETC2-EAC
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SRGB8_ALPHA8_ETC2_EAC(GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC), /**
		 * defines as compressed signed RG11-EAC
		 *
		 * @see org.lwjgl.opengl.EXTTextureCompressionLATC
		 * @see org.lwjgl.opengl.EXTTextureCompressionRGTC
		 * @see org.lwjgl.opengl.EXTTextureCompressionS3TC
		 * @see org.lwjgl.opengl.ATITextureCompression3DC
		 */
		COMPRESSED_SIGNED_RG11_EAC(GL_COMPRESSED_SIGNED_RG11_EAC);

		private final int glEnumConstant;

		InternalFormat(int internalFormat) {
			glEnumConstant = internalFormat;
		}

		public final int getGLEnumConstant() {
			return glEnumConstant;
		}
	}

	public enum Format {
		STENCIL_INDEX(GL_STENCIL_INDEX), DEPTH_COMPONENT(GL_DEPTH_COMPONENT), DEPTH_STENCIL(GL_DEPTH_STENCIL), RED(GL_RED), GREEN(GL_GREEN), BLUE(GL_BLUE), ALPHA(GL_ALPHA), RG(GL_RG), RGB(GL_RGB), RGBA(GL_RGBA), BGR(GL_BGR), BGRA(GL_BGRA), LUMINANCE(GL_LUMINANCE), LUMINANCE_ALPHA(GL_LUMINANCE_ALPHA), RED_INTEGER(GL_RED_INTEGER), GREEN_INTEGER(GL_GREEN_INTEGER), BLUE_INTEGER(GL_BLUE_INTEGER), ALPHA_INTEGER(GL_ALPHA_INTEGER), RG_INTEGER(GL_RG_INTEGER), RGB_INTEGER(GL_RGB_INTEGER), RGBA_INTEGER(GL_RGBA_INTEGER), BGR_INTEGER(GL_BGR_INTEGER), BGRA_INTEGER(GL_BGRA_INTEGER);

		private final int glEnumConstant;

		Format(int type) {
			glEnumConstant = type;
		}

		public final int getGLEnumConstant() {
			return glEnumConstant;
		}

	}

	public enum Type {
		UNSIGNED_BYTE(GL_UNSIGNED_BYTE), BYTE(GL_BYTE), UNSIGNED_SHORT(GL_UNSIGNED_SHORT), SHORT(GL_SHORT), UNSIGNED_INT(GL_UNSIGNED_INT), INT(GL_INT), HALF_FLOAT(GL_HALF_FLOAT), FLOAT(GL_FLOAT), UNSIGNED_BYTE_3_3_2(GL_UNSIGNED_BYTE_3_3_2), UNSIGNED_BYTE_2_3_3_REV(GL_UNSIGNED_BYTE_2_3_3_REV), UNSIGNED_SHORT_5_6_5(GL_UNSIGNED_SHORT_5_6_5), UNSIGNED_SHORT_5_6_5_REV(GL_UNSIGNED_SHORT_5_6_5_REV), UNSIGNED_SHORT_4_4_4_4(GL_UNSIGNED_SHORT_4_4_4_4), UNSIGNED_SHORT_4_4_4_4_REV(GL_UNSIGNED_SHORT_4_4_4_4_REV), UNSIGNED_SHORT_5_5_5_1(GL_UNSIGNED_SHORT_5_5_5_1), UNSIGNED_SHORT_1_5_5_5_REV(GL_UNSIGNED_SHORT_1_5_5_5_REV), UNSIGNED_INT_8_8_8_8(GL_UNSIGNED_INT_8_8_8_8), UNSIGNED_INT_8_8_8_8_REV(GL_UNSIGNED_INT_8_8_8_8_REV), UNSIGNED_INT_10_10_10_2(GL_UNSIGNED_INT_10_10_10_2), UNSIGNED_INT_2_10_10_10_REV(GL_UNSIGNED_INT_2_10_10_10_REV), UNSIGNED_INT_24_8(GL_UNSIGNED_INT_24_8), UNSIGNED_INT_10F_11F_11F_REV(GL_UNSIGNED_INT_10F_11F_11F_REV), UNSIGNED_INT_5_9_9_9_REV(GL_UNSIGNED_INT_5_9_9_9_REV), FLOAT_32_UNSIGNED_INT_24_8_REV(GL_FLOAT_32_UNSIGNED_INT_24_8_REV), BITMAP(GL_BITMAP);

		private final int glEnumConstant;

		static {
			glTexImage2D(0, 0, 0, 0, 0, 0, 0, 0, NULL);
		}

		Type(int format) {
			glEnumConstant = format;
		}

		public final int getGLEnumConstant() {
			return glEnumConstant;
		}
	}

}
