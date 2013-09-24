package com.javangon.matchinggame;

import java.util.LinkedList;
import java.util.List;

//Create 3 timers, 1 for each possible revealed cell.  Start each timer when the cell is revealed
//if a second cell is selected then the first cells timer can be set to the second timers
//if a third cell is selected then the first two timers need to be reset and the cells hidden
public class GamePlayManager {
	private final GameGrid grid;
	private List<RowColumnPair> guessList;
	private MatchingGameView host;
	
	public GamePlayManager(MatchingGameView host) {
		grid = new GameGrid();
		guessList = new LinkedList<RowColumnPair>();
		this.host = host;
	}
	//Start method to begin timer
	
	//Select method for when a grid space is selected
	public void select(RowColumnPair rcp) throws Exception {
		//Check that the selected cell is not already revealed or matched
		if(grid.isMatched(rcp) || grid.isRevealed(rcp)) {
			return;
		}

		guessList.add(rcp);
		int revealed = guessList.size();
		//Set cell as revealed
		grid.reveal(rcp);
		host.show(rcp, grid.getColor(rcp));
		
		//If it's the first guess, reveal for 3 seconds
		if(revealed == 1) {
			//Start that cells timer
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
			}
			else {
				//set timers for first two guesses for 3 seconds
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
			
			//Kill the timers for the first 2 guesses
			//Start timer for first two
		}
		else {
			throw new Exception("more than 3 cells revealed");
		}
		//If it's the second guess, check for match
		//If it the third then hide the first two
	}
	
	public interface MatchingGameView {
		void show(RowColumnPair rcp, Color color);
		void hide(RowColumnPair rcp);
	}
}