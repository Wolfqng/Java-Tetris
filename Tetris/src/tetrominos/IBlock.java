package tetrominos;

import java.awt.Color;

import tetris.Tetromino;

public class IBlock extends Tetromino {
	public static int[][][] phases = {
			{{0,0,0,0},
			{1,1,1,1},
			{0,0,0,0},
			{0,0,0,0}},
			
			{{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0},
			{0,0,1,0}},
			
			{{0,0,0,0},
			{0,0,0,0},
			{1,1,1,1},
			{0,0,0,0}},
			
			{{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0}}
	};
	
	public IBlock(Color c, int x, int y, int phase) {
		super(c, x, y, phase, phases);
	}
	
	public IBlock(int x, int y, int phase) {
		super(new Color(0, 0, 255), x, y, phase, phases);
		if(this.getClass().getSuperclass() == Tetromino.class)
			System.out.println("Worked");

	}

	public int[][][] getPhases() {
		return phases;
	}
	
}
