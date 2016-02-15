package helpers;

import org.lwjgl.glfw.*;

public class Clock {

	private static boolean paused = false;
	public static double lastFrame;
	public static long totalTime;
	public static float deltaTime = 0, multiplier = 1;
	
	public static double getTime(){
		return GLFW.glfwGetTime() * 1000;
	}
	
	public static float getDelta() {
		double currentTime = getTime();
		int delta = (int)(currentTime - lastFrame);
		lastFrame = getTime();
		return delta * 0.01f;
	}
	
	public static float Delta() {
		if(paused){
			return 0;
		}else {
			return deltaTime * multiplier;
		}
	}
	
	public static float totalTime() {
		return totalTime;
	}
	
	public static float getMultiplier() {
		return multiplier;
	}
	
	public static void update() {
		deltaTime = getDelta();
		totalTime += deltaTime;
	}
	
	public static void setMultiplier(int change){
		if(multiplier + change < -1 && multiplier + change > 7){
			
		}else{
			multiplier =+ change;
		}
	}
	
	public static void Pause() {
		if(paused){
			paused = false;
		}else
			paused = true;
	}
}
