package helpers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import java.nio.ByteBuffer;



public class Texture {

	private int id;
	private int width, height;
	
	//GL_LINEAR - anti_aliasing HD
	//GL_NEAREST - aliasing SPRITE
	private static int filter = GL_LINEAR;
	private static int wrap = GL_REPEAT;
	
	public Texture(){
		this.id = glGenTextures();
	}
	
	public static Texture fromColor(Color color, int width, int height){
		ByteBuffer buffer = BufferUtil.createByteBuffer(width * height * 4);
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				buffer.put((byte)(color.r * 255.0f)).put((byte)(color.g * 255.0f)).put((byte)(color.b * 255.0f)).put((byte)(color.a * 255.0f));
			}
		}
		buffer.flip();
		
		return fromByteBuffer(buffer, width, height, 4);
		
		
	}
	
	public static Texture fromImage(Image image){
		Texture texture = fromByteBuffer(image.getBuffer(), image.getWidth(), image.getHeight(), image.getComponents());
		image.dispose();
		return texture;
	}
	
	public static Texture fromByteBuffer(ByteBuffer buffer, int width, int height, int components){
		Texture texture = new Texture();
		texture.bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, wrap);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, wrap);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, components == 4 ? GL_RGBA : GL_RGB, GL_UNSIGNED_BYTE, buffer);
		texture.unbind();
		
		return texture;
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public void dispose(){
		glDeleteTextures(id);
	}
	
	public static void setFilter(int filter){
		Texture.filter = filter;
	}
	
	public static void setWrap(int wrap) {
		Texture.wrap = wrap;
	}
	
	public static int getFilter() {
		return filter;
	}
	
	public static int getWrap() {
		return wrap;
	}
	
	public ByteBuffer getImage2D(int format){
		return getImage2D(format, GL_FLOAT);
	}
	
	public ByteBuffer getImage2D(int format, int type){
		int size = 4;
		
		switch(format){
		case GL_RGB:
		case GL_BGR:
			size = 3;
			break;
		}
		
		size = (int)(size * width * height * 4);
		
		ByteBuffer buffer = BufferUtil.createByteBuffer(size);
		return getImage2D(format, type, buffer);
	}

	public ByteBuffer getImage2D(int format, int type, ByteBuffer buffer) {
		glGetTexImage(GL_TEXTURE_2D, 0 , format, type, buffer);
		return buffer;
	}
	
	public int getID(){
		return id;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
