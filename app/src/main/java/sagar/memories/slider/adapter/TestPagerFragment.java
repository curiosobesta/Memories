package sagar.memories.slider.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import sagar.memories.R;
import sagar.memories.slider.helper.TouchImageView;
import sagar.memories.utils.Globals;

/**
 * Created by Sagar on 12-05-2016.
 */
public class TestPagerFragment extends Fragment {

    private FragmentActivity _activity;
    private ArrayList<String> _filePaths = new ArrayList<String>();
    private int imageWidth;
    String name;
    private int position;
    private Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TouchImageView imgDisplay;
        Button btnClose;

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_fullscreen_image, container, false);

        imgDisplay = (TouchImageView) rootView.findViewById(R.id.imgDisplay);
        btnClose = (Button) rootView.findViewById(R.id.btnClose);

        imgDisplay.setImageBitmap(Globals.bitmap);

        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {









                /*_activity.finish();*/


            }
        });

        return rootView;
    }

    public void setFragmentActivity(FragmentActivity activity){
        this._activity = activity;
    }

    @Override
    public void setArguments(Bundle bundle) {
        _filePaths = bundle.getStringArrayList(Globals.KEY_FILES);
        name = bundle.getString(Globals.KEY_PERSON_NAME);
        position = bundle.getInt(Globals.KEY_POSITION);
        //bitmap = bundle.getParcelable(Globals.KEY_BITMAP);
    }
}
