package GameProject.Entidades;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import GameProject.Mapas.Camera;
import GameProject.Mapas.Mapas;
import GameProject.main.Game;


public class Entidades {
	
	public static BufferedImage FLOWER = Game.enviroment.getSprite(32, 32, 32, 32);
	public static BufferedImage WALL = Game.enviroment.getSprite(8*32, 0, 32, 32);
	public static BufferedImage Treecut = Game.enviroment.getSprite(0, 32, 64, 80);
	public static BufferedImage Water = Game.enviroment.getSprite(11*32, 4*32, 32, 32);
	public static BufferedImage Casa = Game.enviroment.getSprite(0, 9*32, 70, 40);
	public static BufferedImage Casa1 = Game.enviroment.getSprite(0, 10*32, 70, 65);
	public static BufferedImage Bluetree = Game.enviroment.getSprite(5*32, 9*32, 64,96);
	public static BufferedImage Redtree = Game.enviroment.getSprite(3*32, 9*32, 64,96);
	public static BufferedImage Greentree = Game.enviroment.getSprite(7*32, 9*32, 64,96);
	public static BufferedImage Browntree = Game.enviroment.getSprite(0, 12*32, 75,65);
	public static BufferedImage Purpletree = Game.enviroment.getSprite(3*32, 12*32, 75,65);
	public static BufferedImage Browntree1 = Game.enviroment.getSprite(0, 14*32, 70,32);
	public static BufferedImage Stone = Game.enviroment.getSprite(0, 15*32, 3*32, 3*32);
	public static BufferedImage Grass = Game.enviroment.getSprite(5*32, 32, 32, 32);
	public static BufferedImage Lago = Game.enviroment.getSprite(6*32, 12*32, 64, 64);
	
	public static BufferedImage Cerca1 = Game.enviroment.getSprite(5*32, 7*32, 16, 32);
	public static BufferedImage Cerca2 = Game.enviroment.getSprite(6*32, 7*32, 16, 32);
	public static BufferedImage Cerca3 = Game.enviroment.getSprite(8*32, 8*32, 32, 16);
	public static BufferedImage Cerca4 = Game.enviroment.getSprite(7*32, 8*32, 32, 16);
	
	public static BufferedImage Canto1 = Game.enviroment.getSprite(3*32, 6*32, 32, 32);
	public static BufferedImage Canto2 = Game.enviroment.getSprite(2*32, 5*32, 32, 32);
	public static BufferedImage Canto3 = Game.enviroment.getSprite(4*32, 4*32, 32, 32);
	public static BufferedImage Canto4 = Game.enviroment.getSprite(5*32, 5*32, 32, 32);
	
	public static BufferedImage Cerca5 = Game.enviroment.getSprite(32, 7*32, 32, 32);
	public static BufferedImage Cerca6 = Game.enviroment.getSprite(32, 8*32, 32, 32);
	public static BufferedImage Cerca7 = Game.enviroment.getSprite(4*32, 7*32, 32, 32);
	public static BufferedImage Cerca8 = Game.enviroment.getSprite(3*32, 7*32, 32, 32);
	
	public static BufferedImage Canto5 = Game.enviroment.getSprite(0, 7*32, 32, 32);
	public static BufferedImage Canto6 = Game.enviroment.getSprite(2*32, 7*32, 32, 32);
	public static BufferedImage Canto7 = Game.enviroment.getSprite(2*32, 8*32, 32, 32);
	public static BufferedImage Canto8 = Game.enviroment.getSprite(0, 8*32, 32, 32);
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	public int depth = 3;
	
	private int Maskx, Masky, Maskw, Maskh;
	
	public Entidades(int x, int y, int width, int heigh, BufferedImage image){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = heigh;
		this.image = image;
		
		this.Maskx = 0;
		this.Masky = 0;
		this.Maskw = width;
		this.Maskh = heigh;
		
	}
	
	public static Comparator<Entidades> nodeSorter = new Comparator<Entidades>() {
		
		@Override
		public int compare(Entidades n0,Entidades n1) {
			if(n1.depth < n0.depth)
				return +1;
			if(n1.depth > n0.depth)
				return -1;
			return 0;
		}
		
	};
	
	public void setMask(int Maskx, int Masky, int Maskw, int Maskh){
		this.Maskx = Maskx;
		this.Masky = Masky;
		this.Maskw = Maskw;
		this.Maskh = Maskh;
		
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeigh(){
		return height;
	}
	
	public void update(){
		
	}
	
	public static boolean isColidding(Entidades e1, Entidades e2){
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.Maskx, e1.getY() + e1.Masky, e1.Maskw, e1.Maskh);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.Maskx, e2.getY() + e2.Masky, e2.Maskw, e2.Maskh);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics graficos){
		
		//Otimização da renderização das entidades
		if(this.x > (Camera.x - 70) && this.y > (Camera.y - 70) && 
				this.x < (Camera.x + Game.Width) && this.y < (Camera.y + Game.Height)){
			
			graficos.drawImage(image, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		
		
		//graficos.setColor(Color.red);
		//graficos.fillRect(this.getX()+Maskx - Camera.x, this.getY()+Masky - Camera.y, Maskw, Maskh);
	}

}
