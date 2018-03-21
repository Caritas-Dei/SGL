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
package sgl.opengl.glsl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author link
 */
public final class GLShader implements Serializable {

	private int version;
	private int shader;
	private final int type;

	// keep the source in main memory (volatile) for non-cached reading of
	// shaders as an optimization. (OpenGL only requires a pointer to a string
	// for reading in. Keeping the source reads/writes volatile also ensures the
	// shader source code is always updated to  all threads that access it at
	// at any given moment, more importantly OpenGL.)
	private volatile String src;

	private StringBuilder source = new StringBuilder();

	public GLShader(int type) {
		this.type = type;
		shader = glCreateShader(type);
	}

	public GLShader(String source, int type) {
		this(type);
		this.src = source;
	}

	public GLShader(File file, int type) {
		this(readSrc(file), type);
	}

	public GLShader(GLShader shader) {
		this.shader = shader.shader;
		this.src = glGetShaderSource(this.shader);
		this.type = glGetShaderi(this.shader, GL_SHADER_TYPE);
	}

	private static String readSrc(File file) {
		try (BufferedReader in = Files.newBufferedReader(file.toPath())) {
			StringBuilder builder = new StringBuilder();
			String next;

			while ((next = in.readLine()) != null) {
				builder.append(next);
			}

			return builder.toString();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets the source String for this GLShader object in it's current state.
	 *
	 * @return the source String for this GLShader object
	 */
	public String getSource() {
		src = source.toString();
		return src;
	}

	/**
	 * Sets the source String for this GLShader object, but does not recompile
	 * the shader.
	 *
	 * @param src a valid GLSL source String
	 */
	public void setSource(String src) {
		this.src = src;
		glShaderSource(shader, src);
	}

	/**
	 * Gets the source version of this shader.
	 * <p>
	 * For more information about the source version of shaders, please refer to
	 * the OpenGL documentation for GLSL.
	 * </p>
	 *
	 * @return the source version of this shader
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the source version of this shader.
	 * <p>
	 * For more information about the source version of shaders, please refer to
	 * the OpenGL documentation for GLSL.
	 * </p>
	 *
	 * @param version the shader source version
	 */
	public void setVersion(int version) {
		this.version = version;
		final String versionLine = "#version " + version + '\n';
		source.replace(0, versionLine.length(), versionLine);
	}

	/**
	 * Appends a given string of valid GLSL source to the end of this shader's
	 * String.
	 *
	 * @param string a valid GLSL source string to append
	 */
	public void append(String string) {
		source.append(string);
	}

	/**
	 * Appends the given line of GLSL shader source to this shader. The newline
	 * character (\n) is appended to the line.
	 *
	 * @param line the GLSL shader source line
	 */
	public void ln(String line) {
		source.append(line).append('\n');
	}

	/**
	 * Uploads the shader source to OpenGL for compilation. The internal source
	 * String is first updated, and then this method calls {@link
	 * org.lwjgl.opengl.GL20#glShaderSource(int, CharSequence)} .
	 */
	public void upload() {
		src = source.toString();
		glShaderSource(shader, src);
	}

	/**
	 * Compiles this shader for use with OpenGL.
	 * <p>
	 * <em>Note: the source must first be uploaded
	 * to
	 * OpenGL with {@link #upload()}. {@link #append(String)} and {@link
	 * #ln(String)} will not update the source of OpenGL; it must be updated
	 * explicitly.</em>
	 * </p>
	 */
	public void compile() {
		glCompileShader(shader);
	}

	public int glRef() {
		return shader;
	}


	/**
	 * Destroys this shader to free up OpenGL resources. Don't forget to call
	 * this when the shader is no longer needed!
	 */
	public void discard() {
		glDeleteShader(shader);
	}

}
