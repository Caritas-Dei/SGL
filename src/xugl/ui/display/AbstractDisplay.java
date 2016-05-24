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
 * Created file on 12/27/15 at 4:36 PM.
 *
 * This file is part of jGUI
 */
package xugl.ui.display;

import xugl.ui.canvas.Canvas;

/**
 * @author link
 */
public abstract class AbstractDisplay implements Display {

	/** The location and size of this Display */
	protected int x, y, width, height;
	/** The title of this Display */
	protected String title;
	/** The canvas this Display will render */
	protected Canvas canvas;

	/**
	 * Creates a new AbstractDisplay with the specified location, size, and
	 * title.
	 *
	 * @param x
	 * 		the x location on screen of this Display
	 * @param y
	 * 		the y location on screen of this Display
	 * @param width
	 * 		the width of this Display
	 * @param height
	 * 		the height of this Display
	 * @param title
	 * 		the title of this Display
	 */
	protected AbstractDisplay(int x, int y, int width, int height, String title) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.title = title;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Canvas getCanvas() {
		return canvas;
	}

	@Override
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
}
