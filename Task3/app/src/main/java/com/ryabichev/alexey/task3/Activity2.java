package com.ryabichev.alexey.task3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_2);
		TextView oldtext = findViewById(R.id.oldText);
		ImageButton imageButton = findViewById(R.id.goBackward);

		oldtext.setText(getIntent().getStringExtra(MainActivity.EXTRA));
		imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
