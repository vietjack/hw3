package com.javangon.matchinggame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameGrid {
	private static final int ROWS = 4;
	private static final int COLS = 5;
	
	private int[][] mTypeGrid;
	private boolean[][] mRevealedGrid;
	private boolean[][] mMatchFound;
	private int numTypes;
	
	public GameGrid(int numTypes) {
		mTypeGrid = new int[4][5];
		mRevealedGrid = new boolean[4][5];
		mMatchFound = new boolean[4][5];
		this.numTypes = numTypes;
		
		reset();
	}
	
	public void reset() {
		int r;
		int c;
		
		for(int i=0; i<ROWS*COLS; i++) {
			r = i / COLS;
			c = i % COLS;
			
			mTypeGrid[r][c] = -1;
			mRevealedGrid[r][c] = false;
			mMatchFound[r][c] = false;
		}
		placeColorsRandomly();
	}
	
	private void placeColorsRandomly() {
		List<Integer> typesList = new LinkedList<Integer>();
		for(int j = 0; j < 10;  j++) {
			Integer type = Integer.valueOf(j % numTypes);
			typesList.add(type);
			typesList.add(type);
		}
		
		List<Integer> cellIndexes = new ArrayList<Integer>();
		for(int i=0; i < ROWS*COLS; i++)
			cellIndexes.add(i);
		
		Collections.shuffle(cellIndexes);
		
		for(Integer i: cellIndexes) {
			int r = i.intValue() / COLS;
			int c = i.intValue() % COLS;
			mTypeGrid[r][c] = typesList.remove(0);
		}
	}
	
	public void reveal(RowColumnPair rcp) {
		mRevealedGrid[rcp.getRow()][rcp.getColumn()] = true;
	}

	public void hide(RowColumnPair rcp) {
		mRevealedGrid[rcp.getRow()][rcp.getColumn()] = false;
	}
	
	public void setMatched(RowColumnPair p1, RowColumnPair p2) {
		mMatchFound[p1.getRow()][p1.getColumn()] = true;
		mMatchFound[p2.getRow()][p2.getColumn()] = true;
	}
	
	public boolean isMatched(RowColumnPair rcp) {
		return mMatchFound[rcp.getRow()][rcp.getColumn()];
	}
	
	public boolean isRevealed(RowColumnPair rcp) {
		return mRevealedGrid[rcp.getRow()][rcp.getColumn()];
	}
	
	public int getType(RowColumnPair rcp) {
		return mTypeGrid[rcp.getRow()][rcp.getColumn()];
	}

}
