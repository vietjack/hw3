package com.javangon.matchinggame;

import java.util.EnumMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ColorResourceMap {
	
	private final Resources res;
	private Map<CellColors, Drawable> map;
	
	public ColorResourceMap(Resources res) {
		this.res = res;
		
		map = new EnumMap<CellColors, Drawable>(CellColors.class);
		map.put(CellColors.BLACK , res.getDrawable(R.drawable.black ));
		map.put(CellColors.BLUE  , res.getDrawable(R.drawable.blue  ));
		map.put(CellColors.BROWN , res.getDrawable(R.drawable.brown ));
		map.put(CellColors.GREEN , res.getDrawable(R.drawable.green ));
		map.put(CellColors.ORANGE, res.getDrawable(R.drawable.orange));
		map.put(CellColors.PINK  , res.getDrawable(R.drawable.pink  ));
		map.put(CellColors.PURPLE, res.getDrawable(R.drawable.purple));
		map.put(CellColors.RED   , res.getDrawable(R.drawable.red));
		map.put(CellColors.WHITE , res.getDrawable(R.drawable.white));
		map.put(CellColors.YELLOW, res.getDrawable(R.drawable.yellow));
	}
	
	public Drawable getDrawable(CellColors color) {
		return map.get(color);
	}
	

}
