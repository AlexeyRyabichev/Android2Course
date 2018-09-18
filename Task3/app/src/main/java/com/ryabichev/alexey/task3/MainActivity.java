package com.ryabichev.alexey.task3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

	public static String EXTRA = "EXTRA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText input = findViewById(R.id.inputText);
		ImageButton imageButton = findViewById(R.id.goForward);

		imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Activity2.class);
				intent.putExtra(EXTRA, input.getText().toString());
				startActivity(intent);
			}
		});
	}
}
