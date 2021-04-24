package tetrominos;

import java.awt.Color;

import tetris.Tetromino;

public class RZBlock extends Tetromino {
	public static int[][][] phases = {
			{{1,1,0},
			{0,1,1},
			{0,0,0},},
			
			{{0,0,1},
			{0,1,1},
			{0,1,0},},
			
			{{0,0,0},
			{1,1,0},
			{0,1,1},},
			
			{{0,1,0},
			{1,1,0},
			{1,0,0},},
	};
	
	public RZBlock(Color c, int x, int y, int phase) {
		super(c, x, y, phase, phases);
	}
	
	public RZBlock(int x, int y, int phase) {
		super(new Color(0, 255, 255), x, y, phase, phases);
	}

	public int[][][] getPhases() {
		return phases;
	}
}
