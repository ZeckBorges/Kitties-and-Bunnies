package GameProject.Entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameProject.main.Game;

public class PlacaParaLabirinto extends Entidades{
	
	public boolean showMessage = false;
	public boolean show = false;
	public boolean talk, endtalk, close;
	public boolean labirinto;
	public boolean vila;

	public PlacaParaLabirinto(int x, int y, int width, int heigh, BufferedImage image) {
		super(x, y, width, heigh, image);
		depth = 1;
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
		
		if(Math.abs(Game.player.getX()) > x){
			//Game.gameState = "Labirinto";
			//System.out.println("Labirinto");
		}
	}
	
	public void render(Graphics g){
		super.render(g);
		if(showMessage == true){
			//Caixa de diálogo
			g.setColor(Color.WHITE);
			g.fillRect(Game.Width/10, Game.Height-50, Game.Width - 60, Game.Height-260);
			
			//Formatação do texto
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.black);
			
			//Ícone do personagem
			//g.drawImage(this.image, Game.Width/10, Game.Height-50, null);
			g.drawImage(this.image, Game.Width/10+5, Game.Height-48, 100, 305, 0, 0, 32, 32, null, null);
			/*drawImage pode ser usado com todos os parâmetros.
			 *Com esses valores pode-se controlar as posições, dimensões e até espelhar a imagem em x e/ou y.
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
			g.drawString("Labirinto -->", (Game.Width/5)+20, (Game.Height-25)-9);
			g.drawString("<-- Vila dos Gatinhos", (Game.Width/5)+20, (Game.Height-25));
			g.drawString("Enter p/ sair", (Game.Width/5)+90, (Game.Height-25)+9);
		}
		
	}

}
