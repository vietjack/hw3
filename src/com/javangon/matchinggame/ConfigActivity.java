package com.javangon.matchinggame;

import java.io.File;
import java.io.FilenameFilter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * This activity will be responsible for showing a list of all of the images
 * located in the root directory of the SD card and allowing the user to select
 * any number of the images.  The activity will return a list of paths to the
 * images that the user selected.
 * 
 * @author Zack
 *
 */
public class ConfigActivity extends ListActivity implements OnClickListener {

	private boolean[] isChecked;
	private String[] filenameList;
	private File rootDir;
	public static final String EXTRA_FILE_PATHS = "EFP";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_config);
		Button buttonSubmit = (Button) findViewById(R.id.submit);
		buttonSubmit.setOnClickListener(this);
		
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) { //Check if external storage is available
			//Get a File object handle to the root directory
			rootDir = Environment.getExternalStorageDirectory();
			//Scan root directory for image files.
			filenameList = rootDir.list(new ImageFilenameFilter());
			
			//Create a checked array for the list initially set to false.
			isChecked = new boolean[filenameList.length];
			
			for(boolean b : isChecked) {
				b = false;
			}
			
			getListView().setAdapter(new ThumbnailSelectorAdapter(rootDir, filenameList));
			
			//Display the image files to user to let make a selection
			//Create an Intent
			//Add all paths to image files as extras
		}
	}
	
	@Override
	public void onClick(View arg0) {
		int trueCount = 0;
		Intent intent = new Intent();
		for(boolean b : isChecked) {
			trueCount = b ? trueCount + 1 : trueCount;
		}

		String path = rootDir.getAbsolutePath();
		String[] checkedImages = new String[trueCount];
		int cur = 0;
		for(int i = 0; i < trueCount; i++) {
			if(isChecked[i]) {
				if(!path.endsWith(File.separator)){
					path +=  File.separator;
				}
				checkedImages[cur] = path + filenameList[i];
				cur++;
			}
		}
		intent.putExtra(EXTRA_FILE_PATHS, checkedImages);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}

	private class ImageFilenameFilter implements FilenameFilter {

		@Override
		public boolean accept(File file, String filename) {
			
			//Get the filename extension
			int dot = filename.lastIndexOf('.');
			String base = (dot == -1) ? filename : filename.substring(0, dot);
			String extension = (dot == -1) ? "" : filename.substring(dot+1);
			
			//Return true if filename extension is png, jpeg, jpg, or gif
			return extension.equalsIgnoreCase("png")  ? true : 
				  (extension.equalsIgnoreCase("jpg")  ? true : 
				  (extension.equalsIgnoreCase("jpeg") ? true :
				  (extension.equalsIgnoreCase("gif")  ? true : false)));
		}
	}
	
	private class ThumbnailSelectorAdapter extends BaseAdapter {

		private final String[] files;
		private final File root;
		
		public ThumbnailSelectorAdapter(File root, String[] files) {
			this.root = root;
			this.files = files;
		}
		
		@Override
		public int getCount() {
			return files.length;
		}

		@Override
		public Object getItem(int position) {
			return files[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.layout_thumbnail_checkbox_item, null);
			}
			
			String path = root.getAbsolutePath();
			if(!path.endsWith(File.separator)){
				path +=  File.separator;
			}
			ImageView tn = (ImageView) convertView.findViewById(R.id.thumbnail);
			Drawable drawable = Drawable.createFromPath(path + files[position]);
			
			
			
			Bitmap bb=((BitmapDrawable) drawable).getBitmap();

			int width = bb.getWidth();
			int height = bb.getHeight();           
			  
			float scaleWidth = ((float) 50) / width;
			float scaleHeight = ((float) 50) / height;
			
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			
			Bitmap resultBitmap = Bitmap.createBitmap(bb, 0, 0,width, height, matrix, true);
			drawable = new BitmapDrawable(resultBitmap);
			
			tn.setImageDrawable(drawable);

			CompoundButton checkbox = (CompoundButton) convertView.findViewById(R.id.checkbox);
			checkbox.setChecked(isChecked[position]);
			
			checkbox.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					isChecked[position] = !isChecked[position];
					getListView().setItemChecked(position, isChecked[position]);
				}
			});
			
			return convertView;
		}
		
	}
}
