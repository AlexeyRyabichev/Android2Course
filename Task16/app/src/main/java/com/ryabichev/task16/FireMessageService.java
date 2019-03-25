package com.ryabichev.task16;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireMessageService extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("MSG", "Message received [" + remoteMessage + "]");

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1410, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.common_full_open_on_phone)
                .setContentTitle("Message")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager)

                        getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1410,
                notificationBuilder.build());

//        Context context = this;
//        // Sets an ID for the notification, so it can be updated.
//        int notifyID = 1;
//        String CHANNEL_ID = "my_channel_01";// The id of the channel.
//        CharSequence name = "channel";// The user-visible name of the channel.
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//        // Create a notification and set the notification channel.
//        Notification notification = new Notification.Builder(this)
//                .setContentTitle("Title")
//                .setContentText("Content")
//                .setSmallIcon(android.R.drawable.ic_dialog_alert)
//                .setChannelId(CHANNEL_ID)
//                .setTicker("Notification!")
//                .build();
//
//        NotificationManager mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.createNotificationChannel(mChannel);
//
//        // Issue the notification.
//        mNotificationManager.notify(notifyID, notification);


    }
}
