package helpers;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePosition extends GLFWCursorPosCallback{

	static double x, y;
	
	public MousePosition(){
		MousePosition.x = y = 0;
	}
	
	@Override
	public void invoke(long window, double xpos, double ypos) {
		MousePosition.x = xpos;
		MousePosition.y = ypos;
	}
	 public static double getX() {
		return x;
	}
	 
	 public static double getY() {
		return y;
	}
}
