package tetrominos;

import java.awt.Color;

import tetris.Tetromino;

public class RLBlock extends Tetromino {
	public static int[][][] phases = {
			{{0,0,1},
			{1,1,1},
			{0,0,0},},
			
			{{0,1,0},
			{0,1,0},
			{0,1,1},},
			
			{{0,0,0},
			{1,1,1},
			{1,0,0},},
			
			{{1,1,0},
			{0,1,0},
			{0,1,0},},
	};
	
	public RLBlock(Color c, int x, int y, int phase) {
		super(c, x, y, phase, phases);
	}
	
	public RLBlock(int x, int y, int phase) {
		super(new Color(150, 0, 255), x, y, phase, phases);
	}

	public int[][][] getPhases() {
		return phases;
	}
}
