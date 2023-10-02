package GameProject.Entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import GameProject.Mapas.Camera;
import GameProject.Mapas.Mapas;
import GameProject.main.Game;

public class Player extends Entidades {
	
	public boolean right, left, up, down, attack, defesa;
	private int right_dir = 0, left_dir = 1, up_dir = 3, down_dir = 4;
	private int dir = right_dir; 
	public int velocidade = 2;
	public boolean lastright = false;
	public boolean lastleft = false;
	
	private int frames = 0, maxframes = 5, index = 0, index2 = 0, maxindex = 0;
	private boolean moved = false;
	private BufferedImage[] correrdireita;
	private BufferedImage[] correresquerda;
	private BufferedImage[] subir;
	private BufferedImage[] descer;
	private BufferedImage[] ataquedireita; //= Game.sprites.getSprite(5*32, 32, 32, 32);
	private BufferedImage[] ataqueesquerda; //= Game.sprites.getSprite(5*32, 0, 32, 32);
	private BufferedImage[] ataquecostas; //= Game.sprites.getSprite(4*32, 64, 32, 40);
	private BufferedImage[] ataquefrente; //= Game.sprites.getSprite(4*32, 0, 32, 40);
	private BufferedImage paradofrente = Game.sprites.getSprite(32, 0, 32, 32);
	private BufferedImage paradoesquerda = Game.sprites.getSprite(32, 32, 32, 32);
	private BufferedImage paradodireita = Game.sprites.getSprite(32, 3*32, 32, 32);
	private BufferedImage paradocostas = Game.sprites.getSprite(32, 2*32, 32, 32);
	private BufferedImage defesafrente = Game.sprites.getSprite(96, 0, 32, 32);
	private BufferedImage defesaesquerda = Game.sprites.getSprite(96, 32, 32, 32);
	private BufferedImage defesadireita = Game.sprites.getSprite(96, 3*32, 32, 32);
	private BufferedImage defesacostas = Game.sprites.getSprite(96, 2*32, 32, 32);
	
	
	public Player(int x, int y, int width, int high, BufferedImage image) {
		super(x, y, width, high, image);
		
		correrdireita = new BufferedImage[3];
		correresquerda = new BufferedImage[3];
		subir = new BufferedImage[3];
		descer = new BufferedImage[3];
		ataquedireita = new BufferedImage[2];
		ataqueesquerda = new BufferedImage[2];
		ataquecostas = new BufferedImage[2];
		ataquefrente = new BufferedImage[2];
		depth = 2;
		
	}
	
	public void update(){
		
		moved = false;
		//attack = false;
		if(attack == false){
			if(right && Mapas.isFree(this.getX()+8, this.getY()+8)){
				for(int i = 0; i < 3; i++){
					correrdireita[i] = Game.sprites.getSprite(0 + (i*32), 3*32, 32, 32);
				}
				moved = true;
				maxindex = 2;
				dir = right_dir;
				x += velocidade;
				
			}
			if(left && Mapas.isFree(this.getX()-4, this.getY()+10)){
				for(int i = 0; i < 3; i++){
					correresquerda[i] = Game.sprites.getSprite(0 + (i*32), 32, 32, 32);
				}
				moved = true;
				maxindex = 2;
				dir = left_dir;
				x -= velocidade;
				
			}
			if(up && Mapas.isFree(this.getX()+2, this.getY()-2)){
				for(int i = 0; i < 3; i++){
					subir[i] = Game.sprites.getSprite(0 + (i*32), 2*32, 32, 32);
				}
				moved = true;
				maxindex = 2;
				dir = up_dir;
				y -= velocidade;
			}
			if (down && Mapas.isFree(this.getX(), (this.getY()+16))){
				for(int i = 0; i < 3; i++){
					descer[i] = Game.sprites.getSprite(0 + (i*32), 0, 32, 32);
				}
				moved = true;
				maxindex = 2;
				dir = down_dir;
				y += velocidade;
			}
		}
		
		
		if(attack){
			if(dir == right_dir){
				moved = false;
				for(int i = 0; i<2; i++){
					ataquedireita[i] = Game.sprites.getSprite(0+(i*32), 5*32, 32, 32);
				}
				maxindex = 1;
				
			}
			if(dir == left_dir){
				moved = false;
				for(int i = 0; i<2; i++){
					ataqueesquerda[i] = Game.sprites.getSprite(0+(i*32), 4*32, 32, 32);
				}
				maxindex = 1;
				
			}
			if(dir == up_dir){
				moved = false;
				for(int i = 0; i<2; i++){
					ataquecostas[i] = Game.sprites.getSprite(128+(i*32), 64, 32, 40);
				}
				maxindex = 1;
				
			}
			if(dir == down_dir){
				moved = false;
				for(int i = 0; i<2; i++){
					ataquefrente[i] = Game.sprites.getSprite(128+(i*32), 0, 32, 40);
				}
				maxindex = 1;
				
			}
			//maxindex = 1;//tentar mover o maxindex para dentro dos ifs
		}
		
		if(defesa){
			moved = false;
		}
		
		if (moved){
			frames ++;
			if(frames == maxframes){
				frames = 0;
				index ++;
				if(index > maxindex){
					index = 0;
				}
			}
		}
		
		if (attack){
			frames ++;
			if(frames == maxframes){
				frames = 0;
				index2 ++;
				if(index2 > maxindex){
					index2 = 0;
				}
			}
		}
		
		Camera.x = Camera.Clamp(this.getX() - (Game.Width/2), 0, Mapas.WIDTH*10 - Game.Width);
		Camera.y = Camera.Clamp(this.getY() - (Game.Height/2), 0, Mapas.HEIGHT*10 - Game.Height);
	}
	
	
	public void render(Graphics graficos){
		//parado
		if(attack == false){
			if(moved == false && dir == up_dir){
				graficos.drawImage(paradocostas, this.getX()  - Camera.x, this.getY() - Camera.y, null);
			}else if (moved == false && dir == left_dir){
				graficos.drawImage(paradoesquerda, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if (moved == false && dir == right_dir){
				graficos.drawImage(paradodireita, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if (moved == false && dir == down_dir){
				graficos.drawImage(paradofrente, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		
		//movendo
		if(moved == true){
			if(dir == right_dir){
				graficos.drawImage(correrdireita[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(dir == left_dir){
				graficos.drawImage(correresquerda[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(dir == up_dir){
				graficos.drawImage(subir[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(dir == down_dir){
				graficos.drawImage(descer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		
		//atacando
		if(attack == true){
			if(dir == right_dir){
				graficos.drawImage(ataquedireita[index2], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if(dir == left_dir){
				graficos.drawImage(ataqueesquerda[index2], (this.getX() - 15) - Camera.x, this.getY() - Camera.y, null);
			}
			if(dir == up_dir){
				graficos.drawImage(ataquecostas[index2], this.getX() - Camera.x, (this.getY() - 15) - Camera.y, null);
			}
			if(dir == down_dir){
				graficos.drawImage(ataquefrente[index2], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		
		//defendendo
		if(defesa){
			if(dir == up_dir){
				graficos.drawImage(defesacostas, this.getX()  - Camera.x, this.getY() - Camera.y, null);
			}else if (dir == left_dir){
				graficos.drawImage(defesaesquerda, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if (dir == right_dir){
				graficos.drawImage(defesadireita, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if (dir == down_dir){
				graficos.drawImage(defesafrente, this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		
		
		
	}
}
