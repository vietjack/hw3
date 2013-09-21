package com.javangon.matchinggame;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

public class GameActivity extends Activity {

	private Button mButtonStart;
	private Button mButtonReset;
	private Button[][] mButtonGrid;
	
	private static final int GRID_ROWS = 4;
	private static final int GRID_COLS = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		//Get references to the views in the layout
		getReferenceToViews();
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
		
		//Grab references to the start and reset buttons.
		mButtonStart = (Button) findViewById(R.id.buttonStart);
		mButtonReset = (Button) findViewById(R.id.buttonReset);
		
		//Get resources to the color PNG files
		Resources res = getResources();
		Drawable black  = res.getDrawable(R.drawable.black);
		Drawable blue   = res.getDrawable(R.drawable.blue);
		Drawable brown  = res.getDrawable(R.drawable.brown);
		Drawable green  = res.getDrawable(R.drawable.green);
		Drawable orange = res.getDrawable(R.drawable.orange);
		Drawable pink   = res.getDrawable(R.drawable.pink);
		Drawable purple = res.getDrawable(R.drawable.purple);
		Drawable red    = res.getDrawable(R.drawable.red);
		Drawable white  = res.getDrawable(R.drawable.white);
		Drawable yellow = res.getDrawable(R.drawable.yellow);
	}
}
