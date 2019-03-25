package com.ryabichev.task9;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.name);
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.color_red) {
            TextView textView = (TextView) findViewById(R.id.name);
            textView.setTextColor(Color.parseColor("red"));
        }
        if (id == R.id.color_black) {
            TextView textView = (TextView) findViewById(R.id.name);
            textView.setTextColor(Color.parseColor("black"));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.show_text) {
            if (item.isChecked()) {
                TextView textView = findViewById(R.id.name);
                textView.setVisibility(TextView.VISIBLE);
                item.setChecked(false);
            } else {
                TextView textView = findViewById(R.id.name);
                textView.setVisibility(TextView.INVISIBLE);
                item.setChecked(true);
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
