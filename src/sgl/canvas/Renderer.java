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

/**
 * A Renderer is a primarily logical device that renders a Canvas onto a
 * Display. It is the medium between applications and their platform-specific
 * rendering libraries, and abstracts away the interaction between the native
 * Display implementation and native rendering library known as a
 * window context. The context is strictly between an instance of a window and
 * the current native rendering library. No context handling is done directly
 * by
 * SGL or the underlying libraries when using either OpenGL or Vulkan. (TODO:
 * SGL's own native Display system <em>does</em> have context handling.)
 *
 * @author link
 */
@FunctionalInterface
public interface Renderer {

	/**
	 * Renders the given Canvas to the Display that this Renderer is currently
	 * Bound to.
	 *
	 * @param canvas the Canvas to render
	 */
	void render(Canvas canvas);

	/**
	 * Renders the given Canvas2D to the Display that this Renderer is currently
	 * Bound to.
	 *
	 * @param canvas the Canvas to render
	 */
	default void render(Canvas2D canvas) {
		render((Canvas) canvas);
	}

	/**
	 * Renders the given Canvas3D to the Display that this Renderer is currently
	 * Bound to.
	 *
	 * @param canvas the Canvas to render
	 */
	default void render(Canvas3D canvas) {
		render((Canvas2D) canvas);
	}

}
