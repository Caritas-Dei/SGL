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
 * Created file on 12/27/15 at 4:18 PM.
 *
 * This file is part of jGUI
 */
package xugl.ui.display;

import xugl.io.Keyboard;
import xugl.ui.canvas.Canvas;

/**
 * @author link
 */
public interface Display {


	/**
	 * Gets the x of this Display on the screen.
	 *
	 * @return the x of this Display on the monitor
	 */
	int getX();

	/**
	 * Sets the x of this Display on the screen.
	 */
	void setX(int x);

	/**
	 * Gets the y of this Display on the screen.
	 *
	 * @return the y of this Display on the monitor
	 */
	int getY();

	/**
	 * Sets the y of this Display on the screen.
	 */
	void setY(int y);

	/**
	 * Gets the width of this Display.
	 *
	 * @return the width of this Display
	 */
	int getWidth();

	/**
	 * Sets the width of this Display.
	 *
	 * @param width
	 * 		the width of this Display
	 */
	void setWidth(int width);

	/**
	 * Gets the height of this Display.
	 *
	 * @return the height of this Display.
	 */
	int getHeight();

	/**
	 * Sets the height of this Display
	 *
	 * @param height
	 * 		the height of this Display
	 */
	void setHeight(int height);

	String getTitle();

	/**
	 * Sets the title of this Display.
	 *
	 * @param title
	 * 		the new title of this Display
	 */
	void setTitle(String title);

	/**
	 * Gets the canvas that this Display is rendering.
	 *
	 * @return the canvas that this Display is rendering
	 */
	Canvas getCanvas();

	/**
	 * Sets the canvas that this Display will render.
	 *
	 * @param canvas
	 * 		the canvas this Display will render
	 */
	void setCanvas(Canvas canvas);


	/**
	 * Shows this Display if hidden.
	 */
	void show();

	/**
	 * Hides this Display if visible.
	 */
	void hide();

	/**
	 * Closes this Display.
	 */
	void close();

	/**
	 * Destroys this Display to free up resources.
	 */
	void destroy();

	/**
	 * Renders the specified canvas, but doesn't refresh this Display.
	 */
	void render(Canvas canvas);

	/**
	 * Refreshes the Display.
	 */
	void refresh();

	/**
	 * Creates a child of this Display.
	 * <p>
	 * A Child Display is implementation dependant, and allows for the
	 * possibility of resource sharing between Displays.
	 * </p>
	 *
	 * @param width
	 * 		the width of the child Display
	 * @param height
	 * 		the height of the child Display
	 * @param title
	 * 		the title of the child Display
	 * @return a child of this Display
	 */
	Display createChild(int x, int y, int width, int height, String title);

	Keyboard getKeyboard();

}
