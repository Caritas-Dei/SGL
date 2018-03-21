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
package sgl.opengl;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;
import sgl.opengl.error.GLError;

import java.util.HashSet;
import java.util.Set;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.glGetError;
import static org.lwjgl.system.MemoryUtil.NULL;
import static sgl.opengl.OpenGL.Feature.*;

/**
 * A very high level OpenGL decoupling to make your life easier.
 * <p>
 * This provided OpenGL decoupling provides a version and feature set, as
 * well
 * as GL profiles. Each GL profile contains the same methods as this class, and
 * each is required to implement them for the appropriate OpenGL context.
 * Most parameters are preserved.
 * </p>
 * <p>
 * Proper usage of this class is vital. One may prefer using this decoupling in
 * preference to directly calling LWJGL bindings. It also used entirely by this
 * framework; few OpenGL functions are called directly. To ensure that this
 * class and OpenGl work correctly with your application:
 * <ol>
 * <li>Ensure an OpenGL context is current for the current thread. This is
 * done by creating a window.</li>
 * Call {@link #initialize()} on the Thread you
 * created the Display on. <strong><em>This must happen after you initialize the Display
 * object.</em></strong></li>
 * </ol>
 * </p>
 * <p>
 * The resulting code might look like this pseudo-code:
 * <pre>
 *      GLDisplay display = new GLFWDisplay(x, y, size, height, title,
 * canvas);
 *      OpenGL.setProfile(display.getContext().getProfile());
 *      OpenGL.initialize(display.getContext());
 * </pre>
 * </p>
 *
 * @author link
 */
public enum OpenGL {
	;

	private static final Set<Integer> VERSIONS = new HashSet<>();
	private static final Set<Feature> FEATURES = new HashSet<>();
	private static final int VERSION;

	/**
	 * Full list of OpenGL Features and Extensions
	 */
	public enum Feature {

		// AMD

		AMD_BLEND_MINMAX_FACTOR, AMD_CONSERVATIVE_DEPTH, AMD_DEBUG_OUTPUT, AMD_DEPTH_CLAMP_SEPERATE, AMD_DRAW_BUFFERS_BLEND, AMD_GCN_SHADER, AMD_GPU_SHADER_INT_64, AMD_INTERLEAVED_ELEMENTS, AMD_OCCLUSION_QUERY_EVENT, AMD_PERFORMANCE_MONITOR, AMD_PINNED_MEMORY, AMD_QUERY_BUFFER_OBJECT, AMD_SAMPLE_POSITIONS, AMD_SEAMLESS_CUBEMAP_PER_TEXTURE, AMD_SHADER_ATOMIC_COUNTER_OPS, AMD_SHADER_STENCIL_EXPORT, AMD_SHADER_TRINARY_MINMAX, AMD_SPARSE_TEXTURE, AMD_STENCIL_OPERATION_EXTENDED, AMD_TEXTURE_TEXTURE4, AMD_TRANSFORM_FEEDBACK3_LINES_TRIANGLES, AMD_TRANSFORM_FEEDBACK_4, AMD_VERTEX_SHADER_LAYER, AMD_VERTEX_SHADER_TESSELLATOR, AMD_VERTEX_SHADER_VIEWPORT_INDEX,

		// ARB

		ARB_ARRAY_OF_ARRAYS, ARB_BASE_INSTANCE, ARB_BINDLESS_TEXTURE, ARB_BLEND_FUNC_EXTENDED, ARB_BUFFER_STORAGE, ARB_CLEAR_BUFFER_OBJECT, ARB_CLEAR_TEXTURE, ARB_CL_EVENT, ARB_CLIP_CONTROL, ARB_COLOR_BUFFER_FLOAT, ARB_COMPRESSED_TEXTURE_PIXEL_STORAGE, ARB_COMPUTE_SHADER, ARB_COMPUTE_VARIABLE_GROUP_SIZE, ARB_CONDITIONAL_RENDER_INVERTED, ARB_CONSERVATIVE_DEPTH, ARB_COPY_BUFFER, ARB_COPY_IMAGE, ARB_CULL_DISTANCE, ARB_DEBUG_GROUP, ARB_DEBUG_LABEL, ARB_DEBUG_OUTPUT2, ARB_DEBUG_OUTPUT, ARB_DEPTH_BUFFER_FLOAT, ARB_DEPTH_CLAMP, ARB_DEPTH_TEXTURE, ARB_DERIVATIVE_CONTROL, ARB_DIRECT_STATE_ACCESS, ARB_DRAW_BUFFERS, ARB_DRAW_BUFFERS_BLEND, ARB_DRAW_ELEMENTS_BASE_VERTEX, ARB_DRAW_INDIRECT, ARB_DRAW_INSTANCED, ARB_ENHANCED_LAYOUTS, ARB_ES2_COMPATIBILITY, ARB_ES3_COMPATIBILITY, ARB_ES31_COMPATIBILITY, ARB_ES32_COMPATIBILITY, ARB_EXPLICIT_ATTRIB_LOCATION, ARB_EXPLICIT_UNIFORM_LOCATION, ARB_FRAGMENT_COORD_CONVENTIONS, ARB_FRAGMENT_LAYER_VIEWPORT, ARB_FRAGMENT_PROGRAM, ARB_FRAGMENT_SHADER, ARB_FRAMEBUFFER_NO_ATTACHMENTS, ARB_FRAMEBUFFER_OBJECT, ARB_FRAMEBUFFER_SRGB, ARB_GEOMETRY_SHADER4, ARB_GET_PROGRAM_BINARY, ARB_GET_TEXTURE_SUB_IMAGE, ARB_GPU_SHADER5, ARB_GPU_SHADER_FP64, ARB_GPU_SHADER_INT64, ARB_HALF_FLOAT_PIXEL, ARB_HALF_FLOAT_VERTEX, ARB_IMAGING, ARB_INDIRECT_PARAMETERS, ARB_INSTANCED_ARRAYS, ARB_INTERNALFORMAT_QUERY, ARB_INTERNALFORMAT_QUERY2, ARB_INVALIDATE_SUBDATA, ARB_MAP_BUFFER_ALIGNMENT, ARB_MAP_BUFFER_RANGE, ARB_MATRIX_PALETTE, ARB_MULTI_BIND, ARB_MULTI_DRAW_INDIRECT, ARB_MULTISAMPLE, ARB_MULTITEXTURE, ARB_OCCLUSION_QUERY, ARB_OCCLUSION_QUERY2, ARB_PARALLEL_SHADER_COMPILE, ARB_PIPELINE, STATISTICS_QUERY, ARB_PIXEL_BUFFER_OBJECT, ARB_POINT_PARAMETERS, ARB_POINT_SPRITE, ARB_PROGRAM_INTERFACE_QUERY, ARB_PROVOKING_VERTEX, ARB_QUERY_BUFFER_OBJECT, ARB_ROBUSTNESS, ARB_ROBUST_BUFFER_ACCESS_BEHAVIOR, ARB_SAMPLE_LOCATIONS, ARB_SAMPLER_OBJECTS, ARB_SAMPLE_SHADING, ARB_SEAMLESS_CUBE_MAP, ARB_SEAMLESS_CUBEMAP_PER_TEXTURE, ARB_SEPARATE_SHADER_OBJECTS, ARB_SHADER_ATOMIC_COUNTERS, ARB_SHADER_BIT_ENCODING, ARB_SHADER_IMAGE_LOAD_STORE, ARB_SHADER_IMAGE_SIZE, ARB_SHADER_OBJECTS, ARB_SHADER_PRECISION, ARB_SHADER_STORAGE_BUFFER_OBJECT, ARB_SHADER_SUBROUTINE, ARB_SHADER_TEXTURE_IMAGE_SAMPLES, ARB_SHADING_LANGUAGE_100, ARB_SHADING_LANGUAGE_420PACK, ARB_SHADING_LANGUAGE_INCLUDE, ARB_SHADOW, ARB_SHADOW_AMBIENT, ARB_SPARSE_BUFFER, ARB_SPARSE_TEXTURE, ARB_STENCIL_TEXTURING, ARB_SYNC, ARB_TESSELLATION_SHADER, ARB_TEXTURE_BARRIER, ARB_TEXTURE_BORDER_CLAMP, ARB_TEXTURE_BUFFER_OBJECT, ARB_TEXTURE_BUFFER_OBJECT_RGB32, ARB_TEXTURE_BUFFER_RANGE, ARB_TEXTURE_COMPRESSION, ARB_TEXTURE_COMPRESSION_BPTC, ARB_TEXTURE_COMPRESSION_RGTC, ARB_TEXTURE_CUBE_MAP, ARB_TEXTURE_CUBE_MAP_ARRAY, ARB_TEXTURE_ENV_ADD, ARB_TEXTURE_ENV_COMBINE, ARB_TEXTURE_ENV_CROSSBAR, ARB_TEXTURE_ENV_DOT3, ARB_TEXTURE_FILTER_MINMAX, ARB_TEXTURE_FLOAT, ARB_TEXTURE_GATHER, ARB_TEXTURE_MIRROR_CLAMP_TO_EDGE, ARB_TEXTURE_MIRRORED_REPEAT, ARB_TEXTURE_MULTISAMPLE, ARB_TEXTURE_NON_POWER_OF_TWO, ARB_TEXTURE_QUERY_LEVELS, ARB_TEXTURE_QUERY_LOD, ARB_TEXTURE_RECTANGLE, ARB_TEXTURE_RG, ARB_TEXTURE_RGB10_A2UI, ARB_TEXTURE_STENCIL8, ARB_TEXTURE_STORAGE, ARB_TEXTURE_STORAGE_MULTISAMPLE, ARB_TEXTURE_SWIZZLE, ARB_TEXTURE_VIEW, ARB_TIMER_QUERY, ARB_TRANSFORM_FEEDBACK2, ARB_TRANSFORM_FEEDBACK3, ARB_TRANSFORM_FEEDBACK_INSTANCED, ARB_TRANSFORM_FEEDBACK_OVERFLOW_QUERY, ARB_TRANSPOSE_MATRIX, ARB_UNIFORM_BUFFER_OBJECT, ARB_VERTEX_ARRAY_BGRA, ARB_VERTEX_ARRAY_OBJECT, ARB_VERTEX_ATTRIB_64BIT, ARB_VERTEX_ATTRIB_BINDING, ARB_VERTEX_BLEND, ARB_VERTEX_BUFFER_OBJECT, ARB_VERTEX_PROGRAM, ARB_VERTEX_SHADER, ARB_VERTEX_TYPE_2_10_10_10_REV, ARB_VERTEX_TYPE_10F_11F_11F_REV, ARB_VIEWPORT_ARRAY, ARB_WINDOW_POS,

