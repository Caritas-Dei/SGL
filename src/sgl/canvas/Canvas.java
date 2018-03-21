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
package sgl.canvas;

import sgl.image.Image;
import sgl.image.color.Color;
import sgl.shape.Shape;
import sgl.util.math.point.Point2D;
import sgl.util.math.point.Point3D;

/**
 * A Canvas is a generic mediator between a Display and the application
 * which provides basic fundamental drawing operations.
 * <p>
 * A Canvas does not have to rely on any native implementations, but most
 * Canvases do rely on specific rendering libraries for drawing.
 * The Canvas is implemented in software and may call hardware functions for
 * efficiency. Other implementations are purely virtual and do not rely on the
 * hardware for calculations. A Canvas that relies on hardware generally has
 * better performance, latency, and overhead benchmarks compared to a
 * software-based Canvas.
 * </p>
 *
 * @author link
 */
public interface Canvas {

	/**
	 * Gets the size of this Canvas.
	 *
	 * @return the size of this Canvas
	 */
	int getSize();

	/**
	 * Sets the size of this Canvas.
	 *
	 * @param size the new size of this Canvas
	 */
	void setSize(int size);

	int getWidth();

	void setWidth(int width);

	int getHeight();

	void setHeight(int height);

	int getDepth();

	void setDepth(int depth);

	/**
	 * Marks the offset position of this Canvas. Used for linear rendering
	 * operations
	 *
	 * @param offset the offset
	 */
	void offset(double offset);

	void offset(double x, double y);

	void offset(double x, double y, double z);

	/**
	 * Sets the offset to 0.0d, or the initial position
	 */
	default void reset() {
		offset(0.0d);
	}

	//--------------------drawing---------------------

	/**
	 * Draws the given color at the current offset.
	 *
	 * @param color the color to draw
	 * @see #offset(double)
	 */
	void draw(Color color);

	/**
	 * Draws the given image at the current offset.
	 *
	 * @param image the image to draw
	 * @see #offset(double)
	 */
	void draw(Image image);

	/**
	 * Draws the given shape at the current offset.
	 *
	 * @param shape the shape to draw
	 * @see #offset(double)
	 */
	void draw(Shape shape);


	/**
	 * Draws the given color at the given offset.
	 *
	 * @param offset the offset to draw at
	 * @param color  the color to draw
	 */
	void draw(double offset, Color color);

	/**
	 * Draws the given image at the given offset.
	 *
	 * @param offset the offset to draw at
	 * @param image  the image to draw
	 */
	void draw(double offset, Image image);

	/**
	 * Draws the given shape at the given offset.
	 *
	 * @param offset the offset to draw at
	 * @param shape  the shape to draw
	 */
	void draw(double offset, Shape shape);


	void draw(double x, double y, Color color);

	void draw(double x, double y, Image image);

	void draw(double x, double y, Shape shape);


	void draw(double x, double y, double z, Color color);

	void draw(double x, double y, double z, Image image);

	void draw(double x, double y, double z, Shape shape);


	default void draw(Point2D point, Color color) {
		draw(point.getX(), point.getY(), color);
	}

	default void draw(Point2D point, Image image) {
		draw(point.getX(), point.getY(), image);
	}

	default void draw(Point2D point, Shape shape) {
		draw(point.getX(), point.getY(), shape);
	}


	default void draw(Point3D point, Color color) {
		draw(point.getX(), point.getY(), point.getZ(), color);
	}

	default void draw(Point3D point, Image image) {
		draw(point.getX(), point.getY(), point.getZ(), image);
	}

	default void draw(Point3D point, Shape shape) {
		draw(point.getX(), point.getY(), point.getZ(), shape);
	}


	/**
	 * Fills the given region (from start to end) with the given color.
	 *
	 * @param start the start offset
	 * @param end   the end offset
	 * @param color the color
	 */
	void fill(double offset, double size, Color color);

	void fill(double x, double y, double width, double height, Color color);

	void fill(double startX, double startY, double startZ, double endX, double endY, double endZ, Color color);


	default void fill(Point2D point, Point2D dimensions, Color color) {
		fill(point.getX(), point.getY(), dimensions.getX(), dimensions.getY(), color);
	}

	default void fill(Point3D start, Point3D end, Color color) {
		fill(start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ(), color);
	}

}
