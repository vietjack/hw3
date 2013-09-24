package com.javangon.matchinggame;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//Create 3 timers, 1 for each possible revealed cell.  Start each timer when the cell is revealed
//if a second cell is selected then the first cells timer can be set to the second timers
//if a third cell is selected then the first two timers need to be reset and the cells hidden
public class GamePlayManager {
	private final GameGrid grid;
	private List<RowColumnPair> guessList;
	private MatchingGameView host;
	
	private Timer mTimer = null;
	
	private int mMatchesFound = 0;
	
	public GamePlayManager(MatchingGameView host) {
		grid = new GameGrid();
		guessList = new LinkedList<RowColumnPair>();
		this.host = host;
	}
	//Start method to begin timer
	
	//Select method for when a grid space is selected
	public void select(final RowColumnPair rcp) throws Exception {
		//Check that the selected cell is not already revealed or matched
		if(grid.isMatched(rcp) || grid.isRevealed(rcp)) {
			return;
		}

		//Cancel all timers
		if(mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
		guessList.add(rcp);
		int revealed = guessList.size();
		//Set cell as revealed
		grid.reveal(rcp);
		host.show(rcp, grid.getColor(rcp));
		
		//If it's the first guess, reveal for 3 seconds
		if(revealed == 1) {
			//Start that cells timer
			mTimer = new Timer();
			mTimer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					host.hide(rcp);
					grid.hide(rcp);
					guessList.remove(rcp);
				}
			}, 3000);
		}
		else if(revealed == 2) {
			RowColumnPair firstGuess = guessList.get(0);
			RowColumnPair secondGuess = rcp;
			
			//Check to see if it's a match
			Color c1 = grid.getColor(firstGuess);  //Get the first guess
			Color c2 = grid.getColor(secondGuess); //Get the second guess color
			
			if (c1.equals(c2)) {
				//Kill timers for c1 and c2
				//Set as matched in the grid
				grid.setMatched(firstGuess, secondGuess);
				guessList.remove(0);
				guessList.remove(0);
				mMatchesFound++;
				if(mMatchesFound == 10) {
					host.declareWinner();
				}
			}
			else {
				mTimer = new Timer();
				mTimer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						host.hide(rcp);
						grid.hide(rcp);
						guessList.remove(rcp);
						
						RowColumnPair rcp1 = guessList.remove(0); 
						host.hide(rcp1);
						grid.hide(rcp1);
					}
				}, 3000);
			}
			
		}
		else if(revealed == 3) {
			RowColumnPair p1 = guessList.remove(0);
			RowColumnPair p2 = guessList.remove(0);
			
			//Hide the first 2 cells
			grid.hide(p1);
			grid.hide(p2);
			
			host.hide(p1);
			host.hide(p2);
			
			//Start timer for the most recently selected 
			mTimer = new Timer();
			mTimer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					host.hide(rcp);
					grid.hide(rcp);
					guessList.remove(rcp);
				}
			}, 3000);
		}
		else {
			throw new Exception("more than 3 cells revealed");
		}
	}
	
	public void reset() {
		grid.reset();
		guessList = new LinkedList<RowColumnPair>();
		
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
		mMatchesFound = 0;
	}
	
	public interface MatchingGameView {
		void show(RowColumnPair rcp, Color color);
		void hide(RowColumnPair rcp);
		void declareWinner();
		
	}
}