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
 * Created file on 1/15/16 at 8:57 AM.
 *
 * This file is part of jGUI
 */
package xugl.gl.canvas.shader;

import xugl.gl.canvas.GLFrame;
import xugl.ui.canvas.Frame;
import xugl.ui.canvas.model.Model;
import xugl.ui.canvas.shader.Shader;
import xugl.ui.canvas.texture.Texture;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author link
 */
public final class GLShader implements Shader {

	private String shaderSrc;
	private int shader;
	private boolean compiled;
	private static final Frame DUMMY_FRAME = new GLFrame();

	public GLShader(int type, String src) {
		shader = glCreateShader(type);
		shaderSrc = src;
	}

	public void setSource(String src) {
		shaderSrc = src;
		compiled = false;
	}

	public String getSource() {
		return shaderSrc;
	}

	public void compile() {
		if (!compiled) {
			glShaderSource(shader, shaderSrc);
			glCompileShader(shader);
			compiled = true;
		}
	}

	public int getShader() {
		return shader;
	}

	@Override
	public String toString() {
		return glGetShaderInfoLog(shader);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof GLShader)
			if (((GLShader) object).shaderSrc.trim().equals(shaderSrc.trim()) || ((GLShader) object).shader == shader) {
				return true;
			}
		return false;
	}

	@Override
	public Frame process(Model model, Texture texture) {
		return DUMMY_FRAME;
	}
}
