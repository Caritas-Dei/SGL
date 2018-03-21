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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author link
 * <p>
 * TODO should this class implement Serializable for network transport?
 */
public final class GLProgram implements AutoCloseable {

	private int program;
	private final Map<String, Integer> uniforms = new HashMap<>();

	public GLProgram(GLShader... shaders) {
		program = glCreateProgram();
		if (shaders != null && shaders.length > 0) attach(shaders);
	}

	/**
	 * Attaches the given shader to this program object.
	 *
	 * @param shader the shader object to attach
	 */
	public void attach(GLShader shader) {
		glAttachShader(program, shader.glRef());
	}

	/**
	 * Attaches the given shaders to this program object.
	 *
	 * @param shaders the shaders to attach
	 */
	public void attach(GLShader[] shaders) {
		for (GLShader shader : shaders) {
			attach(shader);
		}
	}

	/**
	 * Attaches the given list of shaders to this program object.
	 *
	 * @param shaders the shaders to attach
	 */
	public void attach(List<GLShader> shaders) {
		shaders.forEach(this::attach);
	}

	/**
	 * Detaches the given shader from this program object.
	 *
	 * @param shader the shader to detach
	 */
	public void detach(GLShader shader) {
		glDetachShader(program, shader.glRef());
	}

	/**
	 * Detaches the given shaders from this program object
	 *
	 * @param shaders the shaders to detach
	 */
	public void detach(GLShader[] shaders) {
		for (GLShader shader : shaders) {
			detach(shader);
		}
	}

	/**
	 * Detaches the given list of shaders from this program object.
	 *
	 * @param shaders the shaders to detach
	 */
	public void detach(List<GLShader> shaders) {
		shaders.forEach(this::detach);
	}


	public void discard() {
		glDeleteProgram(program);
	}

	@Override
	public void close() {
		discard();
	}

	public void use() {
		glUseProgram(program);
	}

	//---------------------------------Uniforms---------------------------------

	private int getLocation(String name) {
		int uniform;
		if (!uniforms.containsKey(name)) {
			uniforms.put(name, Integer.valueOf(uniform = glGetUniformLocation(program, name)));
		} else {
			uniform = uniforms.get(name);
		}
		return uniform;
	}

	public int getUniformi(int uniform) {
		return glGetUniformi(program, uniform);
	}

	public int getUniformi(String name) {
		return getUniformi(getLocation(name));
	}

	public void setUniformi(int uniform, int value) {
		glUniform1i(uniform, value);
	}

	public void setUniformi(String name, int value) {
		setUniformi(getLocation(name), value);
	}


	public int[] getUniformiv(int uniform, int size) {
		int[] value = new int[size];
		glGetUniformiv(program, uniform, value);
		return value;
	}

	public int[] getUniformiv(String name, int size) {
		return getUniformiv(getLocation(name), size);
	}

	public void setUniformiv(int uniform, int[] value) {
		switch (value.length) {
			case 1:
				glUniform1iv(uniform, value);
				break;
			case 2:
				glUniform2iv(uniform, value);
				break;
			case 3:
				glUniform3iv(uniform, value);
				break;
			case 4:
				glUniform4iv(uniform, value);
				break;
			default:
				glUniform4iv(uniform, value);
		}
	}

	public void setUniformiv(int uniform, int value) {
		glUniform1iv(uniform, new int[]{value});
	}

	public void setUniformiv(int uniform, int value0, int value1) {
		glUniform2iv(uniform, new int[]{value0, value1});
	}

	public void setUniformiv(int uniform, int value0, int value1, int value2) {
		glUniform3iv(uniform, new int[]{value0, value1, value2});
	}

	public void setUniformiv(int uniform, int value0, int value1, int value2, int value3) {
		glUniform4iv(uniform, new int[]{value0, value1, value2, value3});
	}


	public float getUniformf(int uniform) {
		return glGetUniformf(program, uniform);
	}

	public float getUniformf(String name) {
		return getUniformf(getLocation(name));
	}

	public void setUniformf(int uniform, float[] value) {
		switch (value.length) {
			case 1:
				glUniform1f(uniform, value[0]);
				break;
			case 2:
				glUniform2f(uniform, value[0], value[1]);
				break;
			case 3:
				glUniform3f(uniform, value[0], value[1], value[2]);
				break;
			case 4:
				glUniform4f(uniform, value[0], value[1], value[2], value[3]);
				break;
			default:
				glUniform4fv(uniform, value);
				break;
		}
	}


}
