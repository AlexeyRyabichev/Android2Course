package com.ryabichev.task8;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

/**
 * The configuration screen for the {@link NewAppWidget NewAppWidget} AppWidget.
 */
public class NewAppWidgetConfigureActivity extends Activity {
    private NewAppWidgetConfigureActivity context;
    private int widgetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_app_widget_configure);
        setResult(RESULT_CANCELED);
        context = this;
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            final EditText editText = findViewById(R.id.appwidget_text);
            Button button = findViewById(R.id.add_button);


            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(editText.getText().toString()));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
            appWidgetManager.updateAppWidget(widgetID, remoteViews);
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    }
}

