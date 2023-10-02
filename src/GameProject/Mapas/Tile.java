package GameProject.Mapas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import GameProject.main.Game;

public class Tile {
	
	public static BufferedImage TILE_WALL = Game.enviroment.getSprite(8*32, 0, 32, 32);
	public static BufferedImage TILE_GRASS = Game.enviroment.getSprite(5*32, 32, 23, 23);
	public static BufferedImage TILE_FLORES = Game.enviroment.getSprite(0, 32, 32, 32);
	public static BufferedImage TILE_ROAD = Game.enviroment.getSprite(2*32, 32, 32, 32);
	public static BufferedImage TILE_Ground = Game.enviroment.getSprite(10*32, 0, 32, 32);
	public static BufferedImage TILE_WATER = Game.enviroment.getSprite(12*32, 4*32, 32, 32);
	public static BufferedImage TILE_HOUSE = Game.enviroment.getSprite(0, 10*32, 70, 60);
	public static BufferedImage TILE_ROOF = Game.enviroment.getSprite(0, 9*32, 16, 90);
	public static BufferedImage TILE_Cerca = Game.enviroment.getSprite(32, 4*32, 32, 32);
	public static BufferedImage TILE_ROAD2 = Game.enviroment.getSprite(9*32, 0, 16, 16);
	
	private BufferedImage tile;
	private int x,y;
	
	public Tile(int x, int y, BufferedImage tile){
		this.x = x;
		this.y = y;
		this.tile = tile;
	}
	public void render(Graphics graficos){
		graficos.drawImage(tile, x - Camera.x, y - Camera.y, null);
	}

}
