package helpers;

import org.lwjgl.glfw.GLFWMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.*;

public class Mouse extends GLFWMouseButtonCallback {

	/**
	 * Mouse buttons. See
	 * <a href="http://www.glfw.org/docs/latest/input.html#input_mouse_button">
	 * mouse button input</a> for how these are used.
	 */
	public static final int MOUSE_BUTTON_1 = 0x0, MOUSE_BUTTON_2 = 0x1, MOUSE_BUTTON_3 = 0x2, MOUSE_BUTTON_4 = 0x3,
			MOUSE_BUTTON_5 = 0x4, MOUSE_BUTTON_6 = 0x5, MOUSE_BUTTON_7 = 0x6, MOUSE_BUTTON_8 = 0x7,
			MOUSE_BUTTON_LAST = MOUSE_BUTTON_8, MOUSE_BUTTON_LEFT = MOUSE_BUTTON_1, MOUSE_BUTTON_RIGHT = MOUSE_BUTTON_2,
			MOUSE_BUTTON_MIDDLE = MOUSE_BUTTON_3;

	private static int[] buttonState = new int[MOUSE_BUTTON_LAST];
	private static int[] buttonDown = new int[MOUSE_BUTTON_LAST];

	public Mouse() {
		for (int i = 0; i < buttonState.length; i++) {
			buttonState[i] = -1;
		}
	}

	public void update() {
		for (int i = 0; i < buttonState.length; i++) {
			buttonState[i] = -1;
		}
	}

	public static boolean isDown(int button) {
		if (button <= MOUSE_BUTTON_LAST) {
			return buttonDown[button] == 1;
		}
		return false;
	}

	public static boolean isClicked(int button) {
		if (button <= MOUSE_BUTTON_LAST) {
			return buttonState[button] == 1;
		}
		return false;
	}

	public static boolean isReleased(int button) {
		if (button <= MOUSE_BUTTON_LAST) {
			return buttonState[button] == 0;
		}
		return false;
	}

	@Override
	public void invoke(long window, int button, int action, int mods) {
		if (button <= MOUSE_BUTTON_LAST) {
			buttonState[button] = action;
			buttonDown[button] = action != GLFW_RELEASE ? 1 : 0;

		}
	}
}