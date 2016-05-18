package sagar.memories.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import sagar.memories.R;
import sagar.memories.Utils;

/**
 * Created by Sagar on 11-05-2016.
 */
public class GalleryAdapter extends BaseAdapter{

    Context context;
    String assetFiles[];
    AssetManager assets;
    LayoutInflater inflater;
    String name;

    public GalleryAdapter(Context context, String name){
        this.context = context;
        assets = context.getAssets();
        try {
            assetFiles = context.getAssets().list(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Arrays.sort(assetFiles);
        this.name = name;
        inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return assetFiles.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{

        public ImageView imgView;

    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if(view == null){
            view = inflater.inflate(R.layout.inner_gallery, null);
            holder = new ViewHolder();
            holder.imgView = (ImageView) view.findViewById(R.id.gallery_img);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        try {
            String path = name + "/" + assetFiles[assetFiles.length-1 - position];
            Log.e("MyTag", path);
            InputStream is = assets.open(path);
            //holder.imgView.setBackground(new BitmapDrawable(context.getResources(), decodeSmallImage(is, 100, 100)));

            holder.imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Utils utils = new Utils(context);
            int imageWidth = (utils.getScreenWidth() - (3 * 2)) / 2;
            holder.imgView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageWidth));
            holder.imgView.setImageBitmap(decodeSmallImage(is, 100, 100));
            //holder.imgView.setImageBitmap(BitmapFactory.decodeStream(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    public static Bitmap decodeSmallImage(InputStream is, int reqWidth, int reqHeight) {

        //First Load to check width and height of Image
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeStream(is, null, options);
        //-- First Load to check width and height of Image

        //Calculate Scaling Factor
        options.inSampleSize = calculateInSampleFactor(options, reqWidth, reqHeight);
        //-- Calculate Scaling Factor

        //Decode reduced Image
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(is, null, options);
        //-- Decode reduce Image
    }

    public static int calculateInSampleFactor(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
