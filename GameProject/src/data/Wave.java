package data;

import java.util.ArrayList;
import static helpers.Clock.*;

public class Wave {

	private float timeSinceLastSpawn, spawnTime;
	private Enemy enemyType;
	private ArrayList<Enemy> enemyList;
	
	public Wave(float spawnTime, Enemy enemyType){
		this.enemyType = enemyType;
		this.spawnTime = spawnTime;
		this.timeSinceLastSpawn = 0;
		enemyList = new ArrayList<Enemy>();
	}
	
	public void update() {
		timeSinceLastSpawn += Delta();
		if(timeSinceLastSpawn > spawnTime){
			Spawn();
			timeSinceLastSpawn = 0;
		}
		
		for(Enemy e: enemyList){
			e.update();
			e.Draw();
		}
	}
	
	public void Spawn(){
		enemyList.add(new Enemy(enemyType.getTexture(), enemyType.getStartTile(), 64, 64, enemyType.getSpeed()));
		
	}
}
