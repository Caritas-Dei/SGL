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
 * Created file on 1/15/16 at 9:15 AM.
 *
 * This file is part of jGUI
 */
package xugl.gl.canvas.shader;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author link
 */
public enum GLSL {
	;

	public static final GLShader DEFAULT_VERTEX = null;
	public static final GLShader DEFAULT_GEOMETRY = null;
	public static final GLShader DEFAULT_FRAGMENT = null;

	private static final String VEC = "vec";
	private static final String UNIFORM = "uniform";

	public static String line(String s) {
		return s + '\n';
	}

	public static String glslShader(byte version, String main) {
		return glslVersion(version) + main;
	}

	public static String glslShader(byte version, String[] in, String main) {
		return glslVersion(version) + String.join("", in) + main;
	}

	public static String glslShader(byte version, String[] in, String[] out, String main) {
		return glslVersion(version) + String.join("", in) + String.join("", out) + main;
	}

	public static String glslShader(byte version, String[] in, String[] out, String main, String[] functions) {
		return glslVersion(version) + String.join("", in) + String.join("", out) + main + String.join("", functions);
	}


	public static int glslCompileShader(int type, String source) {
		int shader = glCreateShader(type);
		glShaderSource(shader, source);
		glCompileShader(shader);
		return shader;
	}

	public static int glslCreateProgram(int vertex, int fragment) {
		int program = glCreateProgram();
		glAttachShader(program, vertex);
		glAttachShader(program, fragment);
		glLinkProgram(program);
		return program;
	}

	/**
	 * Prepends and appends the newline char to make the string  a lone string
	 *
	 * @param s
	 * 		the string that is to be a single line
	 * @return the formatted single line
	 */
	public static String singleLine(String s) {
		return '\n' + line(s);
	}

	public static String tab(String s) {
		return '\t' + s;
	}

	public static String glslVersion(int version) {
		return line(line("#version " + version));
	}

	public static String glslIn(String type, String name) {
		return line("in " + type + ' ' + name);
	}

	public static String glslOut(String type, String name) {
		return line("out " + type + ' ' + name);
	}

	public static String glslUniform(String type, String name) {
		return line(UNIFORM + ' ' + type + ' ' + name + ';');
	}

	public static String glslMain(String[] src) {
		return "void main() {\n\t" +
				       String.join("", src) +
				       "}\n";
	}

	public static String glslVar(String type, String name, String value) {
		return line(type + ' ' + name + " = " + value + ';');
	}

	public static String glslVec(byte size, String name, String[] values) {
		return line(VEC + size + ' ' + name + " = (" + String.join(", ", values) + ");");
	}

	public static String glslVec1(String name, String value) {
		return glslVec((byte) 1, name, new String[]{value});
	}

	public static String glslVec2(String name, String value1, String value2) {
		return glslVec((byte) 2, name, new String[]{value1, value2});
	}

	public static String glslVec3(String name, String value1, String value2, String value3) {
		return glslVec((byte) 3, name, new String[]{value1, value2, value3});
	}

	public static String glslVec4(String name, String value1, String value2, String value3, String value4) {
		return glslVec((byte) 4, name, new String[]{value1, value2, value3, value4});
	}

	public static String glslGetGL(String name) {
		return "gl_" + name;
	}

	/**
	 * Sets a built-in variable.
	 * <p>
	 * Example: to set gl_Position:
	 * <p>
	 * glslSetGL("Position", "myVar");
	 *
	 * @param name
	 * 		the name of the built-in variable
	 * @param value
	 * 		the new value of the variable
	 * @return the source to set a built-in gl_ variable
	 */
	public static String glslSetGL(String name, String value) {
		return glslSet("gl_" + name, value);
	}

	/**
	 * Gets the gl_Position built-in variable
	 *
	 * @return the gl_Position variable
	 */
	public static String glslPosition() {
		return "gl_Position";
	}

	public static String glslPosition(String value) {
		return line(glslPosition() + " = " + value + ';');
	}

	public static String glslSet(String var, String value) {
		return line(var + " = " + value + ';');
	}

	public static String glslGet(String var) {
		return var;
	}

	public static String glslReturn(String var) {
		return line("return " + var + ';');
	}

	public static String glslInVar(String type, String name) {
		return "in " + type + ' ' + name;
	}

	public static String glslOutVar(String type, String name) {
		return "out " + type + ' ' + name;
	}

	public static String glslInOutVar(String type, String name) {
		return "inout " + type + ' ' + name;
	}

	public static String glslVarfn(String type, String name) {
		return type + ' ' + name;
	}

	public static String glslFunction(String type, String name, String[] vars, String[] code) {
		return line(type + ' ' + name + '(' + String.join(", ", vars) + ") {\n" +
				            '\t' + String.join("", code) + '\n' +
				            "}\n");
	}

}
