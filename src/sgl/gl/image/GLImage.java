/*
 * The MIT License
 *
 * Copyright 2016 link.
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
 * Created file on 5/26/16 at 9:05 PM.
 *
 * This file is part of XUGL
 */
package sgl.gl.image;

import sgl.gl.GLObject;
import sgl.gl.OpenGL;
import sgl.gl.image.color.GLColor;
import sgl.ui.image.Image;
import sgl.ui.image.color.Color;
import sgl.ui.image.color.RGBAColor;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author link
 */
public final class GLImage implements Image, GLObject {

	private final int texture;
	private int width, height, target;

	public GLImage(int target, int lod, int type, int width, int height,
	               int borderWidth, int format, Color[] pixels) {
		texture = glGenTextures();
		this.width = width;
		this.height = height;
		glBindTexture(this.target, texture);
		glTexImage2D(target, lod, type, width, height, borderWidth, format, type, (ByteBuffer) null);
	}


	public GLImage(int target, int width, int height, Image data) {
		texture = glGenTextures();

		glBindTexture(this.target, texture);
		glTexImage2D(target, 0, GL_RGBA, width, height, 0, 0, GL_FLOAT, data.values());
	}

	public GLImage(int target, int texture) {
		this.texture = texture;
		this.target = target;
	}

	/**
	 * Gets the width of this texture in pixels.
	 *
	 * @return the width of this texture in pixels
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of this texture in pixels.
	 *
	 * @param width
	 * 		the new width of this texture in pixels
	 */
	public void setWidth(int width) {
		OpenGL.seti(this, GL_TEXTURE_WIDTH, this.width = width);
	}

	/**
	 * Gets the height of this texture in pixels.
	 *
	 * @return the height of this texture in pixels
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of this texture in pixels.
	 *
	 * @param height
	 * 		the new height of this texture in pixels
	 */
	public void setHeight(int height) {
		OpenGL.seti(this, GL_TEXTURE_HEIGHT, this.height = height);
	}

	@Override
	public int getSize() {
		return getWidth() * getHeight();
	}

	@Override
	public Color get(int index) {
		return new RGBAColor(0f, 0f, 0f, 0f);
	}

	@Override
	public void set(int index, Color color) {
		glBindTexture(target, texture);
		int x = index % width, y = (int) (Math.floor(index / width) + 1);
		glTexSubImage2D(target, 0, x, y, width, height, GL_RGBA, 0, new int[]{color.rgba()});
	}

	@Override
	public GLColor[] get() {
		GLColor[] pixels = new GLColor[width * height];
		return pixels;
	}

	@Override
	public void set(Color[] colors) {

	}

	@Override
	public int[] values() {
		return new int[0];
	}

	@Override
	public void values(int[] values) {

	}

