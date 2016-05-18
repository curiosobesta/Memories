package sagar.memories.slider.helper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import sagar.memories.R;
import sagar.memories.slider.FullScreenViewActivity;
import sagar.memories.utils.Globals;

/**
 * Created by Sagar on 13-05-2016.
 */
public class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

    Activity activity;
    int imageWidth;
    Callback callback;
    ImageView imgView;
    int position;

    public BitmapWorkerTask(Activity activity, int imageWidth, ImageView imgView, int position, Callback callback){
        this.activity = activity;
        this.imageWidth = imageWidth;
        this.callback = callback;
        this.imgView = imgView;
        this.position = position;
    }

    @Override
    protected Bitmap doInBackground(String... filePaths) {

        InputStream fileStream = null;
        try {
            fileStream = activity.getAssets().open(filePaths[0]);
            Bitmap image = decodeFile(fileStream, imageWidth, imageWidth);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


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

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imgView.setImageBitmap(bitmap);
        imgView.setOnClickListener(new OnImageClickListener(position));
        callback.onDecode(position, bitmap);
        super.onPostExecute(bitmap);
    }

    public interface Callback{
        void onDecode(int position, Bitmap bitmap);
    }

    class OnImageClickListener implements View.OnClickListener {

        int _postion;

        // constructor
        public OnImageClickListener(int position) {
            this._postion = position;
        }

        @Override
        public void onClick(View v) {
            // on selecting grid view image
            // launch full screen activity
            Intent i = new Intent(activity, FullScreenViewActivity.class);
            i.putExtra("position", _postion);
            i.putExtra(Globals.KEY_PERSON_NAME, Globals.THEME_PERSON);
            activity.startActivity(i);
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

    }
}
