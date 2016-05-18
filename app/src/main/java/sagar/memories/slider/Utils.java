package sagar.memories.slider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import sagar.memories.slider.helper.AppConstant;

public class Utils {

	private Context context;
	AssetManager assets;
	String[] assetFiles;
	String name;
	// constructor
	public Utils(Context context, String personName) {
		this.name = personName;
		this.context = context;
	}

	/*
	 * Reading file paths from SDCard
	 */
	public ArrayList<String> getFilePaths() {


		assets = context.getAssets();
		try {
			assetFiles = context.getAssets().list(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Arrays.sort(assetFiles);

		ArrayList<String> filePaths = new ArrayList<String>();
		for(String s: assetFiles){
			filePaths.add(s);
		}
		return filePaths;
	}

	/*
	 * Check supported file extensions
	 * 
	 * @returns boolean
	 */
	private boolean IsSupportedFile(String filePath) {
		String ext = filePath.substring((filePath.lastIndexOf(".") + 1),
				filePath.length());

		if (AppConstant.FILE_EXTN
				.contains(ext.toLowerCase(Locale.getDefault())))
			return true;
		else
			return false;

	}

	/*
	 * getting screen width
	 */
	public int getScreenWidth() {
		int columnWidth;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (NoSuchMethodError ignore) { // Older device
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		columnWidth = point.x;
		return columnWidth;
	}
}
