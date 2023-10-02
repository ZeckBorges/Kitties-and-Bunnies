package GameProject.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;

import GameProject.Entidades.Entidades;
import GameProject.Entidades.Loui;
import GameProject.Entidades.PlacaParaLabirinto;
import GameProject.Entidades.Player;
import GameProject.Graficos.Assets;
import GameProject.Mapas.Camera;
import GameProject.Mapas.Mapas;

public class Game extends Canvas implements Runnable, KeyListener {

 
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
    public static final int Width = 350;
    public static final int Height = 300;
    public static final int Scale = 2;
    private boolean isRunning;
    private Thread thread;
    
    private BufferedImage image;
    
    public static String gameState;
    
    public static List<Entidades> entidades;
    public static Assets sprites;
    public static Assets enviroment;
    public static Assets npcs;
    public static Player player;
    public static Loui Loui;
    public static PlacaParaLabirinto placaparalabirinto;
    public static Mapas mapa1;
    public static Mapas mapa2;
    public static Mapas labirinto;
    public Menu menu;
    public GameController controller = new GameController();

    public Game() {
    	addKeyListener(this);
        setPreferredSize(new Dimension(Width * Scale, Height * Scale));
        initFrame();
        image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
        entidades = new ArrayList<Entidades>();
        sprites = new Assets("/Link.png");
        enviroment = new Assets("/enviroment.png");
        npcs = new Assets("/NPCs.png");
        player = new Player(20, 20, 32, 32, sprites.getSprite(32, 0, 16, 16));
        entidades.add(player);
        Loui = new Loui(100,100,16,16, npcs.getSprite(32, 0, 32, 32));
        entidades.add(Loui);
        placaparalabirinto = new PlacaParaLabirinto(300,150,16,16, enviroment.getSprite(8*32, 5*32, 32, 32));
        entidades.add(placaparalabirinto);
        mapa1 = new Mapas("/viladosgatinhos.png");
        mapa2 = new Mapas("/world.png");
        menu = new Menu();
        
        Sound.load("viladosgatinhos", "/Concerning Hobbits.wav");
        Sound.load("menutheme", "/Marmots.wav");

    }
    
    public void initFrame(){
         //frame.setIconImage();
        frame = new JFrame("Kittens and Bunnies"); //t�tulo
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);     
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
        gameState = "Menu";
        Sound.loop("menutheme");
        
    }
    
     public synchronized void stop(){
         isRunning = false;
         try {
			thread.join();
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
     }
     

    public void update() {
  
    	if(gameState == "Menu"){
    		menu.update();
    	}
    	
    	for (int i = 0; i < entidades.size(); i++){
    		Entidades e = entidades.get(i);
    		e.update();
    	}
    }

    public void render() {
         BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics graficos = image.getGraphics();
        graficos.setColor(new Color(110,150,51)); //cor verde do fundo new Color(120,160,61) - esse foi a primeira cor aceit�vel
        graficos.fillRect(0, 0, Width*Scale, Height*Scale);
        //renderização do jogo
        
        //mapa1.render(graficos);
        mapa2.render(graficos);
        Collections.sort(entidades, Entidades.nodeSorter);
        for (int i = 0; i < entidades.size(); i++){
        	Entidades e = entidades.get(i);
        	e.render(graficos);
        }
        graficos.dispose();
        graficos = bs.getDrawGraphics();
        graficos.drawImage(image,0,0,Width*Scale, Height*Scale,null);

        if(gameState == "Menu"){
        	menu.render(graficos);
        }
        
        bs.show();
    }

    @Override
    public void run() {
        long lastime = System.nanoTime();
        double update = 60.0;
        double nanosegundo = 1000000000 / update;
        double delta = 0;
        int frames = 0;
        double time = System.currentTimeMillis();
        requestFocus();
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastime) / nanosegundo;
            lastime = now;
            if (delta >= 1) {
                update();
                render();
                frames++;
                delta--;
            }
            if (System.currentTimeMillis() - time >= 1000){
                System.out.println("FPS: " + frames);
                frames = 0;
                time += 1000;
            }
        }
        stop();
    }

	@Override
	public void keyPressed(KeyEvent e) {
		
		//Movendo
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.right = true;
			if(gameState == "Menu"){
				player.right = false;
			}
		} else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = true;
			if(gameState == "Menu"){
				player.left = false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
			player.up = true;
			
			if(gameState == "Menu"){
				menu.up = true;
				player.up = false;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
			player.down = true;
			
			if(gameState == "Menu"){
				menu.down = true;
				player.down = false;
			}
		}
		
		//Ataque
		if(e.getKeyCode() == KeyEvent.VK_L){
			player.attack = true;
		}
		
		//Defesa
		if(e.getKeyCode() == KeyEvent.VK_K){
			player.defesa = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(gameState == "Menu"){
				menu.enter = true;
				gameState = "Vila";
				Sound.stop("menutheme");
				Sound.loop("viladosgatinhos");
				
			}
			
			if(Loui.showMessage == true){
				Loui.showMessage = false;
			}
			if(placaparalabirinto.showMessage == true){
				placaparalabirinto.showMessage = false;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F){
			if(Loui.close == true){
				Loui.showMessage = true;
			}
			if(placaparalabirinto.close == true){
				placaparalabirinto.showMessage = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.right = false;
		} else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = false;	
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
			player.down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_L){
			player.attack = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_K){
			player.defesa = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

