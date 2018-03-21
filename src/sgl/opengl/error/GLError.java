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
package sgl.opengl.error;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL45;

/**
 * @author link
 */
public class GLError extends Error {

	public GLError(Class<?> caller, Name name) {
		super("An OpenGL error occured in [" + caller.getName() + "]: " + name);
	}

	public GLError(Class<?> caller, String description, Name name) {
		super("An OpenGL error occured in [" + caller.getName() + "]: " + description + " (" + name + ")");
	}


	public enum Name {
		// OpenGL 1.1
		/**
		 * code: 0; indicates no error since last call to {@linkplain GL11#glGetError() GetError}
		 */
		GL_NO_ERROR("GL_NO_ERROR", GL11.GL_NO_ERROR),
		/**
		 * code: 1280; indicates an invalid GLenum value for a GL function call
		 */
		GL_INVALID_ENUM("GL_INVALID_ENUM", GL11.GL_INVALID_ENUM),
		/**
		 * code: 1281; indicates an invalid value for a GL function call
		 */
		GL_INVALID_VALUE("GL_INVALID_VALUE", GL11.GL_INVALID_VALUE),
		/**
		 * code: 1282; indicates an invalid operation (invalid constant) for a GL function call
		 */
		GL_INVALID_OPERATION("GL_INVALID_OPERATION", GL11.GL_INVALID_OPERATION),
		/**
		 * code: 1283; indicates a stack overflow
		 */
		GL_STACK_OVERFLOW("GL_STACK_OVERFLOW", GL11.GL_STACK_OVERFLOW),
		/**
		 * code: 1284; indicates a stack underflow
		 */
		GL_STACK_UNDERFLOW("GL_STACK_UNDERFLOW", GL11.GL_STACK_UNDERFLOW),
		/**
		 * code: 1285; indicates that the GPU has run out of graphics memory
		 */
		GL_OUT_OF_MEMORY("GL_OUT_OF_MEMORY", GL11.GL_OUT_OF_MEMORY),

		// OpenGL 3.0
		/**
		 * code: 1286; indicates that an invalid operation was attempted on a framebuffer
		 */
		GL_INVALID_FRAMEBUFFER_OPERATION("GL_INVALID_FRAMEBUFFER_OPERATION", GL30.GL_INVALID_FRAMEBUFFER_OPERATION),

		// OpenGL 4.5

		/**
		 * code: 1287; indicates that the OpenGL context for the current Thread has been lost
		 */
		GL_CONTEXT_LOST("GL_CONTEXT_LOST", GL45.GL_CONTEXT_LOST),

		// for invalid error codes
		/**
		 * code: -1; indicates that SGL is not yet updated to support this OpenGL version (unlikely), or an invalid
		 * error code was given (most likely). This may indicate that the OpenGL implementation is causing undefined
		 * behavior if it is a snapshot OpenGL implementation, or is corrupted and the native library needs to be
		 * replaced.
		 *
		 * @see #getName(int)
		 */
		UNKNOWN("[unknown]", -1);

		private final String name;
		private final int value;

		Name(String name, int value) {
			this.name = name;
			this.value = value;
		}

		/**
		 * Gets the name of the error code that is given. If the error code is invalid, this method returns {@link Name#UNKNOWN UNKNOWN}.
		 * This may be used as a convenient method for getting the enum constant version of a GL error constant.
		 *
		 * @param error
		 * @return
		 */
		public static Name getName(final int error) {
			switch (error) {
				case GL11.GL_NO_ERROR:
					return GL_NO_ERROR;
				case GL11.GL_INVALID_ENUM:
					return GL_INVALID_ENUM;
				case GL11.GL_INVALID_VALUE:
					return GL_INVALID_VALUE;
				case GL11.GL_INVALID_OPERATION:
					return GL_INVALID_OPERATION;
				case GL11.GL_STACK_OVERFLOW:
					return GL_STACK_OVERFLOW;
				case GL11.GL_STACK_UNDERFLOW:
					return GL_STACK_UNDERFLOW;
				case GL11.GL_OUT_OF_MEMORY:
					return GL_OUT_OF_MEMORY;
				case GL30.GL_INVALID_FRAMEBUFFER_OPERATION:
					return GL_INVALID_FRAMEBUFFER_OPERATION;
				case GL45.GL_CONTEXT_LOST:
					return GL_CONTEXT_LOST;
				default:
					return UNKNOWN;
			}
		}

		public String getName() {
			return name;
		}

		public int getValue() {
			return value;
		}
	}

}
