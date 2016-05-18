package sagar.memories;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sagar.memories.dialogs.MyAlert;
import sagar.memories.dialogs.PassAlert;
import sagar.memories.dialogs.SecretAlert;
import sagar.memories.slider.GridViewActivity;
import sagar.memories.utils.Globals;

public class AboutPerson extends AppCompatActivity implements MyAlert.Callback, PassAlert.Callback{

    String personName = "adi";
    FloatingActionButton fab, fabSecret, fabGift, fabGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabSecret = (FloatingActionButton) findViewById(R.id.fabSecret);
        fabSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(AboutPerson.this, GridViewActivity.class);
                intent.putExtra(Globals.KEY_PERSON_NAME, personName);
                startActivity(intent);*/

                fab.show();
                fabGift.hide();
                fabSecret.hide();
                fabGallery.hide();

                showSecretPasswordDialog();
            }
        });

        fabGift = (FloatingActionButton) findViewById(R.id.fabGift);
        fabGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(AboutPerson.this, GridViewActivity.class);
                intent.putExtra(Globals.KEY_PERSON_NAME, personName);
                startActivity(intent);*/

                fab.show();
                fabGift.hide();
                fabSecret.hide();
                fabGallery.hide();

                /*Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                File file = new File("file:///android_asset/dipan.mp3");
                intent.setDataAndType(Uri.fromFile(file), "audio*//*");
                startActivity(intent);*/

                switch (personName){
                    case Globals.KEY_ADI: playForAdi(); break;
                    case Globals.KEY_DIPAN: showPasswordDialog(); break;
                    case Globals.KEY_KRISHNA: break;
                    case Globals.KEY_SUVI: playForSuvi(); break;
                }
            }
        });

        fabGallery = (FloatingActionButton) findViewById(R.id.fabGallery);
        fabGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.show();
                fabGift.hide();
                fabSecret.hide();
                fabGallery.hide();

                Intent intent = new Intent(AboutPerson.this, GridViewActivity.class);
                intent.putExtra(Globals.KEY_PERSON_NAME, personName);
                startActivity(intent);
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(AboutPerson.this, GridViewActivity.class);
                intent.putExtra(Globals.KEY_PERSON_NAME, personName);
                startActivity(intent);*/

                fab.hide();

                if(personName.equals(Globals.KEY_DIPAN) || personName.equals(Globals.KEY_SUVI) || personName.equals(Globals.KEY_ADI))
                    fabGift.show();

                fabSecret.show();
                fabGallery.show();
            }
        });


        personName = getIntent().getStringExtra(Globals.KEY_PERSON_NAME);
        TextView tv = (TextView) findViewById(R.id.about_msg);
        tv.setText(Html.fromHtml(Globals.getMessage(personName)));

        CollapsingToolbarLayout collapsebar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsebar.setTitle(Globals.getName(personName));
        collapsebar.setBackground(Globals.getDp(getResources(), personName));
        int themeColor = Globals.getThemeColor(getResources(), personName);
        int themeColorDark = Globals.getThemeColorDark(getResources(), personName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(themeColorDark);
        }
        collapsebar.setContentScrimColor(themeColor);
        fabGallery.setBackgroundTintList(ColorStateList.valueOf(themeColor));
        fabSecret.setBackgroundTintList(ColorStateList.valueOf(themeColor));
        fabGift.setBackgroundTintList(ColorStateList.valueOf(themeColor));
        fab.setBackgroundTintList(ColorStateList.valueOf(themeColor));

        fabGallery.hide();
        fabSecret.hide();
        fabGift.hide();
    }

    public void showPasswordDialog(){
        MyAlert alert  = new MyAlert();
        Bundle bundle = new Bundle();
        bundle.putString(Globals.KEY_PERSON_NAME, personName);
        alert.setArguments(bundle);
        alert.show(this.getSupportFragmentManager(), "Password dialog");
    }

    public void showSecretPasswordDialog(){
        PassAlert alert  = new PassAlert();
        Bundle bundle = new Bundle();
        bundle.putString(Globals.KEY_PERSON_NAME, personName);
        alert.setArguments(bundle);
        alert.show(this.getSupportFragmentManager(), "Password dialog");
    }

    public void playForDipan(){

        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/memories");
        if(!dir.isDirectory()) dir.mkdirs();

            InputStream ins;
            try {
                ins = getAssets().open("dipan.mp3");
                int size;
                size = ins.available();
                byte[] buffer = new byte[size];
                ins.read(buffer);
                ins.close();
                FileOutputStream fos = new FileOutputStream(new File(dir,"dipan.mp3"));
                fos.write(buffer);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        File myvid = new File(dir, "dipan.mp3");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(myvid), "audio/*");
        this.startActivity(intent);
    }

    public void playForSuvi(){
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/memories");
        if(!dir.isDirectory()) dir.mkdirs();

        InputStream ins;
        try {
            ins = getAssets().open("suvi.pdf");
            int size;
            size = ins.available();
            byte[] buffer = new byte[size];
            ins.read(buffer);
            ins.close();
            FileOutputStream fos = new FileOutputStream(new File(dir,"suvi.pdf"));
            fos.write(buffer);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        File myvid = new File(dir, "suvi.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(myvid), "application/pdf");
        this.startActivity(intent);
    }

    public void playForAdi(){
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/memories");
        if(!dir.isDirectory()) dir.mkdirs();

        InputStream ins;
        try {
            ins = getAssets().open("adi.png");
            int size;
            size = ins.available();
            byte[] buffer = new byte[size];
            ins.read(buffer);
            ins.close();
            FileOutputStream fos = new FileOutputStream(new File(dir,"adi.png"));
            fos.write(buffer);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        File myvid = new File(dir, "adi.png");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(myvid), "image/*");
        this.startActivity(intent);
    }

    @Override
    public void onTextEntered() {
        switch (personName){
            case Globals.KEY_ADI: playForAdi(); break;
            case Globals.KEY_DIPAN: playForDipan(); break;
        }
    }

    @Override
    public void callback() {
        SecretAlert p = new SecretAlert();
        Bundle b = new Bundle();
        b.putString(Globals.KEY_PERSON_NAME, personName);
        p.setArguments(b);
        p.show(getSupportFragmentManager(), "Secret Dialog");
    }

    @Override
    public void invalidPass() {
        Toast.makeText(AboutPerson.this, "Invalid Password", Toast.LENGTH_SHORT).show();
    }
}
