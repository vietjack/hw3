package com.javangon.matchinggame;

import java.util.EnumMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ColorResourceMap {
	
	private final Resources res;
	private Map<Color, Drawable> map;
	
	public ColorResourceMap(Resources res) {
		this.res = res;
		
		map = new EnumMap<Color, Drawable>(Color.class);
		map.put(Color.BLACK , res.getDrawable(R.drawable.black ));
		map.put(Color.BLUE  , res.getDrawable(R.drawable.blue  ));
		map.put(Color.BROWN , res.getDrawable(R.drawable.brown ));
		map.put(Color.GREEN , res.getDrawable(R.drawable.green ));
		map.put(Color.ORANGE, res.getDrawable(R.drawable.orange));
		map.put(Color.PINK  , res.getDrawable(R.drawable.pink  ));
		map.put(Color.PURPLE, res.getDrawable(R.drawable.purple));
		map.put(Color.RED   , res.getDrawable(R.drawable.red   ));
		map.put(Color.WHITE , res.getDrawable(R.drawable.white ));
		map.put(Color.YELLOW, res.getDrawable(R.drawable.yellow));
		map.put(Color.NONE  , res.getDrawable(R.drawable.ic_launcher));
	}
	
	public Drawable getDrawable(Color color) {
		return map.get(color);
	}
	

}
