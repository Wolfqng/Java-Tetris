package tetris;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tetrominos.LBlock;
import tetrominos.RLBlock;
import tetrominos.RZBlock;
import tetrominos.TBlock;
import tetrominos.ZBlock;
import tetrominos.BBlock;
import tetrominos.IBlock;

public class Enviroment extends JPanel {
	private static final long serialVersionUID = 1L;
	public static JFrame f = new JFrame("Tetris");
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int BLOCKSIZE = 24;
	public static final int WIDTHOFFSET = 180;
	public static Block map[][];
	public static Tetromino tetra;
	public static int level = 0;
	public static int score = 0;
	public static Long time = System.currentTimeMillis();
	public static boolean playing = true;
	
	public void paintComponent(Graphics g) {
		//Draw Map
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] == null) {
					g.setColor(new Color(80, 80, 80));
					g.fillRect(WIDTHOFFSET + (i * BLOCKSIZE), (j * BLOCKSIZE), BLOCKSIZE, BLOCKSIZE);
					g.setColor(Color.BLACK);
					g.drawRect(WIDTHOFFSET + (i * BLOCKSIZE), (j * BLOCKSIZE), BLOCKSIZE, BLOCKSIZE);
				}
				else {
					g.setColor(map[i][j].getColor());
					g.fillRect(WIDTHOFFSET + (i * BLOCKSIZE), (j * BLOCKSIZE), BLOCKSIZE, BLOCKSIZE);
					g.setColor(Color.BLACK);
					g.drawRect(WIDTHOFFSET + (i * BLOCKSIZE), (j * BLOCKSIZE), BLOCKSIZE, BLOCKSIZE);
				}
				
			}
		}
		
		//Draw Tetromino
		if(playing) {
			for(Block b : tetra.getBlocks()) {
				int x = WIDTHOFFSET + (b.getX() * BLOCKSIZE);
				int y = (b.getY() * BLOCKSIZE);
				Color color = b.getColor();
				g.setColor(color);
				g.fillRect(x, y, BLOCKSIZE, BLOCKSIZE);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, BLOCKSIZE, BLOCKSIZE);
			}
		}
		
		//Draw extras
		g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
		
		long drawTime = System.currentTimeMillis() - time;
		if(!playing)
			drawTime = time;
		
		g.drawString(String.valueOf(drawTime / 1000), WIDTH - g.getFontMetrics().stringWidth(String.valueOf(drawTime / 1000)) - 10, 50);
		g.drawString(String.valueOf(score), WIDTH - g.getFontMetrics().stringWidth(String.valueOf(score)) - 10, 100);
		
		if(!playing) {
			g.setColor(Color.BLACK);
			g.fillRect(WIDTHOFFSET, 150 - g.getFontMetrics().getFont().getSize(), map.length * BLOCKSIZE, g.getFontMetrics().getFont().getSize() + 17);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", WIDTHOFFSET + (map.length * BLOCKSIZE / 2) - (g.getFontMetrics().stringWidth("Game Over") / 2), 150);
		}
	}
	
	public static void main(String[] args) {
		keyListener mk = new keyListener();
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setBackground(new Color(20, 20, 20));
    	f.setSize(WIDTH + 16, HEIGHT + 39);
        f.setVisible(true);
        f.add(new Enviroment());
        f.addKeyListener(mk);
        f.setBackground(Color.DARK_GRAY);
        
        map = new Block[10][24];
        newTetromino();
        
        Object monitor = new Object();
        synchronized(monitor) {
            while(true) {
            	f.repaint();
            	
            	if(playing)
            		tetra.moveDown();
            	
                try{Thread.sleep(150);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
            }
        }        
    }
	
	public static void newTetromino() {
		//Set blocks to previous Tetromino
		if(tetra != null && tetra.getBlocks() != null)
			for(Block b : tetra.getBlocks()) {
				map[b.getX()][b.getY()] = b;
			}
		
		//Checks for a full line
		lineCheck();
		
		//New one
		int random = (int)(Math.random() * 6);
		//Color color = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
		int x = (int)(Math.random() * (map.length - 1));
		int y = 0;
		if(random == 0)
			tetra = new BBlock(x, y, (int)Math.random() * BBlock.phases.length);
		if(random == 1)
			tetra = new IBlock(x, y, (int)Math.random() * IBlock.phases.length);
		if(random == 2)
			tetra = new LBlock(x, y, (int)Math.random() * LBlock.phases.length);
		if(random == 3)
			tetra = new RLBlock(x, y, (int)Math.random() * RLBlock.phases.length);
		if(random == 4)
			tetra = new RZBlock(x, y, (int)Math.random() * RZBlock.phases.length);
		if(random == 5)
			tetra = new TBlock(x, y, (int)Math.random() * TBlock.phases.length);
		if(random == 6)
			tetra = new ZBlock(x, y, (int)Math.random() * ZBlock.phases.length);
		
		if(tetra.checkCollision(tetra.getBlocks(), map)) {
			playing = false;
			time = System.currentTimeMillis() - time;
		}
	}
	
	public static void lineCheck() {
		int lineCount = 0;
		
		for(int j = 0; j < map[0].length; j++) {
			int count = 0;
			
			for(int i = 0; i < map.length; i++) {
				if(map[i][j] != null)
					count++;
			}
			
			if(count >= map.length) {
				lineCount++;
				
				for(int i = 0; i < map.length; i++) {
					map[i][j] = null;
				}
				
				//Move everything down
				for(int i = j; i > 0; i--) {
					for(int k = 0; k < map.length; k++) {
						map[k][i] = map[k][i-1];
					}
				}
				
			}
			
		}
		
		if(lineCount < 4) 
			score += lineCount * 100;
		else 
			score += 1200;		
	}
	
	public static void restart() {
		map = new Block[10][24];
		score = 0;
		time = System.currentTimeMillis();
        playing = true;
	}
	
}

class keyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent event) {
    	if(event.getKeyCode() == KeyEvent.VK_D)
    		Enviroment.tetra.moveRight();
    	if(event.getKeyCode() == KeyEvent.VK_A)
    		Enviroment.tetra.moveLeft();
    	if(event.getKeyCode() == KeyEvent.VK_E)
    		Enviroment.tetra.rotateCW();
    	if(event.getKeyCode() == KeyEvent.VK_Q)
    		Enviroment.tetra.rotateCC();
		if(event.getKeyCode() == KeyEvent.VK_S)
    		Enviroment.tetra.moveDown();
		
		if(!Enviroment.playing && event.getKeyCode() == KeyEvent.VK_SPACE)
			Enviroment.restart();
    }
    
}


    
