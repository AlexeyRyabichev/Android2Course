package com.ryabichev.task15;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextScore;
    Button saveButton;
    ListView listViewItems;
    SQLiteDatabase database;

    private ArrayList<String> listOfItems = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // variables
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE", 0);
        editTextName = findViewById(R.id.editTextName);
        editTextScore = findViewById(R.id.editTextScore);
        saveButton = findViewById(R.id.saveButton);
        listViewItems = findViewById(R.id.listViewItems);

        // loading settings
        editTextName.setText(sharedPreferences.getString("text", ""));
        editTextScore.setText(sharedPreferences.getString("score", ""));

        // open DB
        database = openOrCreateDatabase("AndroidDB", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS AndroidTable (Name VARCHAR, Number INT);");

        // Load items from DB
        loadItemsFromDB();

        // Listeners
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String score = editTextScore.getText().toString();

                if (name.equals("") || score.equals("")) {
                    Toast.makeText(MainActivity.this, "Please, check values", Toast.LENGTH_SHORT).show();
                    return;
                }
                String execString = "INSERT INTO AndroidTable (Name, Number) VALUES ('" +
                        name +
                        "'," +
                        score +
                        ");";
                database.execSQL(execString);
                loadItemsFromDB();
            }
        });
    }

    private void loadItemsFromDB() {
        listOfItems.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM AndroidTable", null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data in DB", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                String age = cursor.getString(cursor.getColumnIndex("Number"));
                listOfItems.add("Name: " + name + "\t\tScore: " + age);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfItems);
            listViewItems.setAdapter(arrayAdapter);
        }
        cursor.close();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Saving settings
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("text", editTextName.getText().toString());
        editor.putString("score", editTextScore.getText().toString());
        editor.apply();

        // Closing DB
        database.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.about) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            try {
                dialog.setMessage(getTitle().toString() + " версия " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName + "\r\n\nПрограмма с примером выполнения диалогового окна \r\n\n Автор - Рябичев Алексей Михайлович, гр. БПИ175");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            dialog.setTitle("О программе");
            dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setIcon(R.mipmap.ic_launcher_round);
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
