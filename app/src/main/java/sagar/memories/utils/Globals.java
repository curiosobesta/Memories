package sagar.memories.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import sagar.memories.FfMessages;
import sagar.memories.R;

/**
 * Created by Sagar on 12-05-2016.
 */
public class Globals {

    public static String KEY_FILES = "filepaths";
    public static String KEY_IMG_WIDTH = "imgWidth";
    public static String KEY_PERSON_NAME = "personName";
    public static String KEY_POSITION = "position";

    public static String KEY_BITMAP = "bitmap";

    public static Bitmap bitmap = null;

    final public static String KEY_ADI = "adi";
    final public static String KEY_DIPAN = "dipan";
    final public static String KEY_KRISHNA = "krishna";
    final public static String KEY_SUVI = "suvi";

    

    public static String THEME_PERSON;


    public static String getName(String key){
        switch (key){
            case KEY_ADI: return "Aditya Dahale";
            case KEY_DIPAN: return "Dipan Mirgani";
            case KEY_KRISHNA: return "Krishna Agarwal";
            case KEY_SUVI: return "Suvidha Maheshwari";
        }
        return null;
    }

    public static Drawable getDp(Resources resources, String personName){
        switch(personName){
            case KEY_ADI: return resources.getDrawable(R.drawable.dp_adi);
            case KEY_DIPAN: return resources.getDrawable(R.drawable.dp_dipan);
            case KEY_KRISHNA: return resources.getDrawable(R.drawable.dp_krishna);
            case KEY_SUVI: return resources.getDrawable(R.drawable.dp_suvidha);
        }
        return null;
    }

    public static String getMessage(String personName){
        switch(personName){
            case KEY_ADI: return FfMessages.getAboutAdi();
            case KEY_SUVI: return FfMessages.getAboutSuvi();
            case KEY_KRISHNA: return FfMessages.getAboutKrishna();
            case KEY_DIPAN: return FfMessages.getAboutDipan();
        }
        return null;
    }

    public static int getThemeColor(Resources resources, String personName){
        switch (personName){
            case Globals.KEY_ADI: return resources.getColor(R.color.adi_color);
            case Globals.KEY_DIPAN: return resources.getColor(R.color.dipan_color);
            case Globals.KEY_KRISHNA: return resources.getColor(R.color.krishna_color);
            case Globals.KEY_SUVI: return resources.getColor(R.color.suvi_color);

        }
        return -1;
    }

    public static int getThemeColorDark(Resources resources, String personName){
        switch (personName){
            case Globals.KEY_ADI: return resources.getColor(R.color.adi_color_dark);
            case Globals.KEY_DIPAN: return resources.getColor(R.color.dipan_color_dark);
            case Globals.KEY_KRISHNA: return resources.getColor(R.color.krishna_color_dark);
            case Globals.KEY_SUVI: return resources.getColor(R.color.suvi_color_dark);

        }
        return -1;
    }
}
