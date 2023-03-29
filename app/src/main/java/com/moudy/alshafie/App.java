package com.moudy.alshafie;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel Channel1 = new NotificationChannel(getString(R.string.notifiaction_id), getString(R.string.app_name)
                    , NotificationManager.IMPORTANCE_DEFAULT);

            Channel1.setDescription("bodoor");
            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(Channel1);
        }
    }
}