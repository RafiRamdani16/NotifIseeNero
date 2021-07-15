package com.example.notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.notification.room.NotifViewModel;

import java.util.Date;

public class NotifService extends Service {
    private static final String CHANNEL_ID = "1";
    private NotifViewModel notifViewModel;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        tampilNotifikasi("Sungai Amazon","Bahaya");
        
        return START_STICKY;
    }

    public void tampilNotifikasi(String namaSungai, String tingkatBahaya){
        MainActivity activity = new MainActivity();
        activity.createNotificationChannel();
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_expanded);

        Bitmap bitmapIcon = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

//        Bundle bundle = new Bundle();
//        bundle.putString("namaSungai",namaSungai);
//        bundle.putString("tingkatBahaya",tingkatBahaya);


        Intent intent = new Intent(this, ListNotification.class);
//        intent.putExtra("namaSungai",namaSungai);
//        intent.putExtra("tingkatBahaya",tingkatBahaya);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifService.this, CHANNEL_ID)

                .setSmallIcon(R.drawable.logo)
                .setContentTitle(namaSungai)
                .setContentText(tingkatBahaya)
                .setLargeIcon(bitmapIcon)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setLights(Color.RED, 3000, 3000)
                ;
    }
}
