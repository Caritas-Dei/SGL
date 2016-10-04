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
 * Created file on 8/27/16 at 10:01 AM.
 *
 * This file is part of XUGL
 */
package sgl.gl.profile;

import sgl.gl.GLBuffer;
import sgl.gl.GLObject;
import sgl.gl.OpenGL;
import sgl.gl.error.GLError;
import sgl.gl.image.GLImage;
import sgl.ui.image.color.Color;

/**
 * @author link
 */
public interface GLProfile {

	/**
	 * OpenGL 1.1 Profile
	 */
	GLProfile OpenGL11 = new GL11Profile();

	/**
	 * OpenGL 1.2 Profile
	 */
	GLProfile OpenGL12 = new GL12Profile();

	/**
	 * OpenGL 1.3 Profile
	 */
	GLProfile OpenGL13 = new GL13Profile();

	/**
	 * OpenGL 1.4 Profile
	 */
	GLProfile OpenGL14 = new GL14Profile();

	/**
	 * OpenGL 1.5 Profile
	 */
	GLProfile OpenGL15 = new GL15Profile();

	/**
	 * OpenGL 2.0 Profile
	 */
	GLProfile OpenGL20 = new GL20Profile();

	/**
	 * OpenGL 2.1 Profile
	 */
	GLProfile OpenGL21 = new GL21Profile();

	/**
	 * OpenGL 3.0 Profile
	 */
	GLProfile OpenGL30 = new GL30Profile();

	/**
	 * OpenGL 3.1 Profile
	 */
	GLProfile OpenGL31 = new GL31Profile();

	/**
	 * OpenGL 3.2 Profile
	 */
	GLProfile OpenGL32 = new GL32Profile();

	/**
	 * OpenGL 3.3 Profile
	 */
	GLProfile OpenGL33 = new GL33Profile();

	/**
	 * OpenGL 4.0 Profile
	 */
	GLProfile OpenGL40 = new GL40Profile();

	/**
	 * OpenGL 4.1 Profile
	 */
	GLProfile OpenGL41 = new GL41Profile();

	/**
	 * OpenGL 4.2 Profile
	 */
	GLProfile OpenGL42 = new GL42Profile();

	/**
	 * OpenGL 4.3 Profile
	 */
	GLProfile OpenGL43 = new GL43Profile();

	/**
	 * OpenGL 4.4 Profile
	 */
	GLProfile OpenGL44 = new GL44Profile();

	/**
	 * OpenGL 4.5 Profile
	 */
	GLProfile OpenGL45 = new GL45Profile();

	/**
	 * The latest available Core OpenGL functionality for this platform
	 */
	GLProfile CORE = forVersion(OpenGL.getVersion());

	/**
	 * The minimum basic OpenGL (1.1), nothing more
	 */
	GLProfile BASE = OpenGL11;

	/**
	 * OpenGL 1.0 to 1.5
	 */
	GLProfile LEGACY_CORE_GL10 = OpenGL15;

	/**
	 * OpenGL 2.0 to 2.1
	 */
	GLProfile LEGACY_CORE_GL20 = OpenGL21;

	/**
	 * OpenGL 3.0 to 3.3
	 */
	GLProfile LEGACY_CORE_GL30 = OpenGL33;

	/**
	 * OpenGL 4.0 to 4.5
	 */
	GLProfile CORE_GL40 = OpenGL45;


	static GLProfile forVersion(int version) {
		switch (version) {
			case 11:
				return OpenGL11;
			case 12:
				return OpenGL12;
			case 13:
				return OpenGL13;
			case 14:
				return OpenGL14;
			case 15:
				return OpenGL15;
			case 20:
				return OpenGL20;
			case 21:
				return OpenGL21;
			case 30:
				return OpenGL30;
			case 31:
				return OpenGL31;
			case 32:
				return OpenGL32;
			case 33:
				return OpenGL33;
			case 40:
				return OpenGL40;
			case 41:
				return OpenGL41;
			case 42:
				return OpenGL42;
			case 43:
				return OpenGL43;
			case 44:
				return OpenGL44;
			case 45:
				return OpenGL45;
			default:
				throw new IllegalArgumentException("The specified OpenGL version does not exist: " + version);
		}
	}

	int version();

	boolean supports(int version);

	boolean supports(OpenGL.Feature feature);

	GLError getError();

	GLImage image(int target, int lod, int type, int width, int height,
	              int borderWidth, int pixelFormat, Color[] pixels);

	GLBuffer buffer(int target, int size, int usage);

	GLPrimitive primitive();

	GLObject object(GLPrimitive[] primitives);

	GLObject object(GLObject[] objects);

}
