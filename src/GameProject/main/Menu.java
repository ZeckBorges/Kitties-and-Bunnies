package GameProject.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public String[] options = {"New Game", "Load", "Exit"};
	public int currentoption = 0;
	public int maxoption = options.length - 1;
	public boolean menu;
	public boolean up, down, enter;
	public boolean newgame;
	
	public void update(){
		
		if(up){
			up = false;
			currentoption--;
			if(currentoption < 0){
				currentoption = maxoption;
			}
		}
		if(down){
			down = false;
			currentoption++;
			if(currentoption > maxoption){
				currentoption = 0;
			}
		}
		if(enter){
			enter = false;
			/*if(options[currentoption] == "New Game"){
				//Game.gameState = "Vila";
			}*/
			if(options[currentoption] == "Exit"){
				System.exit(0);
			}
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.Width*Game.Scale, Game.Height*Game.Scale);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 36));
		g.drawString("Kitties and Bunnies", (Game.Width*Game.Scale)/2 - 150, (Game.Height*Game.Scale)/2 - 200);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 24));
		g.drawString("New Game", (Game.Width*Game.Scale)/2 - 60, (Game.Height*Game.Scale)/2 - 100);
		g.drawString("Load", (Game.Width*Game.Scale)/2 - 27, (Game.Height*Game.Scale)/2 - 50);
		g.drawString("Exit", (Game.Width*Game.Scale)/2 - 20, (Game.Height*Game.Scale)/2);
		
		if(options[currentoption] == "New Game"){
			g.drawString(">", (Game.Width*Game.Scale)/2 - 75, (Game.Height*Game.Scale)/2 - 100);
			g.drawString("<", (Game.Width*Game.Scale)/2 + 62, (Game.Height*Game.Scale)/2 - 100);
		}else if(options[currentoption] == "Load"){
			g.drawString(">", (Game.Width*Game.Scale)/2 - 42, (Game.Height*Game.Scale)/2 - 50);
			g.drawString("<", (Game.Width*Game.Scale)/2 + 32, (Game.Height*Game.Scale)/2 - 50);
		}else if(options[currentoption] == "Exit"){
			g.drawString(">", (Game.Width*Game.Scale)/2 - 35, (Game.Height*Game.Scale)/2);
			g.drawString("<", (Game.Width*Game.Scale)/2 + 25, (Game.Height*Game.Scale)/2);
		}
		
	}

}
