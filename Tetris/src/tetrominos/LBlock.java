package tetrominos;

import java.awt.Color;

import tetris.Tetromino;

public class LBlock extends Tetromino {
	public static int[][][] phases = {
			{{1,0,0},
			{1,1,1},
			{0,0,0},},
			
			{{0,1,1},
			{0,1,0},
			{0,1,0},},
			
			{{0,0,0},
			{1,1,1},
			{0,0,1},},
			
			{{0,1,0},
			{0,1,0},
			{1,1,0},},
	};
	
	public LBlock(Color c, int x, int y, int phase) {
		super(c, x, y, phase, phases);
	}
	
	public LBlock(int x, int y, int phase) {
		super(new Color(0, 255, 0), x, y, phase, phases);
	}

	public int[][][] getPhases() {
		return phases;
	}
}
