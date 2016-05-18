package sagar.memories;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash);

        final int TIME = 7000;
        final float SCALE_FACTOR = 1.25f;
        final String font = "virgo.ttf";

        final ImageView img = (ImageView) findViewById(R.id.splash);
        img.animate().scaleX(SCALE_FACTOR).setDuration(TIME)
                .scaleY(SCALE_FACTOR).setDuration(TIME)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        img.animate()
                                .scaleX(1.1f).setDuration(TIME)
                                .scaleY(1.1f).setDuration(TIME);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {}

                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 9000);

        TextView tv = (TextView) findViewById(R.id.tv);
        Typeface tf = Typeface.createFromAsset(getAssets(), font );
        tv.setTypeface(tf);
    }
}
