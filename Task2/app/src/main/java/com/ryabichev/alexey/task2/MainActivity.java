package com.ryabichev.alexey.task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText input = findViewById(R.id.inputText);
		final TextView output = findViewById(R.id.showText);
		ImageButton imageButton = findViewById(R.id.imageButton);

		imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				output.setText(input.getText().toString());
			}
		});
	}
}
