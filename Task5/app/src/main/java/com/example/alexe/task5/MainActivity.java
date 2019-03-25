package com.example.alexe.task5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Element 1");
        strings.add("Element 2");
        strings.add("Element 3");
        strings.add("Element 4");
        strings.add("Element 5");
        strings.add("Element 6");
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,strings));
    }
}