	/**
	 * Gets the texture type, the GL target.
	 *
	 * @return the texture type
	 */
	public int getTarget() {
		return target;
	}

//	// FOLD { // gl-specific texture functions
//	/**
//	 * Gets the base level of this texture.
//	 *
//	 * @return the base level of this texture
//	 */
//	public int getBaseLevel() {
//		return OpenGL.geti(this, GL_TEXTURE_BASE_LEVEL);
//	}
//
//	public void setBaseLevel(int baseLevel) {
//		OpenGL.seti(this, GL_TEXTURE_BASE_LEVEL, baseLevel);
//	}
//
//	public int getBorderColor() {
//		return OpenGL.geti(this, GL_TEXTURE_BORDER_COLOR);
//	}
//
//	public void setBorderColor(int borderColor) {
//		OpenGL.seti(this, GL_TEXTURE_BORDER_COLOR, borderColor);
//	}
//
//	public int getCompareMode() {
//		return OpenGL.geti(this, GL_TEXTURE_COMPARE_MODE);
//	}
//
//	public void setCompareMode(int compareMode) {
//		OpenGL.seti(this, GL_TEXTURE_COMPARE_MODE, compareMode);
//	}
//
//	public int getCompareFunc() {
//		return OpenGL.geti(this, GL_TEXTURE_COMPARE_FUNC);
//	}
//
//	public void setCompareFunc(int func) {
//		OpenGL.seti(this, GL_TEXTURE_COMPARE_FUNC, func);
//	}
//
//	public int getLODBias() {
//		return OpenGL.geti(this, GL_TEXTURE_LOD_BIAS);
//	}
//
//	public void setLODBias(int lodBias) {
//		OpenGL.seti(this, GL_TEXTURE_LOD_BIAS, lodBias);
//	}
//
//	public int getMagFilter() {
//		return OpenGL.geti(this, GL_TEXTURE_MAG_FILTER);
//	}
//
//	public void setMagFilter(int magFilter) {
//		OpenGL.seti(this, GL_TEXTURE_MAG_FILTER, magFilter);
//	}
//
//	public int getMaxLevel() {
//		return OpenGL.geti(this, GL_TEXTURE_MAX_LEVEL);
//	}
//
//	public void setMaxLevel(int maxLevel) {
//		OpenGL.seti(this, GL_TEXTURE_MAX_LEVEL, maxLevel);
//	}
//
//	public int getMaxLOD() {
//		return OpenGL.geti(this, GL_TEXTURE_MAX_LOD);
//	}
//
//	public void setMaxLOD(int maxLOD) {
//		OpenGL.seti(this, GL_TEXTURE_MAX_LOD, maxLOD);
//	}
//
//	public int getMinFilter() {
//		return OpenGL.geti(this, GL_TEXTURE_MIN_FILTER);
//	}
//
//	public void setMinFilter(int minFilter) {
//		OpenGL.seti(this, GL_TEXTURE_MIN_FILTER, minFilter);
//	}
//
//	public int getMinLOD() {
//		return OpenGL.geti(this, GL_TEXTURE_MIN_LOD);
//	}
//
//	public void setMinLOD(int minLOD) {
//		OpenGL.seti(this, GL_TEXTURE_MIN_LOD, minLOD);
//	}
//
//	public int getPriority() {
//		return OpenGL.geti(this, GL_TEXTURE_PRIORITY);
//	}
//
//	public void setPriority(float priority) {
//		OpenGL.setf(this, GL_TEXTURE_PRIORITY, priority);
//	}
//
//	public float getSwizzleR() {
//		return OpenGL.getf(this, GL_TEXTURE_SWIZZLE_R);
//	}
//
//	public void setSwizzleR(float r) {
//		OpenGL.setf(this, GL_TEXTURE_SWIZZLE_R, r);
//	}
//
//	public float getSwizzleG() {
//		return OpenGL.getf(this, GL_TEXTURE_SWIZZLE_G);
//	}
//
//	public void setSwizzleG(float g) {
//		OpenGL.setf(this, GL_TEXTURE_SWIZZLE_G, g);
//	}
//
//	public float getSwizzleB() {
//		return OpenGL.getf(this, GL_TEXTURE_SWIZZLE_B);
//	}
//
//	public float getSwizzleA() {
//		return OpenGL.getf(this, GL_TEXTURE_SWIZZLE_A);
//	}
//
//	public float[] getSwizzleRGBA() {
//		return OpenGL.getfv(this, GL_TEXTURE_SWIZZLE_RGBA);
//	}
//
//	public float getWrapS() {
//		return OpenGL.getf(this, GL_TEXTURE_WRAP_S);
//	}
//
//	public float getWrapT() {
//		return OpenGL.getf(this, GL_TEXTURE_WRAP_T);
//	}
//
//	public float getWrapR() {
//		return OpenGL.getf(this, GL_TEXTURE_WRAP_R);
//	}
//
//	public int getDepthMode() {
//		return OpenGL.geti(this, GL_DEPTH_TEXTURE_MODE);
//	}
//
//	public int getGenerateMipmap() {
//		return OpenGL.geti(this, GL_GENERATE_MIPMAP);
//	}
//
//	public int getImageFormatCompatibilityType() {
//		return OpenGL.geti(this, GL_IMAGE_FORMAT_COMPATIBILITY_TYPE);
//	}
//
//	public int getImmutableFormat() {
//		return OpenGL.geti(this, GL_TEXTURE_IMMUTABLE_FORMAT);
//	}
//
//	public int getImmutableLevels() {
//		return OpenGL.geti(this, GL_TEXTURE_IMMUTABLE_LEVELS);
//	}
//
//	public int getViewMinLevel() {
//		return OpenGL.geti(this, GL_TEXTURE_VIEW_MIN_LEVEL);
//	}
//
//	public int getViewNumLevels() {
//		return OpenGL.geti(this, GL_TEXTURE_VIEW_NUM_LEVELS);
//	}
//
//	public int getViewMinLayer() {
//		return OpenGL.geti(this, GL_TEXTURE_VIEW_MIN_LAYER);
//	}
//
//	public int getViewNumLayers() {
//		return OpenGL.geti(this, GL_TEXTURE_VIEW_NUM_LAYERS);
//	}
//
//	public int getResident() {
//		return OpenGL.geti(this, GL_TEXTURE_RESIDENT);
//	}
//	// }

	@Override
	public int gl_ptr() {
		return texture;
	}

}
