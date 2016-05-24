/*
 * The MIT License
 *
 * Copyright 2015 link.
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
 * Created file on 12/29/15 at 12:48 PM.
 *
 * This file is part of jGUI
 */
package xugl.glfw.error;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.APIUtil;

import java.util.Map;

/**
 * @author link
 */
public class GLFWError extends Error {

	static final Map<Integer, String> GLFW_ERR_CODES = APIUtil.apiClassTokens((field, value) -> 0x10000 < value && value < 0x20000, null, GLFW.class);

	protected final int errorCode;
	protected final String description;

	public GLFWError(int errorCode, long description) {
		this.errorCode = errorCode;
		this.description = GLFWException.decode(description);
	}

	@Override
	public String getMessage() {
		return description;
	}

	@Override
	public String getLocalizedMessage() {
		return description;
	}
}
