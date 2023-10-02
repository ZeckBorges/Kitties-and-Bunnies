package GameProject.Entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import GameProject.Mapas.Mapas;
import GameProject.Mapas.Camera;
import GameProject.main.Game;

public class Loui extends Entidades {
	
	
	public String[] frases = new String[3];
	public boolean showMessage = false;
	public boolean show = false;
	public boolean talk, endtalk, close;
	public int frase = 0;
	public int curIndex = 0;
	
	public boolean esquerda = false;
	public boolean direita = false;
	public boolean subindo = false;
	public boolean descendo = false;
	
	private BufferedImage[] correrdireita;
	private BufferedImage[] correresquerda;
	private BufferedImage[] subir;
	private BufferedImage[] descer;
	
	private int frames = 0, maxframes = 5, index = 0, maxindex = 0;
	private boolean moved = false;
	
	public int time = 0;
	public int maxtime = 10;
	
	public int velocidade = 1;

	public Loui(int x, int y, int width, int heigh, BufferedImage image) {
		super(x, y, width, heigh, image);
		depth = 2;
		frases[0] = "Miau! Meu nome é Loui!";
		frases[1] = "Você me encontrou, miau!";
		frases[2] = "Você sabe o caminho de casa?";
		
		correrdireita = new BufferedImage[3];
		correresquerda = new BufferedImage[3];
		subir = new BufferedImage[3];
		descer = new BufferedImage[3];
	}
	
	public void update(){
		
		if(Math.abs(Game.player.getX() - x) < 35 && Math.abs(Game.player.getY() - y) < 35){
			close = true;
			if(close){
				if(talk){
					this.depth = 4;
					showMessage = true;
				}
			}
		}else{
			close = false;
			showMessage = false;
			this.depth = 1;
		}
		
		if(showMessage){
			
			time++;
			if(time >= maxtime){
				time = 0;
				
				if(curIndex < frases[frase].length()){
					curIndex++;
				}else{
					if(frase < frases.length - 1){
						frase++;
						curIndex = 0;
					}
				}
			}
			
			
		}
		//Seguindo o player
		if(frase == 2 && Mapas.isFree((int)x, (int)y)){
			if((int)x < Game.player.getX() & Mapas.isFree((int)x+velocidade, (int)y)){
				for(int i = 0; i < 3; i++){
					correrdireita[i] = Game.npcs.getSprite(0 + (i*32), 2*32, 32, 32);
				}
				maxindex = 2;
				x+=velocidade;
				direita = true;
				moved = true;
				esquerda = false;
				subindo = false;
				descendo = false;
			}
			else if((int)x > Game.player.getX() & Mapas.isFree((int)x-velocidade, (int)y)){
				for(int i = 0; i < 3; i++){
					correresquerda[i] = Game.npcs.getSprite(0 + (i*32), 32, 32, 32);
				}
				maxindex = 2;
				x-=velocidade;
				esquerda = true;
				moved = true;
				direita = false;
				subindo = false;
				descendo = false;
			}
			if((int)y < Game.player.getY() & Mapas.isFree((int)x, (int)y+velocidade)){
				for(int i = 0; i < 3; i++){
					descer[i] = Game.npcs.getSprite(0 + (i*32), 0, 32, 32);
				}
				maxindex = 2;
				y+=velocidade;
				descendo = true;
				moved = true;
				subindo = false;
				direita = false;
				esquerda = false;
			}
			else if((int)y > Game.player.getY() & Mapas.isFree((int)x, (int)y-velocidade)){
				for(int i = 0; i < 3; i++){
					subir[i] = Game.npcs.getSprite(0 + (i*32), 3*32, 32, 32);
				}
				maxindex = 2;
				y-=velocidade;
				subindo = true;
				moved = true;
			}
			if((int)x == Game.player.getX() && (int)y == Game.player.getY()){
				moved = false;
				descendo = false;
				subindo = false;
				direita = false;
				esquerda = false;
			}
			
		}
		/*
		if(direita){
			
		}
		if(esquerda){
			
			moved = true;
		}
		if(descendo){
			
			moved = true;
		}
		if(subindo){
			
			moved = true;
		}*/
		
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
	}
	
	public void render(Graphics g){
		//super.render(g);
		if(showMessage){
			//Caixa de di�logo
			g.setColor(Color.WHITE);
			g.fillRect(Game.Width/10, Game.Height-50, Game.Width - 60, Game.Height-260);
			
			//Formata��o do texto
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.black);
			
			//�cone do personagem
			//g.drawImage(this.image, Game.Width/10, Game.Height-50, null);
			g.drawImage(this.image, Game.Width/10, Game.Height-10, 95, 240, 32, 25, 1, 0, null, null);
			/*drawImage pode ser usado com todos os par�metros.
			 *Com esses valores pode-se controlar as posi��es, dimens�es e at� espelhar a imagem em x e/ou y.
			 *img - the specified image to be drawn. This method does nothing if img is null.
			 *dx1 - the x coordinate of the first corner of the destination rectangle.
			 *dy1 - the y coordinate of the first corner of the destination rectangle.
			 *dx2 - the x coordinate of the second corner of the destination rectangle.
			 *dy2 - the y coordinate of the second corner of the destination rectangle.
			 *sx1 - the x coordinate of the first corner of the source rectangle.
			 *sy1 - the y coordinate of the first corner of the source rectangle.
			 *sx2 - the x coordinate of the second corner of the source rectangle.
			 *sy2 - the y coordinate of the second corner of the source rectangle.
			 **/
			
			//Falas
			g.drawString(frases[frase].substring(0, curIndex), (Game.Width/5)+20, (Game.Height-25));
			g.drawString("Enter p/ sair", (Game.Width/5)+90, (Game.Height-25)+9);
		}
		
		//parado
		if(moved == false){
			super.render(g);
		}
		
		//movendo
				if(descendo && moved == true){
					g.drawImage(descer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}else if(subindo && moved == true){
					g.drawImage(subir[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}else if(direita && moved == true){
					g.drawImage(correrdireita[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}else if(esquerda && moved == true){
					g.drawImage(correresquerda[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
	}
}
