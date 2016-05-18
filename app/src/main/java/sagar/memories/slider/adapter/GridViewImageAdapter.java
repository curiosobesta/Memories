package sagar.memories.slider.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import sagar.memories.R;
import sagar.memories.slider.FullScreenViewActivity;
import sagar.memories.slider.helper.BitmapWorkerTask;

public class GridViewImageAdapter extends BaseAdapter implements BitmapWorkerTask.Callback {

	private Activity _activity;
	private ArrayList<String> _filePaths = new ArrayList<String>();
	private int imageWidth;
	String name;
	ImageView imgView;
	private LruCache<String, Bitmap> mMemoryCache;

	public GridViewImageAdapter(Activity activity, ArrayList<String> filePaths,
			int imageWidth, String personName) {
		this._activity = activity;
		this._filePaths = filePaths;
		this.imageWidth = imageWidth;
		this.name = personName;

		//Cache Setup
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;

		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount();
			}
		};
		//-- Cache Setup
	}

	@Override
	public int getCount() {
		return this._filePaths.size();
	}

	@Override
	public Object getItem(int position) {
		return this._filePaths.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(_activity);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageWidth));
		} else {
			imageView = (ImageView) convertView;
		}

		imgView = imageView;
		loadBitmap(_filePaths.get(position), imageView, position);
		/*Bitmap bitmap = null;
		bitmap = getImageFromCache(_filePaths.get(position));
		if(bitmap == null)
			bitmap = getImage(_filePaths.get(position));*/
		/*imageView.setImageBitmap(bitmap);
		imageView.setOnClickListener(new OnImageClickListener(position));*/

		// Main Logic
		// get screen dimensions
		/*InputStream fileStream = null;
		try {
			fileStream = _activity.getAssets().open(name + "/" + _filePaths.get(position));
			Bitmap image = decodeFile(fileStream, imageWidth, imageWidth);

			imageView.setImageBitmap(image);

			// image view click listener
			imageView.setOnClickListener(new OnImageClickListener(position));

			return imageView;
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		//-- Main Logic
		return imageView;
	}

	public void loadBitmap(String fileName, ImageView imageView, int position) {
		final String imageKey = fileName;

		final Bitmap bitmap = getBitmapFromMemCache(imageKey);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else {
			imageView.setImageResource(R.drawable.transparent);
			BitmapWorkerTask task = new BitmapWorkerTask(_activity, imageWidth, imageView, position, this);
			task.execute(name+"/"+fileName);
		}
	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemCache(String key) {
		return mMemoryCache.get(key);
	}

	/*public Bitmap getImage(String fileName){
		InputStream fileStream = null;
		try {
			fileStream = _activity.getAssets().open(name + "/" + fileName);
			Bitmap image = decodeFile(fileStream, imageWidth, imageWidth);
			mMemoryCache.put(fileName, image);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/

	@Override
	public void onDecode(int position, Bitmap bitmap) {
		addBitmapToMemoryCache(_filePaths.get(position), bitmap);
	}

	/*
	 * Resizing image size
	 */
	public static Bitmap decodeFile(InputStream is, int WIDTH, int HIGHT) {

			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(is, null, o);

			final int REQUIRED_WIDTH = WIDTH;
			final int REQUIRED_HIGHT = HIGHT;
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_WIDTH
					&& o.outHeight / scale / 2 >= REQUIRED_HIGHT)
				scale *= 2;

			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale*2;
			return BitmapFactory.decodeStream(is, null, o2);
	}
}
