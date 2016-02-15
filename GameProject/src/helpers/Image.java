package helpers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.stb.STBImage.*;
public class Image {
	
	private final ByteBuffer image;
	
	private final int width, height;
	
	//RGBA - 32
	//RGB - 24
	private final int components;
	
	public Image(String path){
		ByteBuffer imageBuffer;
		
		try{
			imageBuffer = BufferUtil.file2ByteBuffer(path, 1024);
		}catch(IOException e){
			//logger.error("Could not find the image oat the path " + path + ".");
			throw new RuntimeException(e);
		}
		
		
		IntBuffer width = BufferUtils.createIntBuffer(1);
		IntBuffer height = BufferUtils.createIntBuffer(1);
		IntBuffer component = BufferUtils.createIntBuffer(1);

		image =  stbi_load_from_memory(imageBuffer, width, height, component, 0);
		this.width = width.get(0);
		this.height = height.get(0);
		this.components = component.get(0);
	}
	
	public ByteBuffer getBuffer(){
		return image;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getComponents() {
		return components;
	}
	
	public void dispose(){
		stbi_image_free(image);
	}
}
