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
 * Created file on 9/18/16 at 7:22 PM.
 *
 * This file is part of XUGL
 */
package sgl.gl.profile;

import sgl.gl.GLBuffer;
import sgl.gl.OpenGL;
import sgl.gl.image.GLImage;
import sgl.ui.image.color.Color;

import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static sgl.gl.OpenGL.Feature.*;

/**
 * @author link
 */
public final class GL11Profile implements GLProfile {

	static final Set<OpenGL.Feature> FEATURES = new HashSet<>();

	static {
		// GL11 extensions
		FEATURES.add(EXT_VERTEX_ARRAY);
		FEATURES.add(EXT_POLYGON_OFFSET);
		FEATURES.add(EXT_BLEND_LOGIC_OP);
		FEATURES.add(EXT_TEXTURE);
		FEATURES.add(EXT_COPY_TEXTURE);
		FEATURES.add(EXT_SUBTEXTURE);
		FEATURES.add(EXT_TEXTURE_OBJECT);

	}

	private GLImage current;

	@Override
	public int version() {
		return 11;
	}

	@Override
	public boolean supports(int version) {
		return version == 11;
	}

	@Override
	public boolean supports(OpenGL.Feature feature) {
		return FEATURES.contains(feature);
	}

	@Override
	public GLImage image(int target, int lod, int type, int width, int height,
	                     int borderWidth, int pixelFormat, Color[] pixels) {
		return new GLImage(target, lod, type, width, height, borderWidth, pixelFormat, pixels);
	}

	public GLImage image(int target, int type, int width, int height,
	                     int borderWidth, int pixelFormat, Color[] pixels) {
		return new GLImage(target, 0, type, width, height, borderWidth, pixelFormat, pixels);
	}

	public GLImage image(int target, int width, int height, int borderWidth,
	                     int pixelFormat, Color[] pixels) {
		return image(target, GL_TEXTURE_2D, width, height, borderWidth, pixelFormat, pixels);
	}

	@Override
	public GLBuffer buffer(int target, int size, int usage) {
		return null;
	}

}