		// ATI

		ATI_MEM_INFO, ATI_SEPARATE_STENCIL, ATI_TEXTURE_COMPRESSION_3DC,

		CGL,

		// Apple

		APPLE_FLUSH_BUFFER_RANGE, APPLE_VERTEX_ARRAY_OBJECT,

		// EXT

		EXT_422_PIXELS, EXT_ABGR, EXT_BGRA, EXT_BINDABLE_UNIFORM, EXT_BLEND_LOGIC_OP, EXT_BLEND_COLOR, EXT_BLEND_FUNC_SEPARATE, EXT_BLEND_EQUATION_SEPARATE, EXT_FOG_COORD, EXT_FUNC_SEPARATE, EXT_BLEND_MINMAX, EXT_BLEND_SUBTRACT, EXT_CLIP_VOLUME_HINT, EXT_COLOR_TABLE, EXT_COLOR_SUBTABLE, EXT_COMPILED_VERTEX_ARRAY, EXT_CONVOLUTION, EXT_COPY_TEXTURE, EXT_DEBUG_LABEL, EXT_DEBUG_MARKER, EXT_DEPTH_BOUNDS_TEST, EXT_DIRECT_STATE_ACCESS, EXT_DRAW_BUFFERS2, EXT_DRAW_INSTANCED, EXT_DRAW_RANGE_ELEMENTS, EXT_FRAMEBUFFER_BLIT, EXT_FRAMEBUFFER_MULTISAMPLE, EXT_FRAMEBUFFER_MULTISAMPLE_BLIT_SCALED, EXT_FRAMEBUFFER_OBJECT, EXT_FRAMEBUFFER_SRGB, EXT_GEOMETRY_SHADER4, EXT_GPU_PROGRAM_PARAMETERS, EXT_GPU_SHADER4, EXT_HISTOGRAM, EXT_MULTI_DRAW_ARRAYS, EXT_PACKED_DEPTH_STENCIL, EXT_PACKED_FLOAT, EXT_PACKED_PIXELS, EXT_PIXEL_BUFFER_OBJECT, EXT_POINT_PARAMETERS, EXT_POLYGON_OFFSET, EXT_POLYGON_OFFSET_CLAMP, EXT_PROVOKING_VERTEX, EXT_RASTER_MULTISAMPLE, EXT_RESCALE_NORMAL, EXT_SECONDARY_COLOR, EXT_SEPARATE_SHADER_OBJECTS, EXT_SEPARATE_SPECULAR_COLOR, EXT_SHADER_IMAGE_LOAD_STORE, EXT_SHARED_TEXTURE_PALETTE, EXT_SHADOW_FUNCS, EXT_STENCIL_CLEAR_TAG, EXT_STENCIL_TWO_SIDE, EXT_STENCIL_WRAP, EXT_SUBTEXTURE, EXT_TEXTURE, EXT_TEXTURE3D, EXT_TEXTURE_ARRAY, EXT_TEXTURE_BUFFER_OBJECT, EXT_TEXTURE_COMPRESSION_LATC, EXT_TEXTURE_COMPRESSION_RGTC, EXT_TEXTURE_COMPRESSION_S3TC, EXT_TEXTURE_FILTER_ANISOTROPIC, EXT_TEXTURE_FILTER_MINMAX, EXT_TEXTURE_INTEGER, EXT_TEXTURE_LOD_BIAS, EXT_TEXTURE_MIRROR_CLAMP, EXT_TEXTURE_OBJECT, EXT_TEXTURE_SHARED_EXPONENT, EXT_TEXTURE_S_NORM, EXT_TEXTURE_SRGB, EXT_TEXTURE_SRGB_DECODE, EXT_TEXTURE_SWIZZLE, EXT_TIMER_QUERY, EXT_TRANSFORM_FEEDBACK, EXT_VERTEX_ARRAY, EXT_VERTEX_ATTRIB_64BIT, EXT_X11_SYNC_OBJECT,

