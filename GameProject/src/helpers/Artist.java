package helpers;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import data.Texture;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
//import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {
	//Window WIDTH and HEIGHT
		public static final int WIDTH = 1280;
		public static final int HEIGHT = 960;
	//The window handler
	private static long window;
	
	private Texture texture;
	
	public static void BeginSession(){
		// Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
 
        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Tower Defense!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        
     // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (vidmode.width() - WIDTH) / 2,
            (vidmode.height() - HEIGHT) / 2
        );
 
        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
 
        // Make the window visible
        glfwShowWindow(window);
        
     // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
 
        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        
        //Set Ortho
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
     
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
	}

	public static void drawQuad(float xPos, float yPos, float width, float height){
		//DRAW RECTANGLE
        glBegin(GL_QUADS);
        glVertex2f(xPos, yPos);			//top left corner
        glVertex2f(xPos + width, yPos); //top right corner
        glVertex2f(xPos + width, yPos + height);//bottom right corner
        glVertex2f(xPos, yPos + height);//bottom left corner
        glEnd();

	}
	
	public static void drawQuadTex(Texture texture, float xPos, float yPos, float width, float height ){
		texture.bind();
		glTranslatef(xPos, yPos, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}

	public static void drawLine(float xPosA, float yPosA, float xPosB, float yPosB){
		//DRAW LINE
        glBegin(GL_LINES);
        glVertex2f(xPosA, yPosA);
        glVertex2f(xPosB, yPosB);
        glEnd();
	}
	
	public static Texture loadTexture(String path){
		Texture texture = new Texture(path);

		return texture;
	}
	
	public static long getwindow() {
		return window;
	}
}
