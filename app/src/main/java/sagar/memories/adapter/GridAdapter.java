package sagar.memories.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import sagar.memories.AboutPerson;
import sagar.memories.GalleryActivity;
import sagar.memories.R;
import sagar.memories.slider.GridViewActivity;
import sagar.memories.utils.Globals;

/**
 * Created by Sagar on 11-05-2016.
 */
public class GridAdapter extends BaseAdapter {

    final int count = 4;
    Context context;

    public GridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.inner_main, null);
        Button nameBtn = (Button) view.findViewById(R.id.nameBtn);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        View underline = view.findViewById(R.id.underline);

        switch (position){
            case 0:
                nameBtn.setText("Aditya Dahale");
                img.setImageDrawable(context.getResources().getDrawable(R.drawable.adi));
                underline.setBackgroundColor(Globals.getThemeColor(context.getResources(), Globals.KEY_ADI));
                break;
            case 1:
                nameBtn.setText("Dipan Mirgani");
                img.setImageDrawable(context.getResources().getDrawable(R.drawable.dipan));
                underline.setBackgroundColor(Globals.getThemeColor(context.getResources(), Globals.KEY_DIPAN));
                break;
            case 2:
                nameBtn.setText("Krishna Agrawal");
                img.setImageDrawable(context.getResources().getDrawable(R.drawable.krishna));
                underline.setBackgroundColor(Globals.getThemeColor(context.getResources(), Globals.KEY_KRISHNA));
                break;
            case 3:
                nameBtn.setText("Suvidha Maheshwari");
                img.setImageDrawable(context.getResources().getDrawable(R.drawable.suvi));
                underline.setBackgroundColor(Globals.getThemeColor(context.getResources(), Globals.KEY_SUVI));
                break;
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.shake);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(context, AboutPerson.class);

                        switch (v.getId()){
                            case 0: intent.putExtra(Globals.KEY_PERSON_NAME, Globals.KEY_ADI); break;
                            case 1: intent.putExtra(Globals.KEY_PERSON_NAME, Globals.KEY_DIPAN); break;
                            case 2: intent.putExtra(Globals.KEY_PERSON_NAME, Globals.KEY_KRISHNA); break;
                            case 3: intent.putExtra(Globals.KEY_PERSON_NAME, Globals.KEY_SUVI); break;
                        }

                        context.startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                v.findViewById(R.id.underline).startAnimation(anim);
            }
        });

        view.setId(position);
        return view;
    }
}
