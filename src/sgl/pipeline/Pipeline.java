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
package sgl.pipeline;

import sgl.canvas.Canvas;
import sgl.canvas.Renderable;

import java.util.List;

/**
 * A Pipeline object is a FIFO queue of rendering operations
 * stored in a call list of {@link Renderable Renderable}s. Each call to {@link
 * #enter(Canvas)} invokes this Pipeline and iterates over the call list
 * and invokes each one in the order that they were added to this Pipeline
 * object.
 * <p>
 * Most Pipeline implementations should invoke Renderers in the order they
 * were added, however, some esoteric implementations may have an inverse
 * invocation order, or an entirely arbitrary invocation order. It all depends
 * on the implementation and the level of control given to the user of the
 * implementation.
 * </p>
 * <p>
 * An esoteric Pipeline, for example, may allow a reconfigurable order of
 * invocation. This would allow reusing the same Pipeline object to run
 * different operations just because the order was changed. Perhaps one
 * implementation is a contiguous Pipeline with a FIFO stack as the call list.
 * Another might use a LIFO call list where any new draw operation is immediate
 * and invoked in reverse order.
 * </p>
 * <p>
 * The Pipeline itself is an instance of Renderable, so whole Pipelines can be
 * part of another Pipeline. <em>A Pipeline should not have non-terminating
 * loops.</em> A Pipeline is specifically used for applying a draw operation to
 * a Canvas for a single frame, so making a recursive pipeline or a pipeline
 * containing an infinitely recursive loop will result in <span
 * style="text-decoration:underline;">soft-lock</span> of that Thread.
 * </p>
 * <p>
 * Furthermore, a Pipeline has no reason to stall at any point, so do not add
 * any calls that halt execution of the Pipeline. The Pipeline should be
 * interrupted using {@link #interrupt()}, and resumed using {@link #resume()}.
 * Any other method of interrupting the Pipeline is undefined behavior.
 * </p>
 * <p>
 * The {@link #interrupt()} and {@link #resume()} methods are intended to pause
 * and resume the Pipeline at any given point. When {@link #interrupt()} is
 * invoked, whether or not the Pipeline waits for the current Renderable to
 * finish is at the discretion of the implementation. SGL's implementations wait
 * for the current drawing function to finish.
 * </p>
 *
 * @author link
 */
public interface Pipeline<C extends Canvas> extends Iterable<Renderable<C>>, Renderable<C> {

	/**
	 * Adds the given Renderable to this Pipeline. Each subsequent call adds a
	 * renderer to the <em>end</em> of the call list. Some implementations may
	 * choose to implement this in favor of reverse-order call lists.
	 * <br>
	 * <strong><em>*!*CAUTION*!*: do not add recursive Pipelines or infinitely
	 * recurring loops to a Pipeline!</em></strong>
	 *
	 * @param renderable the Renderable to add to this Pipeline
	 */
	void add(Renderable<? extends C> renderable);

	/**
	 * Adds the given call list of Renderers to this pipeline in the order
	 * they appear in the given list. Each subsequent call adds the renderers
	 * in the order that they appeared in the list to the <em>end</em> of this
	 * Pipeline's call list. Some implementations may choose to implement this
	 * in favor of reverse-order call lists, however.
	 *
	 * @param renderables the Renderers to add to this Pipeline
	 * @see #add(Renderable)
	 */
	void add(List<Renderable<? extends C>> renderables);

	/**
	 * Resets this Pipeline to a non-operating state by clearing the call list.
	 * When a subsequent {@link #enter(Canvas) enter} is invoked, this
	 * Pipeline is guaranteed to have no effect if no Renderers have been
	 * added to this Pipeline after calling this method and before calling
	 * enter.
	 */
	void clear();

	/**
	 * Iterates over the call list and invokes them on the given Canvas object.
	 *
	 * @param canvas the canvas to draw to
	 */
	void enter(C canvas);

	/**
	 * Gets the size of this Pipeline (the number of Renderers).
	 *
	 * @return the number of Renderers this Pipeline has
	 * if recursive
	 */
	int calls();

	/**
	 * Finishes the current invocation and returns immediately. The next call
	 * call list index is saved.
	 *
	 * @see #resume()
	 * @see #exit()
	 */
	void interrupt();

	/**
	 * Continues execution of this Pipeline. The next uninvoked Renderable is
	 * where execution resumes.
	 *
	 * @see #interrupt()
	 */
	void resume();

	/**
	 * Immediately stops execution after completion of the current Renderable
	 */
	void exit();

	void render(C canvas);

}
