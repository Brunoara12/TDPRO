package data;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import helpers.MousePosition;
import helpers.Artist;
import helpers.Artist.*;
import helpers.Keyboard;
import helpers.Mouse;

public class Player {
	
	private TileGrid grid;
	private TileType[] types;
	private int index;
	
	private MousePosition mousePos;
	private GLFWCursorPosCallback cursorPosCallback;
	private Mouse mouse;
	private GLFWMouseButtonCallback mouseButtonCallback;
	private Keyboard keyboard;
	private GLFWKeyCallback keyCallback;
	
	public Player(TileGrid grid){
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.index = 0;
		
		cursorPosCallback = new MousePosition();
		cursorPosCallback.set(Artist.getwindow());
		mouseButtonCallback = new Mouse();
		mouseButtonCallback.set(Artist.getwindow());
		keyCallback = new Keyboard();
		keyCallback.set(Artist.getwindow());
	}
	
	public void SetTile(){
		double yCoord;
		if (Math.floor((mousePos.getY() - 1)/64) == -1){
			yCoord = 0.0;
		}else{
			yCoord = Math.floor((mousePos.getY() - 1)/64);
		}
		grid.setTile(
				(int)Math.floor(mousePos.getX() / 64), 
				(int)yCoord, 
				types[index]);
	}
	
	public void update(){
		if(mouse.isClicked(mouse.MOUSE_BUTTON_1)){
			SetTile();

		}
		
		if(keyboard.isPressed(keyboard.KEY_RIGHT)){
			moveIndex();
		}
	}
	
	private void moveIndex(){
		index++;
		if(index > types.length - 1){
			index = 0;
		}
	}
}
