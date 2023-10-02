package GameProject.Mapas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import GameProject.Entidades.Cenario;
import GameProject.Entidades.Entidades;
import GameProject.Entidades.PlacaParaLabirinto;
import GameProject.Entidades.Player;
import GameProject.main.Game;

public class Mapas {
	
	
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int Tile_Size = 10;
	
	public static Player player;
    public static PlacaParaLabirinto placaparalabirinto;
    Game game;
	
	public static Entidades ent;
	
	public Mapas(String path){
		try {
			BufferedImage mapa = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[mapa.getWidth() * mapa.getHeight()];
			WIDTH = mapa.getWidth();
			HEIGHT = mapa.getHeight();
			tiles = new Tile[mapa.getWidth() * mapa.getHeight()];
			mapa.getRGB(0, 0, mapa.getWidth(), mapa.getHeight(), pixels, 0, mapa.getWidth());
			for (int xx = 0; xx < mapa.getWidth(); xx ++){
				for(int yy = 0; yy < mapa.getHeight(); yy ++){
					int pixelatual = pixels[xx + (yy * mapa.getWidth())];
					tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, null);
					
					//Pixel preto de colisão
					
					if(pixelatual == 0xFF000000){
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, null);
					}
					
					//Carregamento do Player e NPCs
					else if(pixelatual == 0xFF0008ff){
						//PLAYER
						Game.player.setX(xx*Tile_Size);
						Game.player.setY(yy*Tile_Size);	
						
					}else if(pixelatual == 0xFFdcc6a4){
						Game.Loui.setX(xx*Tile_Size);
						Game.Loui.setY(yy*Tile_Size);
					}else if(pixelatual == 0xFFc500c9){
						Game.placaparalabirinto.setX(xx*Tile_Size);
						Game.placaparalabirinto.setY(yy*Tile_Size);
						
					}
					
					//Carregando Tiles
					else if(pixelatual == 0xFF1c8e00){
						//ARVORE VERDE
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Entidades.Greentree);
					}else if(pixelatual == 0xFFffd600){
						//CERCA
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, Entidades.Cerca1);
					}else if(pixelatual == 0xFFff9d00){
						//CERCA2
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, Entidades.Cerca2);
					}else if(pixelatual == 0xFF8b7400){
						//CERCA3
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, Entidades.Cerca3);
					}else if(pixelatual == 0xFF8b5500){
						//CERCA4
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, Entidades.Cerca4);
					}else if(pixelatual == 0xFF1c8e00){
						//ARVORE VERDE
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Entidades.Greentree);
					}else if(pixelatual == 0xFF6e8c3d){
						//GRAMA
						//Game.entidades.add(new BackGround(xx*Tile_Size,yy*Tile_Size, 32, 32, Entidades.Grass));	
					}else if(pixelatual == 0xFFffd600){
						//CERCA
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, Entidades.Cerca1);
					}else if(pixelatual == 0xFFff9d00){
						//CERCA2
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, Entidades.Cerca2);
					}else if(pixelatual == 0xFF8b7400){
						//CERCA3
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, Entidades.Cerca3);
					}else if(pixelatual == 0xFF8b5500){
						//CERCA4
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, Entidades.Cerca4);
					}else if(pixelatual == 0xFF582700){
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Tile.TILE_ROAD);
					}else if(pixelatual == 0xFF008521){
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Tile.TILE_GRASS);
					}else if(pixelatual == 0xFFfb00ff){
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Tile.TILE_FLORES);
					}else if(pixelatual == 0xFF86713a){
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Tile.TILE_Ground);
					}else if(pixelatual == 0xFF818181){
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Tile.TILE_ROAD2);
					}else if(pixelatual == 0xFF813131){
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Entidades.Browntree);
					}else if(pixelatual == 0xFFff0000){
						//CASAS
						Game.entidades.add(new Cenario(xx*Tile_Size, yy*Tile_Size, 32, 32, Entidades.Casa));
					}else if(pixelatual == 0xFF940505){
						//CASAS
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, null);
						Game.entidades.add(new BackGround(xx*Tile_Size, yy*Tile_Size, 32, 32, Entidades.Casa1));
					}else if(pixelatual == 0xFF00fff0){
						//ARVORE AZUL
						Game.entidades.add(new Cenario(xx*Tile_Size,yy*Tile_Size, 32, 32, Entidades.Bluetree));
					}else if(pixelatual == 0xFFff2245){
						//ARVORE VERMELHA
						Game.entidades.add(new Cenario(xx*Tile_Size,yy*Tile_Size, 32, 32, Entidades.Redtree));
						//tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Entidades.Redtree);
					}else if(pixelatual == 0xFF32ff00){
						//ARVORE VERDE
						Game.entidades.add(new Cenario(xx*Tile_Size,yy*Tile_Size, 32, 32, Entidades.Greentree));
						//tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Entidades.Greentree);
					}else if(pixelatual == 0xFF853232){
						//ARVORE MARROM
						Game.entidades.add(new Cenario(xx*Tile_Size,yy*Tile_Size, 32, 32, Entidades.Browntree));
					}else if(pixelatual == 0xFF764822){
						//ARVORE MARROM
						Game.entidades.add(new BackGround(xx*Tile_Size,yy*Tile_Size, 32, 32, Entidades.Browntree1));
					}else if(pixelatual == 0xFFa14253){
						//ARVORE ROXA
						Game.entidades.add(new Cenario(xx*Tile_Size,yy*Tile_Size, 32, 32, Entidades.Purpletree));
					}else if(pixelatual == 0xFF008df0){
						tiles[xx + (yy * WIDTH)] = new Colision(xx*Tile_Size, yy*Tile_Size, null);
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*Tile_Size, yy*Tile_Size, Entidades.Lago);
					}
					/*
					if(pixelatual < Camera.x && pixelatual < Camera.y || pixelatual > Game.Width && pixelatual > Game.Height){
						Game.entidades.remove(Entidades.Greentree);
					}*/
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static boolean isFree(int xnext, int ynext){
		int x1 = xnext / Tile_Size;
		int y1 = ynext / Tile_Size;
		
		int x2 = (xnext+Tile_Size-1)/ Tile_Size;
		int y2 = ynext / Tile_Size;
		
		int x3 = xnext / Tile_Size;
		int y3 = (ynext+Tile_Size-1) / Tile_Size;
		
		int x4 = (xnext+Tile_Size-1) / Tile_Size;
		int y4 = (ynext+Tile_Size-1) / Tile_Size;
		
		return !((tiles[x1 + (y1 * Mapas.WIDTH)] instanceof Colision) ||
				(tiles[x2 + (y2 * Mapas.WIDTH)] instanceof Colision) ||
				(tiles[x3 + (y3 * Mapas.WIDTH)] instanceof Colision) ||
				(tiles[x4 + (y4 * Mapas.WIDTH)] instanceof Colision));
	}
	
	public void render(Graphics graficos){
		int xstart = Camera.x/Tile_Size - 10;
		int ystart = Camera.y/Tile_Size - 10;
		int xfinal = xstart + (Game.Width/Tile_Size) + 10;
		int yfinal = ystart + (Game.Height/Tile_Size) + 10;
		
		for(int xx = xstart; xx <= xfinal; xx++){
			for(int yy = ystart; yy <= yfinal; yy++){
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT){
					continue;
				}
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(graficos);
			}
		}
	}

}
