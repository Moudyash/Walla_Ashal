package com.moudy.alshafie.Worker.recevier;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.moudy.alshafie.BuildConfig;
import com.moudy.alshafie.Data.DataSets.NotificationDataset;
import com.moudy.alshafie.MainActivity;
import com.moudy.alshafie.R;


public class NotificationReceiver extends BroadcastReceiver {
   // Uri soundUri = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID  + "/" + R.raw.custom_notification_sound);
    @Override
    public void onReceive(Context context, Intent intent1) {


        Intent intent = new Intent(context, MainActivity.class);
        @SuppressLint("UnspecifiedImmutableFlag")
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.notifiaction_id));
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true);

        builder.setContentText(NotificationDataset.getNof());
        builder.setStyle(new NotificationCompat.BigTextStyle());

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
       // builder.setSound(soundUri);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        NotificationManagerCompat.from(context).notify(0, notification);


    }
}
