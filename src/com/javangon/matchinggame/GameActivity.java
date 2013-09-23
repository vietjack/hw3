package com.javangon.matchinggame;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends Activity implements OnClickListener{

	private Button mButtonStart;
	private Button mButtonReset;
	private Button[][] mButtonGrid;
	private ColorResourceMap mColorMap;
	
	private static final int GRID_ROWS = 4;
	private static final int GRID_COLS = 5;
	
	StartClickHandler mStartHandler;
	ResetClickHandler mResetHandler;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		//Get references to the views in the layout
		getReferenceToViews();
		
		//Create a new Drawable map to get drawable
		mColorMap = new ColorResourceMap(getResources());
		
	}

	private void getReferenceToViews() {
		//Create a new 2 dimensional array to hold the grid buttons
		mButtonGrid = new Button[GRID_ROWS][GRID_COLS];
		
		//Grab references to the grid buttons
		mButtonGrid[0][0] = (Button) findViewById(R.id.button00);
		mButtonGrid[0][1] = (Button) findViewById(R.id.button01);
		mButtonGrid[0][2] = (Button) findViewById(R.id.button02);
		mButtonGrid[0][3] = (Button) findViewById(R.id.button03);
		mButtonGrid[0][4] = (Button) findViewById(R.id.button04);
		mButtonGrid[1][0] = (Button) findViewById(R.id.button10);
		mButtonGrid[1][1] = (Button) findViewById(R.id.button11);
		mButtonGrid[1][2] = (Button) findViewById(R.id.button12);
		mButtonGrid[1][3] = (Button) findViewById(R.id.button13);
		mButtonGrid[1][4] = (Button) findViewById(R.id.button14);
		mButtonGrid[2][0] = (Button) findViewById(R.id.button20);
		mButtonGrid[2][1] = (Button) findViewById(R.id.button21);
		mButtonGrid[2][2] = (Button) findViewById(R.id.button22);
		mButtonGrid[2][3] = (Button) findViewById(R.id.button23);
		mButtonGrid[2][4] = (Button) findViewById(R.id.button24);
		mButtonGrid[3][0] = (Button) findViewById(R.id.button30);
		mButtonGrid[3][1] = (Button) findViewById(R.id.button31);
		mButtonGrid[3][2] = (Button) findViewById(R.id.button32);
		mButtonGrid[3][3] = (Button) findViewById(R.id.button33);
		mButtonGrid[3][4] = (Button) findViewById(R.id.button34);
		
		//Set the tag object to hold the views location in the grid
		for(int i = 0; i < GRID_ROWS*GRID_COLS; i++) {
			int row = i / GRID_COLS;
			int col = i % GRID_COLS;
			mButtonGrid[row][col].setTag(new RowColumnPair(row, col));
			mButtonGrid[row][col].setOnClickListener(this);
		}
		
		//Grab references to the start and reset buttons.
		mButtonStart = (Button) findViewById(R.id.buttonStart);
		mButtonReset = (Button) findViewById(R.id.buttonReset);
		
		mButtonStart.setOnClickListener(mStartHandler);
		mButtonReset.setOnClickListener(mResetHandler);
	}

	@Override
	public void onClick(View v) {
		RowColumnPair rowCol = (RowColumnPair) v.getTag();
	}
	
	private class StartClickHandler implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}
		
	}
	
	private class ResetClickHandler implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}
		
	}
	
	public interface MatchingGameView {
		void show(RowColumnPair rcp, Drawable drawable);
		void hide(RowColumnPair rcp);
	}
	
}
