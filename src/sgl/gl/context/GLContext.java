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
 * Created file on 9/20/16 at 7:47 PM.
 *
 * This file is part of XUGL
 */
package sgl.gl.context;

import sgl.gl.GLObject;
import sgl.gl.OpenGL;
import sgl.gl.profile.GLProfile;
import sgl.glfw.ui.display.GLFWDisplay;
import sgl.jni.NativeObject;
import sgl.ui.display.Display;
import sgl.util.Shutdown;

/**
 * A (reusable) OpenGL context object that aids in creation and management of
 * OpenGL contexts.
 *
 * @author link
 * @see OpenGL
 */
public final class GLContext implements NativeObject, AutoCloseable {

	private final Display display;
	private final Thread context;
	private final GLProfile profile;

	/**
	 * @param version
	 */
	public GLContext(final int version) {
		this(version, new GLFWDisplay(0, 0, 0, 0, "", null));
	}

	public GLContext(final int version, final Display display) {
		if (display == null)
			throw new IllegalArgumentException("The display cannot be null");
		else if (!(display instanceof GLObject))
			throw new IllegalArgumentException("The given display does not require an OpenGL context");

		this.display = display;
		context = Thread.currentThread();
		profile = GLProfile.forVersion(version);
	}

	{
		Shutdown.addHook(() -> {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Thread getOwner() {
		return context;
	}

	public Display getDisplay() {
		return display;
	}

	public GLProfile getProfile() {
		return profile;
	}

	@Override
	public void close() throws Exception {
		display.destroy();
	}
}