		// GL SGI

		SGI_COLOR_MATRIX,

		SGIS_GENERATE_MIPMAP, SGIS_TEXTURE_EDGE_CLAMP, SGIS_TEXTURE_LOAD,

		// HP

		HP_CONVOLUTION_BORDER_MODES,

		// GLX

		GLX_AMD_GPU_ASSOCIATION,

		GLX_ARB_CONTEXT_FLUSH_CONTROL, GLX_ARB_CREATE_CONTEXT, GLX_ARB_CREATE_CONTEXT_PROFILE, GLX_ARB_CREATE_CONTEXT_ROBUSTNESS, GLX_ARB_BFB_CONFIG_FLOAT, GLX_ARB_FRAMEBUFFER_SRGB, GLX_ARB_GET_PROC_ADDRESS, GLX_ARB_MULTISAMPLE, GLX_ARB_ROBUSTNESS_APPLICATION_ISOLATION, GLX_ARB_VERTEX_BUFFER_OBJECT,

		// GLX EXT

		GLX_EXT_BUFFER_AGE, GLX_EXT_CREATE_CONTEXT_ES2_PROFILE, GLX_EXT_CREATE_CONTEXT_ES_PROFILE, GLX_EXT_FB_CONFIG_PACKED_FLOAT, GLX_EXT_FRAMEBUFFER_SRGB, GLX_EXT_IMPORT_CONTEXT, GLX_EXT_STEREO_TREE, GLX_EXT_SWAP_CONTROL, GLX_EXT_SWAP_CONTROL_TEAR, GLX_EXT_TEXTURE_FROM_PIXMAP, GLX_EXT_VISUAL_INFO, GLX_EXT_VISUAL_RATING,

		// GLX INTEL

		GLX_INTEL_SWAP_EVENT,

		// GLX NV

		GLX_NV_COPY_BUFFER, GLX_NV_COPY_IMAGE, GLX_NV_DELAY_BEFORE_SWAP, GLX_NV_FLOAT_BUFFER, GLX_NV_MULTISAMPLE_COVERAGE, GLX_NV_SWAP_GROUP,

		// GLX SGI(X)

		GLX_SGI_MAKE_CURRENT_READ, GLX_SGI_SWAP_CONTROL, GLX_SGI_VIDEO_SYNC, GLX_SGIX_FB_CONFIG, GLX_SGIX_PBUFFER, GLX_SGIX_SWAP_BARRIER, GLX_SGIX_SWAP_GROUP,

		GLX_EXT_STEREO_NOTIFY_EVENT,

		// INTEL

		INTEL_FRAMEBUFFER_CMAA, INTEL_MAP_TEXTURE, INTEL_PERFORMANCE_QUERY,

		// KHR

		KHR_BLEND_EQUATION_ADVANCED, KHR_BLEND_EQUATION_ADVANCED_COHERENT, KHR_CONTEXT_FLUSH_CONTROL, KHR_DEBUG, KHR_NO_ERROR, KHR_ROBUST_BUFFER_ACCESS_BEHAVIOR, KHR_ROBUSTNESS, KHR_TEXTURE_COMPRESSION_ASTC_HDR, KHR_TEXTURE_COMPRESSION_ASTC_SLICED_3D, KHR_TEXTURE_COMPRESSION_ASTC_LDR, KHR_TEXTURE_COMPRESSION_ASTCLDR,

		// NV

		NV_BINDLESS_MULTI_DRAW_INDIRECT, NV_BINDLESS_MULTI_DRAW_INDIRECT_COUNT, NV_BINDLESS_TEXTURE, NV_BLEND_EQUATION_ADVANCED, NV_BLEND_EQUATION_ADVANCED_COHERENT, NV_BLEND_SQUARE, NV_CLIP_SPACE_W_SCALING, NV_COMMAND_LIST, NV_CONDITIONAL_RENDER, NV_CONSERVATIVE_RASTER, NV_CONSERVATIVE_RASTER_DILATE, NV_CONSERVATIVE_RASTER_PRE_SNAP_TRIANGLES, NV_COPY_DEPTH_TO_COLOR, NV_COPY_IMAGE, NV_DEEP_TEXTURE3D, NV_DEEP_TEXTURE_3D, NV_DEPTH_BUFFER_FLOAT, NV_DEPTH_CLAMP, NV_DRAW_TEXTURE, NV_DRAW_VULKAN_IMAGE, NV_ES3_1_COMPATIBILITY, NV_EXPLICIT_MULTISAMPLE, NV_FENCE, NV_FILL_RECTANGLE, NV_FLOAT_BUFFER, NV_FOG_DISTANCE, NV_FRAGMENT_COVERAGE_TO_COLOR, NV_FRAGMENT_PROGRAM4, NV_FRAGMENT_PROGRAM_OPTION, NV_FRAMEBUFFER_MIXED_SAMPLES, NV_FRAMEBUFFER_MULTISAMPLE_COVERAGE, NV_GEOMETRY_SHADER4, NV_GEOMETRY_SHADER_PASSTHROUGH, NV_GPU_MULTICAST, NV_GPU_SHADER5, NV_HALF_FLOAT, NV_INTERNALFORMAT_SAMPLE_QUERY, NV_INTERNAL_FORMAT_SAMPLE_QUERY, NV_LIGHT_MAX_EXPONENT, NV_MULTISAMPLE_COVERAGE, NV_MULTISAMPLE_FILTER_HINT, NV_PACKED_DEPTH_STENCIL, NV_PATH_RENDERING, NV_PATH_RENDERING_SHARED_EDGE, NV_PIXEL_DATA_RANGE, NV_POINT_SPRITE, NV_PRIMITIVE_RESTART, NV_ROBUSTNESS_VIDEO_MEMORY_PURGE, NV_SHADER_ATOMIC_INT64, NV_SHADER_ATOMIC_FP16_VECTOR, NV_SHADER_ATOMIC_FLOAT64, NV_SHADER_ATOMIC_FLOAT, NV_SAMPLE_MASK_OVERRIDE_COVERAGE, NV_SAMPLE_LOCATIONS, NV_SHADER_BUFFER_LOAD, NV_SHADER_BUFFER_STORE, NV_STEREO_VIEW_RENDERING, NV_SHADER_THREAD_SHUFFLE, NV_SHADER_THREAD_GROUP, NV_TEXGEN_REFLECTION, NV_TEXTURE_COMPRESSION_VTC, NV_TEXTURE_BARRIER, NV_TEXTURE_MULTISAMPLE, NV_TRANSFORM_FEEDBACK, NV_TRANSFORM_FEEDBACK2, NV_UNIFORM_BUFFER_UNIFIED_MEMORY, NV_VERTEX_ARRAY_RANGE, NV_VERTEX_ARRAY_RANGE2, NV_VERTEX_ATTRIB_INTEGER_64BIT, NV_VIEWPORT_ARRAY2, NV_VERTEX_BUFFER_UNIFIED_MEMORY, NV_VIEWPORT_SWIZZLE, NVX_BLEND_EQUATION_ADVANCED_MULTI_DRAW_BUFFERS, NVX_CONDITIONAL_RENDER, NVX_GPU_MEMORY_INFO,

