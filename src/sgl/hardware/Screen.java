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
package sgl.hardware;

import java.awt.*;

/**
 * @author link
 */
public enum Screen {
	;
	private static final GraphicsEnvironment GRAPHICS_ENV = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static final GraphicsDevice DEFAULT_GRAPHICS_DEVICE = GRAPHICS_ENV.getDefaultScreenDevice();
	private static final GraphicsConfiguration DEFAULT_GRAPHICS_CONFIG = DEFAULT_GRAPHICS_DEVICE.getDefaultConfiguration();
	private static final DisplayMode DEFAULT_DISPLAY_MODE = DEFAULT_GRAPHICS_DEVICE.getDisplayMode();
	private static final int WIDTH = DEFAULT_DISPLAY_MODE.getWidth();
	private static final int HEIGHT = DEFAULT_DISPLAY_MODE.getHeight();

	public static GraphicsEnvironment getGraphicsEnvironment() {
		return GRAPHICS_ENV;
	}

	public static GraphicsDevice getDefaultGraphicsDevice() {
		return DEFAULT_GRAPHICS_DEVICE;
	}

	public static GraphicsConfiguration getDefaultGraphicsConfig() {
		return DEFAULT_GRAPHICS_CONFIG;
	}

	public static DisplayMode getDefaultDisplayMode() {
		return DEFAULT_DISPLAY_MODE;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

}
