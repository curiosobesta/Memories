package sagar.memories.slider;

import sagar.memories.R;
import sagar.memories.slider.adapter.FullScreenImageAdapter;
import sagar.memories.utils.Globals;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

public class FullScreenViewActivity extends Activity{

	private Utils utils;
	private FullScreenImageAdapter adapter;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen_view);

		viewPager = (ViewPager) findViewById(R.id.pager);


		Intent i = getIntent();
		int position = i.getIntExtra("position", 0);
		Bundle basket = i.getExtras();
		String name = basket.getString(Globals.KEY_PERSON_NAME, "adi");


		//Set Theme Color
		int themeColor = getColor(name);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(themeColor);
		}
		findViewById(R.id.overlay).setBackgroundColor(themeColor);

		//-- Set Theme Color

		utils = new Utils(getApplicationContext(), name);

		adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,
				utils.getFilePaths(), name);

		viewPager.setAdapter(adapter);

		// displaying selected image first
		viewPager.setCurrentItem(position);
	}

	public int getColor(String personName){
		switch (personName){
			case Globals.KEY_ADI: return getResources().getColor(R.color.adi_color);
			case Globals.KEY_DIPAN: return getResources().getColor(R.color.dipan_color);
			case Globals.KEY_KRISHNA: return getResources().getColor(R.color.krishna_color);
			case Globals.KEY_SUVI: return getResources().getColor(R.color.suvi_color);
		}
		return -1;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
}