		// OVR

		OVR_MULTIVIEW, OVR_MULTIVIEW2,

		// WGL

		WGL_AMD_GPU_ASSOCIATION,

		WGL_ARB_BUFFER_REGION, WGL_ARB_CONTEXT_FLUSH_CONTROL, WGL_ARB_CREATE_CONTEXT, WGL_ARB_CREATE_CONTEXT_PROFILE, WGL_ARB_CREATE_CONTEXT_ROBUSTNESS, WGL_ARB_EXTENSIONS_STRING, WGL_ARB_FRAMEBUFFER_SRGB, WGL_ARB_MAKE_CURRENT_READ, WGL_ARB_MULTISAMPLE, WGL_ARB_PBUFFER, WGL_ARB_PIXEL_FORMAT, WGL_ARG_PIXEL_FORMAT_FLOAT, WGL_ARB_RENDER_TEXTURE, WGL_ARB_ROBUSTNESS_APPLICATION_ISOLATION,

		WGL_ATI_PIXEL_FORMAT_FLOAT,

		// WGL EXT

		WGL_EXT_CREATE_CONTEXT_ES2_PROFILE, WGL_EXT_CREATE_CONTEXT_ES_PROFILE, WGL_EXT_DEPTH_FLOAT, WGL_EXT_EXTENSIONS_STRING, WGL_EXT_FRAMEBUFFER_SRGB, WGL_EXT_PIXEL_FORMAT_PACKED_FLOAT, WGL_EXT_SWAP_CONTROL,

		// WGL NV

		WGL_NV_COPY_IMAGE, WGL_NV_DELAY_BEFORE_SWAP, WGL_NV_DX_INTEROP, WGL_NV_FLOAT_BUFFER, WGL_NV_GPU_AFFINITY, WGL_NV_MULTISAMPLE_COVERAGE, WGL_NV_RENDER_DEPTH_TEXTURE, WGL_NV_RENDER_TEXTURE_RECTANGLE, WGL_NV_SWAP_GROUP, WGL_NV_VERTEX_ARRAY_RANGE

		//

	}

