package xugl.glfw.io;

import xugl.io.KeyMap;
import xugl.io.KeyMaps;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;

/**
 * @author link
 */
public final class GLFWKeyMap implements KeyMap {

	public static final String GLFW_GENERIC = "glfw.default";

	static {
		KeyMaps.addMap(GLFW_GENERIC, new GLFWKeyMap());
	}

	private final KeyMaps[] map = new KeyMaps[GLFW_KEY_LAST];

	private GLFWKeyMap() {
	}

	@Override
	public int getKey(String key) {
		return 0;
	}

	@Override
	public String getKey(int key) {
		return null;
	}
}
