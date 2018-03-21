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
package sgl.image.color.space;

/**
 * Represents a specific colorspace. A ColorSpace object contains information
 * detailing the format of pixels and the data type used. Each colorspace
 * supported by a particular graphics library is implementation-dependent, so
 * SGL does not itself provide common colorspaces. Instead, the
 * implementations provide the supported colorspace and increase abstraction for
 * the programmer's sake so one does not worry about colorspace and focuses on
 * graphics programming.
 *
 * @author link
 */
public interface ColorSpace<C extends Comparable<C>> {

	/**
	 * Gets the type which represents the data format of pixels. The type must
	 * be Comparable to itself. Generally, the following binary formats are
	 * used:
	 * <ul>
	 * <li>Boolean: bitmap</li>
	 * <li>Byte: minimal color</li>
	 * <li>Short: color</li>
	 * <li>Integer: true color</li>
	 * <li>Float: true color</li>
	 * <li>Double: true color (esoteric display w/ high color range)</li>
	 * <li>Long: true color (esoteric display w/ high color range)</li>
	 * </ul>
	 * <p>
	 * Other custom formats may be created. One example may be an array-based
	 * format.
	 * </p>
	 *
	 * @return the data type used for pixels
	 */
	Class<C> getDataType();

	Format getDataFormat();


	/**
	 * Represents the format of data in a color space. Such is represented as
	 * bit parameters. See internal constants in Format.* .
	 */
	final class Format {

		public static final byte R = 0b0000_0001, G = 0b0000_0010, B = 0b0000_0100, A = 0b0000_1000;

		public static final short BITS_8 = 0b0001_0000, BITS_16 = 0b0010_0000, BITS_24 = 0b0100_0000, BITS_32 = 0b1000_0000,

		RGB = R | G | B, RGBA = RGB | A,

		RGBA_BYTE_2 = RGBA | BITS_8, RGBA_SHORT_4 = RGBA | BITS_16, RGBA_INT_6 = RGBA | BITS_24, RGBA_INT_8 = RGBA | BITS_32, RGBA_LONG_10 = RGBA | BITS_32 | BITS_8, RGBA_LONG_12 = RGBA | BITS_32 | BITS_16, RGBA_LONG_14 = RGBA | BITS_32 | BITS_24, RGBA_LONG_16 = RGBA | BITS_32 | (BITS_32 << 1),

		RGB_BYTE_2 = RGB | BITS_8, RGB_SHORT_4 = RGB | BITS_16, RGB_INT_6 = RGB | BITS_24, RGB_INT_8 = RGB | BITS_32, RGB_LONG_10 = RGB | BITS_32 | BITS_8, RGB_LONG_12 = RGB | BITS_32 | BITS_16, RGB_LONG_14 = RGB | BITS_32 | BITS_24, RGB_LONG_16 = RGB | BITS_32 | (BITS_32 << 1);

		protected final short format;
		protected final boolean r, g, b, a;

		public Format(final short format) {
			this.format = format;
			r = (format & R) != 0;
			g = (format & G) != 0;
			b = (format & B) != 0;
			a = (format & A) != 0;
		}

		public final byte getBitWidth() {
			return (byte) (format >> 4);
		}

		public final boolean hasRed() {
			return r;
		}

		public final boolean hasGreen() {
			return g;
		}

		public final boolean hasBlue() {
			return b;
		}

		public final boolean hasAlpha() {
			return a;
		}

		public int format(int value) {
			return value;
		}
	}

}
