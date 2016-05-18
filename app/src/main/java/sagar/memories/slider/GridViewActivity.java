package sagar.memories.slider;

import sagar.memories.R;
import sagar.memories.slider.adapter.GridViewImageAdapter;
import sagar.memories.slider.helper.AppConstant;
import sagar.memories.utils.Globals;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

public class GridViewActivity extends AppCompatActivity {

	private Utils utils;
	private ArrayList<String> imagePaths = new ArrayList<String>();
	private GridViewImageAdapter adapter;
	private GridView gridView;
	private int columnWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_gallery);


		gridView = (GridView) findViewById(R.id.image_grid);
		Bundle basket = getIntent().getExtras();
		String name = basket.getString(Globals.KEY_PERSON_NAME, "adi");
		Globals.THEME_PERSON = name;
		//String name = "adi";
		utils = new Utils(this, name);

		//Set Theme Color
		int themeColor = Globals.getThemeColor(getResources(), name);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(themeColor);
		}
		findViewById(R.id.overlay).setBackgroundColor(themeColor);
		//-- Set Theme Color

		// Initilizing Grid View
		InitilizeGridLayout();

		// loading all image paths from SD card
		imagePaths = utils.getFilePaths();

		// Gridview adapter
		adapter = new GridViewImageAdapter(GridViewActivity.this, imagePaths,
				columnWidth, name);

		// setting grid view adapter
		gridView.setAdapter(adapter);
	}

	private void InitilizeGridLayout() {
		Resources r = getResources();
		float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				AppConstant.GRID_PADDING, r.getDisplayMetrics());

		columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1) * padding)) / AppConstant.NUM_OF_COLUMNS);

		gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
		gridView.setColumnWidth(columnWidth);
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setPadding((int) padding, (int) padding, (int) padding,
				(int) padding);
		gridView.setHorizontalSpacing((int) padding);
		gridView.setVerticalSpacing((int) padding);
	}



}
