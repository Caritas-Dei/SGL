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
 * Created file on 1/15/16 at 12:01 PM.
 *
 * This file is part of jGUI
 */
package xugl.gl.canvas.shader;

import static java.util.Objects.requireNonNull;
import static org.lwjgl.opengl.GL20.*;

/**
 * @author link
 */
public final class Program {

	private final GLShader vertex, geometry, fragment;
	private final int program;

	public Program(GLShader vertex, GLShader fragment) {
		this.vertex = requireNonNull(vertex, "Vertex ImageFilter cannot be null!");
		geometry = null;
		this.fragment = requireNonNull(fragment, "Fragment ImageFilter cannot be null!");

		program = glCreateProgram();
		glAttachShader(program, vertex.getShader());
		glAttachShader(program, fragment.getShader());
	}

	public Program(GLShader vertex, GLShader geometry, GLShader fragment) {
		this.vertex = requireNonNull(vertex, "Vertex ImageFilter cannot be null!");
		this.geometry = requireNonNull(geometry, "Geometry ImageFilter cannot be null!");
		this.fragment = requireNonNull(fragment, "Fragment ImageFilter cannot be null!");

		program = glCreateProgram();
		glAttachShader(program, vertex.getShader());
		glAttachShader(program, geometry.getShader());
		glAttachShader(program, fragment.getShader());
	}

	public boolean hasGeometryShader() {
		return geometry != null;
	}

	public void link() {
		glLinkProgram(program);
	}

	public void use() {
		glUseProgram(program);
	}

}
