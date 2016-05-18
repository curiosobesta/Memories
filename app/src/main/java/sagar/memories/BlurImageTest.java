package sagar.memories;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import jp.wasabeef.blurry.Blurry;


public class BlurImageTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_image_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        findViewById(R.id.blurImg).setOnLongClickListener(new View.OnLongClickListener() {

            private boolean blurred = false;

            @Override
            public boolean onLongClick(View v) {
                if (blurred) {
                    Blurry.delete((ViewGroup) findViewById(R.id.rootView));
                } else {
                    long startMs = System.currentTimeMillis();
                    Blurry.with(BlurImageTest.this)
                            .radius(25)
                            .sampling(2)
                            .async()
                            .animate(500)
                            .onto((ViewGroup) findViewById(R.id.rootView));
                    Log.d(getString(R.string.app_name),
                            "TIME " + String.valueOf(System.currentTimeMillis() - startMs) + "ms");
                }

                blurred = !blurred;
                return true;
            }
        });
    }

}
