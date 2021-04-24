package tetrominos;

import java.awt.Color;

import tetris.Tetromino;

public class BBlock extends Tetromino {
	public static int[][][] phases = {
			{{0,1,1,0},
			{0,1,1,0},
			{0,0,0,0},
			{0,0,0,0}},
			
			{{0,1,1,0},
			{0,1,1,0},
			{0,0,0,0},
			{0,0,0,0}},
			
			{{0,1,1,0},
			{0,1,1,0},
			{0,0,0,0},
			{0,0,0,0}},
			
			{{0,1,1,0},
			{0,1,1,0},
			{0,0,0,0},
			{0,0,0,0}},
	};//Just doing four to make it uniform, not needed though
	
	public BBlock(Color c, int x, int y, int phase) {
		super(c, x, y, phase, phases);
	}
	
	public BBlock(int x, int y, int phase) {
		super(new Color(255, 0, 0), x, y, phase, phases);
	}

	public int[][][] getPhases() {
		return phases;
	}
}
