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
package sgl.opengl;

/**
 * A GLObject is an object representing a GL data object. This does not
 * represent GL data <em>types</em> (i.e. GLenum, GLuint, etc.).
 *
 * @author link
 */
public interface GLObject {

	/**
	 * Gets the GL name of this object (int). This is used by many different
	 * kinds of GL object types such as textures and buffers. It is primarily
	 * invoked internally, but it does give access to the actual name if
	 * specific GL operations are required for that type of object, although
	 * most operations have their own instance method for each GLObject.
	 *
	 * @return the GL name of this object (int)
	 */
	int glName();

	/**
	 * If applicable, binds this object for immediate use by OpenGL. Used for non-{@linkplain org.lwjgl.opengl.ARBDirectStateAccess DSA}
	 * data types used by OpenGL, such as textures, <abbr title="vertex buffer object">VBO</abbr>s, <abbr title="vertex array object">VAO</abbr>s,
	 * <abbr title="element buffer object">EBO</abbr>s, etc.
	 */
	default void bind() {
	}

}
