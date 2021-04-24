package tetrominos;

import java.awt.Color;

import tetris.Tetromino;

public class ZBlock extends Tetromino {
	public static int[][][] phases = {
			{{0,1,1},
			{1,1,0},
			{0,0,0},},
			
			{{0,1,0},
			{0,1,1},
			{0,0,1},},
			
			{{0,0,0},
			{0,1,1},
			{1,1,0},},
			
			{{1,0,0},
			{1,1,0},
			{0,1,0},},
	};
	
	public ZBlock(Color c, int x, int y, int phase) {
		super(c, x, y, phase, phases);
	}
	
	public ZBlock(int x, int y, int phase) {
		super(new Color(255, 255, 0), x, y, phase, phases);
	}

	public int[][][] getPhases() {
		return phases;
	}
}
