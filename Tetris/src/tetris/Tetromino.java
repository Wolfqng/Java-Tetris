package tetris;

import java.awt.Color;
import java.util.ArrayList;

public class Tetromino {
	private ArrayList<Block> blocks = new ArrayList<>();
	private Color c;
	private int x, y;
	private int phase;
	private int[][][] phases;
	
	public Tetromino(Color c, int x, int y, int phase, int[][][] phases) {
		super();
		this.c = c;
		this.x = x;
		this.y = y;
		this.phase = phase;
		this.phases = phases;
		this.blocks = getBlocksFromPhase();
	}

	public ArrayList<Block> getBlocksFromPhase() {
		ArrayList<Block> bs = new ArrayList<>();
		if(phase >= phases.length)
			phase = 0;
		if(phase < 0)
			phase = phases.length - 1;
		
		for(int i = 0; i < phases[phase].length; i++) {
			for(int j = 0; j < phases[phase][i].length; j++) {
				
				if(phases[phase][i][j] == 1)
					bs.add(new Block(c, x + i, y + (phases[phase][i].length - j))); //This line is most likely going to be problematic
			}
		}
		
		return bs;
	}
	
	public void rotateCW() {
		phase--;
		if(phase < 0)
			phase = phases.length - 1;
		
		this.blocks = getBlocksFromPhase();
		
		if(checkCollision(blocks, Enviroment.map))
			rotateCC();
	}
	
	public void rotateCC() {
		phase++;
		if(phase >= phases.length)
			phase = 0; 
		
		this.blocks = getBlocksFromPhase();
		
		if(checkCollision(blocks, Enviroment.map))
			rotateCW();
	}
	
	public boolean checkCollision(ArrayList<Block> blocks, Block[][] map) {
		for(Block b : blocks) {
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[0].length; j++) {
					//Block to block
					if(map[i][j] != null && b.getX() == i && b.getY() == j)
						return true;
					//Block to map
					//System.out.println(b.getY() + " " + map[0].length);
					if(b.getX() > map.length - 1 || b.getX() < 0 || b.getY() > map[0].length - 1)
						return true;
				}
			}
		}
		
		return false;
	}

	public boolean moveLeft() {
		for(Block b : blocks) {
			b.left();
		}
		
		if(checkCollision(blocks, Enviroment.map)) {
			for(Block b : blocks) {
				b.right();
			}
			return false;	
		}
		
		this.x--;
		return true;
	}
	
	public boolean moveRight() {
		for(Block b : blocks) {
			b.right();
		}
		
		if(checkCollision(blocks, Enviroment.map)) {
			for(Block b : blocks) {
				b.left();
			}
			return false;	
		}
		
		this.x++;
		return true;
	}
	
	public boolean moveDown() {
		for(Block b : blocks) {
			b.down();
		}
		
		if(checkCollision(blocks, Enviroment.map)) {
			for(Block b : blocks) {
				b.up();
			}
			Enviroment.newTetromino();
			return false;	
		}
		
		this.y++;
		return true;
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getPhase() {
		return phase;
	}
	
	public void setPhase(int phase) {
		this.phase = phase;
	}
	
	@Override
	public String toString() {
		return "Tetromino [blocks=" + blocks + ", c=" + c + ", x=" + x + ", y=" + y + ", phase=" + phase + "]";
	}
}
