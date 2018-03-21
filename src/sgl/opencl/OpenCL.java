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

package sgl.opencl;

import org.lwjgl.PointerBuffer;
import org.lwjgl.opencl.CL;
import org.lwjgl.opencl.CLCapabilities;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opencl.CL10.*;
import static org.lwjgl.system.MemoryUtil.memAllocInt;
import static org.lwjgl.system.MemoryUtil.memAllocPointer;

/**
 * @author link
 */
public enum OpenCL {
	;

	private static final Map<Long, CLCapabilities> PLATFORM_CACHE = new HashMap<>(1);
	private static final Map<Integer, Map<Long, CLCapabilities>> DEVICE_CACHE = new HashMap<>(2);

	/**
	 * Gets the available cl_platform_id's for this system.
	 * <p>
	 * <em>You must call {@link PointerBuffer#free()} on the returned buffer after usage; failure to do so will result
	 * in memory leaks.</em>
	 *
	 * @return a PointerBuffer of the specified size containing one or more cl_platform_id pointers
	 */
	public PointerBuffer getAvailablePlatforms() {
		PointerBuffer ptr = memAllocPointer(8);
		int result = clGetPlatformIDs(ptr, memAllocInt(1).put(1));
		if (result != CL_SUCCESS)
			throw new RuntimeException("A CL error occured: " + (result == CL_INVALID_VALUE ? "CL_INVALID_VALUE" : "CL_OUT_OF_HOST_MEMORY"));
		return ptr;
	}

	/**
	 * Gets the available cl_device_id's for the given platform and device_type.
	 * <p>
	 * <em>You must call {@link PointerBuffer#free()} on the returned buffer after usage; failure to do so will result
	 * in memory leaks.</em>
	 *
	 * @return a PointerBuffer of the specified size containing one or more cl_platform_id pointers
	 */
	public PointerBuffer getAvailableDevices(long platform, long device_type) {
		PointerBuffer ptr = memAllocPointer(8);
		int result = clGetDeviceIDs(platform, device_type, ptr, memAllocInt(1).put(1));
		if (result != CL_SUCCESS)
			throw new RuntimeException("A CL error occured: " + (result == CL_INVALID_VALUE ? "CL_INVALID_VALUE" : "CL_OUT_OF_HOST_MEMORY"));
		return ptr;
	}

	/**
	 * Creates a CLCapabilities instance for the given platform_id if it does not already exist, and then caches it.
	 * <em>This method is expensive for new platform IDs, so use at initialization or sparsely during runtime.</em>
	 *
	 * @param cl_platform_id
	 */
	public void initPlatform(long cl_platform_id) {
		if (!PLATFORM_CACHE.containsKey(Long.valueOf(cl_platform_id)))
			PLATFORM_CACHE.put(Long.valueOf(cl_platform_id), CL.createPlatformCapabilities(cl_platform_id));
	}

	/**
	 * Creates a CLCapabilities instance for the given platform_id if it does not already exist, and then caches it.
	 * <em>This method is expensive for new platform IDs, so use at initialization or sparsely during runtime.</em>
	 *
	 * @param cl_device_id
	 * @param cl_device_type the cl_device_type; one of {@link org.lwjgl.opencl.CL10#CL_DEVICE_TYPE_CPU DEVICE_TYPE_CPU}, {@link org.lwjgl.opencl.CL10#CL_DEVICE_TYPE_GPU DEVICE_TYPE_GPU}, or {@link org.lwjgl.opencl.CL10#CL_DEVICE_TYPE_ACCELERATOR DEVICE_TYPE_ACCELERATOR}
	 */
	public void initDevice(int cl_device_type, long cl_device_id, CLCapabilities platformCaps) {
		Map<Long, CLCapabilities> devices = DEVICE_CACHE.get(Integer.valueOf(cl_device_type));
		Long ptr = Long.valueOf(cl_device_id);
		if (cl_device_type == CL_DEVICE_TYPE_CPU || cl_device_type == CL_DEVICE_TYPE_GPU || cl_device_type == CL_DEVICE_TYPE_ACCELERATOR) {
			if (devices != null) {
				devices.computeIfAbsent(ptr, k -> CL.createDeviceCapabilities(cl_device_id, platformCaps));
			}
		} else {
			devices = new HashMap<>(1);
			devices.put(ptr, CL.createDeviceCapabilities(cl_device_id, platformCaps));
			DEVICE_CACHE.put(Integer.valueOf(cl_device_type), devices);
		}
	}

}