	// initialize OpenGL and GL Features Set
	static {
		// create a dummy window to create a core profile context to retrieve information about all supported OpenGL Capabilities
		glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		final long dummy_window = glfwCreateWindow(0, 0, "", NULL, NULL), restore_window = glfwGetCurrentContext();

		glfwMakeContextCurrent(dummy_window);
		GLCapabilities caps = GL.createCapabilities();
		glfwMakeContextCurrent(restore_window);
		glfwDestroyWindow(dummy_window);

		int version = 11;

		VERSIONS.add(11); // OpenGL 1.1 must be supported for LWJGL
		FEATURES.add(EXT_VERTEX_ARRAY);
		FEATURES.add(EXT_POLYGON_OFFSET);
		FEATURES.add(EXT_TEXTURE);
		FEATURES.add(EXT_COPY_TEXTURE);
		FEATURES.add(EXT_SUBTEXTURE);
		FEATURES.add(EXT_TEXTURE_OBJECT);

		if (caps.OpenGL12) {
			version = 12;
			VERSIONS.add(12);
		}

		if (caps.OpenGL13) {
			version = 13;
			VERSIONS.add(13);
			//GL13 extensions
			FEATURES.add(ARB_TEXTURE_COMPRESSION);
			FEATURES.add(ARB_TEXTURE_CUBE_MAP);
			FEATURES.add(ARB_MULTISAMPLE);
			FEATURES.add(ARB_MULTITEXTURE);
			FEATURES.add(ARB_TEXTURE_ENV_ADD);
			FEATURES.add(ARB_TEXTURE_ENV_COMBINE);
			FEATURES.add(ARB_TEXTURE_ENV_DOT3);
			FEATURES.add(ARB_TEXTURE_BORDER_CLAMP);
			FEATURES.add(ARB_TRANSPOSE_MATRIX);
		}

		if (caps.OpenGL14) {
			version = 14;
			VERSIONS.add(14);
			//GL14 extensions
			FEATURES.add(SGIS_GENERATE_MIPMAP);
			FEATURES.add(NV_BLEND_SQUARE);
			FEATURES.add(ARB_DEPTH_TEXTURE);
			FEATURES.add(ARB_SHADOW);
			FEATURES.add(EXT_FOG_COORD);
			FEATURES.add(EXT_MULTI_DRAW_ARRAYS);
			FEATURES.add(ARB_POINT_PARAMETERS);
			FEATURES.add(EXT_SECONDARY_COLOR);
			FEATURES.add(EXT_BLEND_FUNC_SEPARATE);
			FEATURES.add(EXT_STENCIL_WRAP);
			FEATURES.add(ARB_TEXTURE_ENV_CROSSBAR);
			FEATURES.add(EXT_TEXTURE_LOD_BIAS);
			FEATURES.add(ARB_TEXTURE_MIRRORED_REPEAT);
			FEATURES.add(ARB_WINDOW_POS);
		}

		if (caps.OpenGL15) {
			version = 15;
			VERSIONS.add(15);
			//GL15 extensions
			FEATURES.add(ARB_VERTEX_BUFFER_OBJECT);
			FEATURES.add(ARB_OCCLUSION_QUERY);
			FEATURES.add(EXT_SHADOW_FUNCS);
		}

		if (caps.OpenGL20) {
			version = 20;
			VERSIONS.add(20);
			//GL20 extensions
			FEATURES.add(ARB_SHADER_OBJECTS);
			FEATURES.add(ARB_VERTEX_SHADER);
			FEATURES.add(ARB_FRAGMENT_SHADER);
			FEATURES.add(ARB_SHADING_LANGUAGE_100);
			FEATURES.add(ARB_DRAW_BUFFERS);
			FEATURES.add(ARB_TEXTURE_NON_POWER_OF_TWO);
			FEATURES.add(ARB_POINT_SPRITE);
			FEATURES.add(ATI_SEPARATE_STENCIL);
			FEATURES.add(EXT_STENCIL_TWO_SIDE);
		}

		if (caps.OpenGL21) {
			version = 21;
			VERSIONS.add(21);
			//GL21 extensions
			FEATURES.add(ARB_PIXEL_BUFFER_OBJECT);
			FEATURES.add(EXT_TEXTURE_SRGB);
		}

		if (caps.OpenGL30) {
			version = 30;
			VERSIONS.add(30);
			//GL30 extensions
			FEATURES.add(EXT_GPU_SHADER4);
			FEATURES.add(NV_CONDITIONAL_RENDER);
			FEATURES.add(APPLE_FLUSH_BUFFER_RANGE);
			FEATURES.add(ARB_COLOR_BUFFER_FLOAT);
			FEATURES.add(NV_DEPTH_BUFFER_FLOAT);
			FEATURES.add(ARB_TEXTURE_FLOAT);
			FEATURES.add(EXT_PACKED_FLOAT);
			FEATURES.add(EXT_TEXTURE_SHARED_EXPONENT);
			FEATURES.add(EXT_FRAMEBUFFER_OBJECT);
			FEATURES.add(NV_HALF_FLOAT);
			FEATURES.add(ARB_HALF_FLOAT_PIXEL);
			FEATURES.add(EXT_FRAMEBUFFER_MULTISAMPLE);
			FEATURES.add(EXT_FRAMEBUFFER_BLIT);
			FEATURES.add(EXT_TEXTURE_INTEGER);
			FEATURES.add(EXT_TEXTURE_ARRAY);
			FEATURES.add(EXT_PACKED_DEPTH_STENCIL);
			FEATURES.add(EXT_DRAW_BUFFERS2);
			FEATURES.add(EXT_TEXTURE_COMPRESSION_RGTC);
			FEATURES.add(EXT_TRANSFORM_FEEDBACK);
			FEATURES.add(APPLE_VERTEX_ARRAY_OBJECT);
			FEATURES.add(EXT_FRAMEBUFFER_SRGB);
		}

		if (caps.OpenGL31) {
			version = 31;
			VERSIONS.add(31);
			//GL31 extensions
			FEATURES.add(ARB_DRAW_INSTANCED);
			FEATURES.add(ARB_COPY_BUFFER);
			FEATURES.add(NV_PRIMITIVE_RESTART);
			FEATURES.add(ARB_TEXTURE_BUFFER_OBJECT);
			FEATURES.add(ARB_TEXTURE_RECTANGLE);
			FEATURES.add(ARB_UNIFORM_BUFFER_OBJECT);
		}

		if (caps.OpenGL32) {
			version = 31;
			VERSIONS.add(32);
			//GL32 extensions
			FEATURES.add(ARB_VERTEX_ARRAY_BGRA);
			FEATURES.add(ARB_DRAW_ELEMENTS_BASE_VERTEX);
			FEATURES.add(ARB_FRAGMENT_COORD_CONVENTIONS);
			FEATURES.add(ARB_PROVOKING_VERTEX);
			FEATURES.add(ARB_SEAMLESS_CUBE_MAP);
			FEATURES.add(ARB_TEXTURE_MULTISAMPLE);
			FEATURES.add(ARB_DEPTH_CLAMP);
			FEATURES.add(ARB_GEOMETRY_SHADER4);
			FEATURES.add(ARB_SYNC);
		}

		if (caps.OpenGL33) {
			version = 33;
			VERSIONS.add(33);
			//GL33 extensions
			FEATURES.add(ARB_SHADER_BIT_ENCODING);
			FEATURES.add(ARB_BLEND_FUNC_EXTENDED);
			FEATURES.add(ARB_EXPLICIT_ATTRIB_LOCATION);
			FEATURES.add(ARB_OCCLUSION_QUERY2);
			FEATURES.add(ARB_SAMPLER_OBJECTS);
			FEATURES.add(ARB_TEXTURE_RGB10_A2UI);
			FEATURES.add(ARB_TEXTURE_SWIZZLE);
			FEATURES.add(ARB_TIMER_QUERY);
			FEATURES.add(ARB_INSTANCED_ARRAYS);
			FEATURES.add(ARB_VERTEX_TYPE_2_10_10_10_REV);
		}

		if (caps.OpenGL40) {
			version = 40;
			VERSIONS.add(40);
			//GL40 extensions
			FEATURES.add(ARB_TEXTURE_QUERY_LOD);
			FEATURES.add(ARB_DRAW_BUFFERS_BLEND);
			FEATURES.add(ARB_DRAW_INDIRECT);
			FEATURES.add(ARB_GPU_SHADER5);
			FEATURES.add(ARB_GPU_SHADER_FP64);
			FEATURES.add(ARB_SAMPLE_SHADING);
			FEATURES.add(ARB_SHADER_SUBROUTINE);
			FEATURES.add(ARB_TESSELLATION_SHADER);
			FEATURES.add(ARB_TEXTURE_BUFFER_OBJECT_RGB32);
			FEATURES.add(ARB_TEXTURE_CUBE_MAP_ARRAY);
			FEATURES.add(ARB_TEXTURE_GATHER);
			FEATURES.add(ARB_TRANSFORM_FEEDBACK2);
			FEATURES.add(ARB_TRANSFORM_FEEDBACK3);
		}

		if (caps.OpenGL41) {
			version = 41;
			VERSIONS.add(41);
			//GL41 extensions
			FEATURES.add(ARB_ES2_COMPATIBILITY);
			FEATURES.add(ARB_GET_PROGRAM_BINARY);
			FEATURES.add(ARB_SEPARATE_SHADER_OBJECTS);
			FEATURES.add(ARB_SHADER_PRECISION);
			FEATURES.add(ARB_VERTEX_ATTRIB_64BIT);
			FEATURES.add(ARB_VIEWPORT_ARRAY);

		}

		if (caps.OpenGL42) {
			version = 42;
			VERSIONS.add(42);
			//GL42 extensions
			FEATURES.add(ARB_TEXTURE_COMPRESSION_BPTC);
			FEATURES.add(ARB_COMPRESSED_TEXTURE_PIXEL_STORAGE);
			FEATURES.add(ARB_SHADER_ATOMIC_COUNTERS);
			FEATURES.add(ARB_TEXTURE_STORAGE);
			FEATURES.add(ARB_TRANSFORM_FEEDBACK_INSTANCED);
			FEATURES.add(ARB_BASE_INSTANCE);
			FEATURES.add(ARB_SHADER_IMAGE_LOAD_STORE);
			FEATURES.add(ARB_CONSERVATIVE_DEPTH);
			FEATURES.add(ARB_SHADING_LANGUAGE_420PACK);
			FEATURES.add(ARB_INTERNALFORMAT_QUERY);
			FEATURES.add(ARB_MAP_BUFFER_ALIGNMENT);
		}

		if (caps.OpenGL43) {
			version = 43;
			VERSIONS.add(43);
			//GL43 extensions
			FEATURES.add(ARB_ARRAY_OF_ARRAYS);
			FEATURES.add(ARB_ES3_COMPATIBILITY);
			FEATURES.add(ARB_CLEAR_BUFFER_OBJECT);
			FEATURES.add(ARB_COMPUTE_SHADER);
			FEATURES.add(ARB_COPY_IMAGE);
			FEATURES.add(ARB_DEBUG_GROUP);
			FEATURES.add(ARB_DEBUG_LABEL);
			FEATURES.add(ARB_DEBUG_OUTPUT2);
			FEATURES.add(ARB_DEBUG_OUTPUT);
			FEATURES.add(ARB_EXPLICIT_UNIFORM_LOCATION);
			FEATURES.add(ARB_FRAGMENT_LAYER_VIEWPORT);
			FEATURES.add(ARB_FRAMEBUFFER_NO_ATTACHMENTS);
			FEATURES.add(ARB_INTERNALFORMAT_QUERY2);
			FEATURES.add(ARB_INVALIDATE_SUBDATA);
			FEATURES.add(ARB_MULTI_DRAW_INDIRECT);
			FEATURES.add(ARB_PROGRAM_INTERFACE_QUERY);
			FEATURES.add(ARB_ROBUST_BUFFER_ACCESS_BEHAVIOR);
			FEATURES.add(ARB_SHADER_IMAGE_SIZE);
			FEATURES.add(ARB_SHADER_STORAGE_BUFFER_OBJECT);
			FEATURES.add(ARB_STENCIL_TEXTURING);
			FEATURES.add(ARB_TEXTURE_BUFFER_RANGE);
			FEATURES.add(ARB_TEXTURE_QUERY_LEVELS);
			FEATURES.add(ARB_TEXTURE_STORAGE_MULTISAMPLE);
			FEATURES.add(ARB_TEXTURE_VIEW);
			FEATURES.add(ARB_VERTEX_ATTRIB_BINDING);
		}

		if (caps.OpenGL44) {
			version = 44;
			VERSIONS.add(44);
			//GL44 extensions
			FEATURES.add(ARB_BUFFER_STORAGE);
			FEATURES.add(ARB_CLEAR_TEXTURE);
			FEATURES.add(ARB_ENHANCED_LAYOUTS);
			FEATURES.add(ARB_MULTI_BIND);
			FEATURES.add(ARB_QUERY_BUFFER_OBJECT);
			FEATURES.add(ARB_TEXTURE_MIRROR_CLAMP_TO_EDGE);
			FEATURES.add(ARB_TEXTURE_STENCIL8);
			FEATURES.add(ARB_VERTEX_TYPE_10F_11F_11F_REV);
		}

		if (caps.OpenGL45) {
			version = 45;
			VERSIONS.add(45);
			//GL45 extensions
			FEATURES.add(ARB_CLIP_CONTROL);
			FEATURES.add(ARB_CULL_DISTANCE);
			FEATURES.add(ARB_ES31_COMPATIBILITY);
			FEATURES.add(ARB_CONDITIONAL_RENDER_INVERTED);
			FEATURES.add(KHR_CONTEXT_FLUSH_CONTROL);
			FEATURES.add(ARB_DERIVATIVE_CONTROL);
			FEATURES.add(ARB_DIRECT_STATE_ACCESS);
			FEATURES.add(ARB_GET_TEXTURE_SUB_IMAGE);
			FEATURES.add(KHR_ROBUSTNESS);
			FEATURES.add(ARB_SHADER_TEXTURE_IMAGE_SAMPLES);
			FEATURES.add(ARB_TEXTURE_BARRIER);
		}

		addDetectedFeatures(caps);

		VERSION = version;
	}

