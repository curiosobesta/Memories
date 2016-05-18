package sagar.memories.slider.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import sagar.memories.R;
import sagar.memories.slider.FullScreenViewActivity;
import sagar.memories.slider.Utils;
import sagar.memories.utils.Globals;

/**
 * Created by Sagar on 12-05-2016.
 */
public class TestPagerAdapter extends FragmentActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_view);

           Utils utils = new Utils(getApplicationContext(), "adi");

           Intent i = getIntent();
           int position = i.getIntExtra("position", 0);
           Bundle basket = getIntent().getExtras();
           String name = basket.getString(Globals.KEY_PERSON_NAME, "adi");


           mPager = (ViewPager) findViewById(R.id.pager);
           mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), this, utils.getFilePaths(), name);

           mPager.setAdapter(mPagerAdapter);
           mPager.setCurrentItem(position);
    }

    private class FragmentPagerAdapter extends FragmentStatePagerAdapter{

        FragmentActivity activity;
        ArrayList<String> filePaths;
        int imageWidth;
        String personName;
        public FragmentPagerAdapter(FragmentManager fm, FragmentActivity activity, ArrayList<String> filePaths, String personName) {
            super(fm);
            this.activity = activity;
            this.filePaths = filePaths;
            this.personName = personName;
        }

        @Override
        public Fragment getItem(int position) {
            TestPagerFragment fragment = new TestPagerFragment();

            Bundle bundle = new Bundle();
            bundle.putStringArrayList(Globals.KEY_FILES, filePaths);
            bundle.putString(Globals.KEY_PERSON_NAME, personName);
            bundle.putInt(Globals.KEY_POSITION, position);
            Globals.bitmap = getBitmap(position);
            /*Bitmap bitmap = getBitmap(position);
            bundle.putParcelable(Globals.KEY_BITMAP, bitmap);*/

            fragment.setFragmentActivity(activity);
            fragment.setArguments(bundle);

            return new TestPagerFragment();
        }


        public Bitmap getBitmap(int position){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            InputStream is = null;
            try {
                is = activity.getAssets().open(personName+"/"+filePaths.get(position));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(is,null ,options);
            return bitmap;
        }

        @Override
        public int getCount() {
            return filePaths.size();
        }
    }
}
