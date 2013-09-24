package com.javangon.matchinggame;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.javangon.matchinggame.GamePlayManager.MatchingGameView;

public class GameActivity extends Activity implements OnClickListener, MatchingGameView {

	private Button mButtonStart;
	private Button mButtonReset;
	private ImageButton[][] mButtonGrid;
	private TextView mTextViewWinner;
	private TextView mTimerView;
	private GridLayout mGridLayout;
	
	private ColorResourceMap mColorMap;
	private GamePlayManager mGamePlayManager;
	
	private static final int GRID_ROWS = 4;
	private static final int GRID_COLS = 5;
	
	private int mTimeElapsed = 0;
	
	private Timer mGameTimer;
	
	StartClickHandler mStartHandler;
	ResetClickHandler mResetHandler;
	
	private boolean mGameActive = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		mResetHandler = new ResetClickHandler();
		mStartHandler = new StartClickHandler();
		
		//Get references to the views in the layout
		getReferenceToViews();
		
		//Create a new Drawable map to get drawable
		mColorMap = new ColorResourceMap(getResources());
		
		mGamePlayManager = new GamePlayManager(this);
		
		mTimerView.setText("0");
	}

	private void getReferenceToViews() {
		//Create a new 2 dimensional array to hold the grid buttons
		mButtonGrid = new ImageButton[GRID_ROWS][GRID_COLS];
		
		//Grab references to the grid buttons
		mButtonGrid[0][0] = (ImageButton) findViewById(R.id.button00);
		mButtonGrid[0][1] = (ImageButton) findViewById(R.id.button01);
		mButtonGrid[0][2] = (ImageButton) findViewById(R.id.button02);
		mButtonGrid[0][3] = (ImageButton) findViewById(R.id.button03);
		mButtonGrid[0][4] = (ImageButton) findViewById(R.id.button04);
		mButtonGrid[1][0] = (ImageButton) findViewById(R.id.button10);
		mButtonGrid[1][1] = (ImageButton) findViewById(R.id.button11);
		mButtonGrid[1][2] = (ImageButton) findViewById(R.id.button12);
		mButtonGrid[1][3] = (ImageButton) findViewById(R.id.button13);
		mButtonGrid[1][4] = (ImageButton) findViewById(R.id.button14);
		mButtonGrid[2][0] = (ImageButton) findViewById(R.id.button20);
		mButtonGrid[2][1] = (ImageButton) findViewById(R.id.button21);
		mButtonGrid[2][2] = (ImageButton) findViewById(R.id.button22);
		mButtonGrid[2][3] = (ImageButton) findViewById(R.id.button23);
		mButtonGrid[2][4] = (ImageButton) findViewById(R.id.button24);
		mButtonGrid[3][0] = (ImageButton) findViewById(R.id.button30);
		mButtonGrid[3][1] = (ImageButton) findViewById(R.id.button31);
		mButtonGrid[3][2] = (ImageButton) findViewById(R.id.button32);
		mButtonGrid[3][3] = (ImageButton) findViewById(R.id.button33);
		mButtonGrid[3][4] = (ImageButton) findViewById(R.id.button34);
		
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
		
		mTextViewWinner = (TextView) findViewById(R.id.textViewWinner);
		mTimerView = (TextView) findViewById(R.id.textViewTimer);
		
		mGridLayout = (GridLayout) findViewById(R.id.grid);
		mGridLayout.setActivated(false);
	}

	@Override
	public void onClick(View v) {
		RowColumnPair rowCol = (RowColumnPair) v.getTag();
		if(!mGameActive) {
			mTextViewWinner.setText("click start to play");
			return;
		}
		try {
			mGamePlayManager.select(rowCol);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class StartClickHandler implements OnClickListener {

		@Override
		public void onClick(View v) {
			if(mGameActive == true) return; //
			mGameActive = true;
			mTextViewWinner.setText("");
			mTimerView.setText("0");
			if(mGameTimer != null) {
				mGameTimer.cancel();
				mGameTimer = null;
			}
			mGameTimer = new Timer();
			mGameTimer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					mTimeElapsed++;
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							mTimerView.setText(""+mTimeElapsed);
						}
					});
				}
			}, 1000, 1000);
		}
		
	}
	
	private class ResetClickHandler implements OnClickListener {

		@Override
		public void onClick(View v) {
			mGameActive = false;
			for(int i=0; i<20; i++) {
				int r = i / 5;
				int c = i % 5;
				mButtonGrid[r][c].setImageResource(R.drawable.ic_launcher);
			}
			mGamePlayManager.reset();
			mTextViewWinner.setText("");
			if(mGameTimer != null) {
				mGameTimer.cancel();
				mGameTimer = null;
			}
			mTimeElapsed = 0;
			mTimerView.setText("" + mTimeElapsed);
		}
		
	}

	public void show(final RowColumnPair rcp, final Color color) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				int r = rcp.getRow();
				int c = rcp.getColumn();
				ImageButton b = mButtonGrid[r][c];
				b.setImageDrawable(mColorMap.getDrawable(color));
			}
		});
	}

	public void hide(final RowColumnPair rcp) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				int r = rcp.getRow();
				int c = rcp.getColumn();
				ImageButton b = mButtonGrid[r][c];
				b.setImageResource(R.drawable.ic_launcher);
			}
		});
	}

	@Override
	public void declareWinner() {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mTextViewWinner.setText("winner!");
				if(mGameTimer != null) {
					mGameTimer.cancel();
					mGameTimer = null;
				}
			}
		});
	}
	
}