	private static void addDetectedFeatures(GLCapabilities caps) {
		// "AMD" extensions
		if (caps.GL_AMD_blend_minmax_factor)
			FEATURES.add(AMD_BLEND_MINMAX_FACTOR);
		if (caps.GL_AMD_conservative_depth)
			FEATURES.add(AMD_CONSERVATIVE_DEPTH);
		if (caps.GL_AMD_debug_output) FEATURES.add(AMD_DEBUG_OUTPUT);
		if (caps.GL_AMD_depth_clamp_separate)
			FEATURES.add(AMD_DEPTH_CLAMP_SEPERATE);
		if (caps.GL_AMD_draw_buffers_blend)
			FEATURES.add(AMD_DRAW_BUFFERS_BLEND);
		if (caps.GL_AMD_gcn_shader) FEATURES.add(AMD_GCN_SHADER);
		if (caps.GL_AMD_gpu_shader_int64)
			FEATURES.add(AMD_GPU_SHADER_INT_64);
		if (caps.GL_AMD_interleaved_elements)
			FEATURES.add(AMD_INTERLEAVED_ELEMENTS);
		if (caps.GL_AMD_occlusion_query_event)
			FEATURES.add(AMD_OCCLUSION_QUERY_EVENT);
		if (caps.GL_AMD_performance_monitor)
			FEATURES.add(AMD_PERFORMANCE_MONITOR);
		if (caps.GL_AMD_pinned_memory) FEATURES.add(AMD_PINNED_MEMORY);
		if (caps.GL_AMD_query_buffer_object)
			FEATURES.add(AMD_QUERY_BUFFER_OBJECT);
		if (caps.GL_AMD_sample_positions)
			FEATURES.add(AMD_SAMPLE_POSITIONS);
		if (caps.GL_AMD_seamless_cubemap_per_texture)
			FEATURES.add(AMD_SEAMLESS_CUBEMAP_PER_TEXTURE);
		if (caps.GL_AMD_shader_atomic_counter_ops)
			FEATURES.add(AMD_SHADER_ATOMIC_COUNTER_OPS);
		if (caps.GL_AMD_shader_stencil_export)
			FEATURES.add(AMD_SHADER_STENCIL_EXPORT);
		if (caps.GL_AMD_shader_trinary_minmax)
			FEATURES.add(AMD_SHADER_TRINARY_MINMAX);
		if (caps.GL_AMD_sparse_texture) FEATURES.add(AMD_SPARSE_TEXTURE);
		if (caps.GL_AMD_stencil_operation_extended)
			FEATURES.add(AMD_STENCIL_OPERATION_EXTENDED);
		if (caps.GL_AMD_texture_texture4)
			FEATURES.add(AMD_TEXTURE_TEXTURE4);
		if (caps.GL_AMD_transform_feedback3_lines_triangles)
			FEATURES.add(AMD_TRANSFORM_FEEDBACK3_LINES_TRIANGLES);
		if (caps.GL_AMD_transform_feedback4)
			FEATURES.add(AMD_TRANSFORM_FEEDBACK_4);
		if (caps.GL_AMD_vertex_shader_layer)
			FEATURES.add(AMD_VERTEX_SHADER_LAYER);
		if (caps.GL_AMD_vertex_shader_tessellator)
			FEATURES.add(AMD_VERTEX_SHADER_TESSELLATOR);
		if (caps.GL_AMD_vertex_shader_viewport_index)
			FEATURES.add(AMD_VERTEX_SHADER_VIEWPORT_INDEX);

		// "NVidia" extensions
		if (caps.GL_NV_bindless_multi_draw_indirect)
			FEATURES.add(NV_BINDLESS_MULTI_DRAW_INDIRECT);
		if (caps.GL_NV_bindless_multi_draw_indirect_count)
			FEATURES.add(NV_BINDLESS_MULTI_DRAW_INDIRECT_COUNT);
		if (caps.GL_NV_bindless_texture)
			FEATURES.add(NV_BINDLESS_TEXTURE);
		if (caps.GL_NV_blend_equation_advanced)
			FEATURES.add(NV_BLEND_EQUATION_ADVANCED);
		if (caps.GL_NV_blend_equation_advanced_coherent)
			FEATURES.add(NV_BLEND_EQUATION_ADVANCED_COHERENT);
		if (caps.GL_NV_blend_square)
			FEATURES.add(NV_BLEND_SQUARE);
		if (caps.GL_NV_clip_space_w_scaling)
			FEATURES.add(NV_CLIP_SPACE_W_SCALING);
		if (caps.GL_NV_command_list)
			FEATURES.add(NV_COMMAND_LIST);
		if (caps.GL_NV_conditional_render)
			FEATURES.add(NV_CONDITIONAL_RENDER);
		if (caps.GL_NV_conservative_raster)
			FEATURES.add(NV_CONSERVATIVE_RASTER);
		if (caps.GL_NV_conservative_raster_dilate)
			FEATURES.add(NV_CONSERVATIVE_RASTER_DILATE);
		if (caps.GL_NV_conservative_raster_pre_snap_triangles)
			FEATURES.add(NV_CONSERVATIVE_RASTER_PRE_SNAP_TRIANGLES);
		if (caps.GL_NV_copy_depth_to_color)
			FEATURES.add(NV_COPY_DEPTH_TO_COLOR);
		if (caps.GL_NV_copy_image)
			FEATURES.add(NV_COPY_IMAGE);
		if (caps.GL_NV_deep_texture3D)
			FEATURES.add(NV_DEEP_TEXTURE3D);
		if (caps.GL_NV_depth_buffer_float)
			FEATURES.add(NV_DEPTH_BUFFER_FLOAT);
		if (caps.GL_NV_depth_clamp)
			FEATURES.add(NV_DEPTH_CLAMP);
		if (caps.GL_NV_draw_texture)
			FEATURES.add(NV_DRAW_TEXTURE);
		if (caps.GL_NV_draw_vulkan_image)
			FEATURES.add(NV_DRAW_VULKAN_IMAGE);
		if (caps.GL_NV_ES3_1_compatibility)
			FEATURES.add(NV_ES3_1_COMPATIBILITY);
		if (caps.GL_NV_explicit_multisample)
			FEATURES.add(NV_EXPLICIT_MULTISAMPLE);
		if (caps.GL_NV_fence)
			FEATURES.add(NV_FENCE);
		if (caps.GL_NV_fill_rectangle)
			FEATURES.add(NV_FILL_RECTANGLE);
		if (caps.GL_NV_float_buffer)
			FEATURES.add(NV_FLOAT_BUFFER);
		if (caps.GL_NV_fog_distance)
			FEATURES.add(NV_FOG_DISTANCE);
		if (caps.GL_NV_fragment_coverage_to_color)
			FEATURES.add(NV_FRAGMENT_COVERAGE_TO_COLOR);
		if (caps.GL_NV_fragment_program4)
			FEATURES.add(NV_FRAGMENT_PROGRAM4);
		if (caps.GL_NV_fragment_program_option)
			FEATURES.add(NV_FRAGMENT_PROGRAM_OPTION);
		if (caps.GL_NV_framebuffer_mixed_samples)
			FEATURES.add(NV_FRAMEBUFFER_MIXED_SAMPLES);
		if (caps.GL_NV_framebuffer_multisample_coverage)
			FEATURES.add(NV_FRAMEBUFFER_MULTISAMPLE_COVERAGE);
		if (caps.GL_NV_geometry_shader4)
			FEATURES.add(NV_GEOMETRY_SHADER4);
		if (caps.GL_NV_geometry_shader_passthrough)
			FEATURES.add(NV_GEOMETRY_SHADER_PASSTHROUGH);
		if (caps.GL_NV_gpu_multicast)
			FEATURES.add(NV_GPU_MULTICAST);
		if (caps.GL_NV_gpu_shader5)
			FEATURES.add(NV_GPU_SHADER5);
		if (caps.GL_NV_half_float)
			FEATURES.add(NV_HALF_FLOAT);
		if (caps.GL_NV_internalformat_sample_query)
			FEATURES.add(NV_INTERNALFORMAT_SAMPLE_QUERY);
		if (caps.GL_NV_light_max_exponent)
			FEATURES.add(NV_LIGHT_MAX_EXPONENT);
		if (caps.GL_NV_multisample_coverage)
			FEATURES.add(NV_MULTISAMPLE_COVERAGE);
		if (caps.GL_NV_multisample_filter_hint)
			FEATURES.add(NV_MULTISAMPLE_FILTER_HINT);
		if (caps.GL_NV_packed_depth_stencil)
			FEATURES.add(NV_PACKED_DEPTH_STENCIL);
		if (caps.GL_NV_path_rendering)
			FEATURES.add(NV_PATH_RENDERING);
		if (caps.GL_NV_path_rendering_shared_edge)
			FEATURES.add(NV_PATH_RENDERING_SHARED_EDGE);
		if (caps.GL_NV_pixel_data_range)
			FEATURES.add(NV_PIXEL_DATA_RANGE);
		if (caps.GL_NV_point_sprite)
			FEATURES.add(NV_POINT_SPRITE);
		if (caps.GL_NV_primitive_restart)
			FEATURES.add(NV_PRIMITIVE_RESTART);
		if (caps.GL_NV_robustness_video_memory_purge)
			FEATURES.add(NV_ROBUSTNESS_VIDEO_MEMORY_PURGE);
		if (caps.GL_NV_sample_locations)
			FEATURES.add(NV_SAMPLE_LOCATIONS);
		if (caps.GL_NV_sample_mask_override_coverage)
			FEATURES.add(NV_SAMPLE_MASK_OVERRIDE_COVERAGE);
		if (caps.GL_NV_shader_atomic_float)
			FEATURES.add(NV_SHADER_ATOMIC_FLOAT);
		if (caps.GL_NV_shader_atomic_float64)
			FEATURES.add(NV_SHADER_ATOMIC_FLOAT64);
		if (caps.GL_NV_shader_atomic_fp16_vector)
			FEATURES.add(NV_SHADER_ATOMIC_FP16_VECTOR);
		if (caps.GL_NV_shader_atomic_int64)
			FEATURES.add(NV_SHADER_ATOMIC_INT64);
		if (caps.GL_NV_shader_buffer_load)
			FEATURES.add(NV_SHADER_BUFFER_LOAD);
		if (caps.GL_NV_shader_buffer_store)
			FEATURES.add(NV_SHADER_BUFFER_STORE);
		if (caps.GL_NV_shader_thread_group)
			FEATURES.add(NV_SHADER_THREAD_GROUP);
		if (caps.GL_NV_shader_thread_shuffle)
			FEATURES.add(NV_SHADER_THREAD_SHUFFLE);
		if (caps.GL_NV_stereo_view_rendering)
			FEATURES.add(NV_STEREO_VIEW_RENDERING);
		if (caps.GL_NV_texgen_reflection)
			FEATURES.add(NV_TEXGEN_REFLECTION);
		if (caps.GL_NV_texture_barrier)
			FEATURES.add(NV_TEXTURE_BARRIER);
		if (caps.GL_NV_texture_compression_vtc)
			FEATURES.add(NV_TEXTURE_COMPRESSION_VTC);
		if (caps.GL_NV_texture_multisample)
			FEATURES.add(NV_TEXTURE_MULTISAMPLE);
		if (caps.GL_NV_transform_feedback)
			FEATURES.add(NV_TRANSFORM_FEEDBACK);
		if (caps.GL_NV_transform_feedback2)
			FEATURES.add(NV_TRANSFORM_FEEDBACK2);
		if (caps.GL_NV_uniform_buffer_unified_memory)
			FEATURES.add(NV_UNIFORM_BUFFER_UNIFIED_MEMORY);
		if (caps.GL_NV_vertex_array_range)
			FEATURES.add(NV_VERTEX_ARRAY_RANGE);
		if (caps.GL_NV_vertex_array_range2)
			FEATURES.add(NV_VERTEX_ARRAY_RANGE2);
		if (caps.GL_NV_vertex_attrib_integer_64bit)
			FEATURES.add(NV_VERTEX_ATTRIB_INTEGER_64BIT);
		if (caps.GL_NV_vertex_buffer_unified_memory)
			FEATURES.add(NV_VERTEX_BUFFER_UNIFIED_MEMORY);
		if (caps.GL_NV_viewport_array2)
			FEATURES.add(NV_VIEWPORT_ARRAY2);
		if (caps.GL_NV_viewport_swizzle)
			FEATURES.add(NV_VIEWPORT_SWIZZLE);

		// "NVidia X" extensions
		if (caps.GL_NVX_blend_equation_advanced_multi_draw_buffers)
			FEATURES.add(NVX_BLEND_EQUATION_ADVANCED_MULTI_DRAW_BUFFERS);
		if (caps.GL_NVX_conditional_render)
			FEATURES.add(NVX_CONDITIONAL_RENDER);
		if (caps.GL_NVX_gpu_memory_info)
			FEATURES.add(NVX_GPU_MEMORY_INFO);

		// "Khronos" extensions
		if (caps.GL_KHR_blend_equation_advanced)
			FEATURES.add(KHR_BLEND_EQUATION_ADVANCED);
		if (caps.GL_KHR_blend_equation_advanced_coherent)
			FEATURES.add(KHR_BLEND_EQUATION_ADVANCED_COHERENT);
		if (caps.GL_KHR_context_flush_control)
			FEATURES.add(KHR_CONTEXT_FLUSH_CONTROL);
		if (caps.GL_KHR_debug)
			FEATURES.add(KHR_DEBUG);
		if (caps.GL_KHR_no_error)
			FEATURES.add(KHR_NO_ERROR);
		if (caps.GL_KHR_robust_buffer_access_behavior)
			FEATURES.add(KHR_ROBUST_BUFFER_ACCESS_BEHAVIOR);
		if (caps.GL_KHR_robustness)
			FEATURES.add(KHR_ROBUSTNESS);
		if (caps.GL_KHR_texture_compression_astc_hdr)
			FEATURES.add(KHR_TEXTURE_COMPRESSION_ASTC_HDR);
		if (caps.GL_KHR_texture_compression_astc_ldr)
			FEATURES.add(KHR_TEXTURE_COMPRESSION_ASTC_LDR);
		if (caps.GL_KHR_texture_compression_astc_sliced_3d)
			FEATURES.add(KHR_TEXTURE_COMPRESSION_ASTC_SLICED_3D);

		// "OVR" extensions
		if (caps.GL_OVR_multiview)
			FEATURES.add(OVR_MULTIVIEW);
		if (caps.GL_OVR_multiview2)
			FEATURES.add(OVR_MULTIVIEW2);
	}

	/**
	 * Checks if the specified feature is supported by this platform.
	 * <p>
	 * <em>Note: this method checks for platform support of a feature
	 * regardless
	 * of OpenGL context.</em>
	 * </p>
	 *
	 * @param feature the feature to check for
	 * @return true if the feature is supported
	 */
	public static boolean supports(Feature feature) {
		return FEATURES.contains(feature);
	}

	/**
	 * Checks whether the specified version is supported.
	 *
	 * @param version the version to check for
	 * @return true if the version is supported
	 */
	public static boolean supports(int version) {
		return VERSIONS.contains(version);
	}

	/**
	 * Depending on the calling method and what methods will be used in OpenGL specifically, this will initialize the {@link GLCapabilities} features.
	 */
	public static void initialize() {
		GL.createCapabilities();
	}

	/**
	 * Gets the state of OpenGL for the calling Thread. If an OpenGL context has been established for the calling
	 * Thread, this method will return true, otherwise it will return false.
	 *
	 * @return true if an OpenGL context is initialized for this thread; false otherwise
	 * @implNote the result of this method depends on the calling thread
	 */
	public static boolean initialized() {
		try {
			GL.getCapabilities();
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}

	/**
	 * Gets the latest version of OpenGL that this platform supports
	 *
	 * @return the latest version of OpenGL that this platform supports
	 */
	public static int getVersion() {
		return VERSION;
	}

	public static boolean checkError(Class<?> caller) {
		return checkError(caller, "An OpenGL error occured");
	}

	/**
	 * Checks for a GL error and returns false if there is no error, or true if there is an error. If {@code exceptIf}
	 * is true and there is an error, a {@link GLError} will be thrown.
	 *
	 * @param caller the caller of this method
	 * @param exceptIf if true, throws an error if one is found, otherwise this method returns normally
	 * @return true if an error was thrown, false otherwise; throws a GLError if exceptIf is true
	 */
	public static boolean checkError(Class<?> caller, boolean exceptIf) {
		int error = glGetError();
		if (error == GL_NO_ERROR) return false;
		else {
			if (exceptIf)
				throw new GLError(caller, GLError.Name.getName(error));
			return true;
		}
	}

	/**
	 * Checks for a GL error and returns false if there is no error, or if an error is found, a {@link GLError} is thrown.
	 *
	 * @param caller  the caller of this method
	 * @param message the message to display if there is an error
	 * @return false if there is no error; throws a GLError if an error is found
	 */
	public static boolean checkError(Class<?> caller, String message) {
		return checkError(caller, message, true);
	}

	/**
	 * Checks for a GL error and returns false if there is no error, or true if there is an error. If {@code exceptIf}
	 * is true and there is an error, a {@link GLError} will be thrown.
	 *
	 * @param caller   the caller of this method
	 * @param message  the message to display if there is an error and exceptIf is true
	 * @param exceptIf if true, throws an error if one is found, otherwise this method returns normally
	 * @return true if an error was thrown, false otherwise; throws a GLError if exceptIf is true
	 */
	public static boolean checkError(Class<?> caller, String message, boolean exceptIf) {
		int error = glGetError();
		if (error == GL_NO_ERROR) return false;
		else {
			if (exceptIf)
				throw new GLError(caller, message, GLError.Name.getName(error));
			return true;
		}
	}

}
