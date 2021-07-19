package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notification.room.NotifViewModel;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private NotifViewModel notifViewModel;
    private static final String CHANNEL_ID = "1";
    Button alarmTrigger;
    int angka1;
    int angka2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTrigger = findViewById(R.id.trigger);
        Random random = new Random();

        alarmTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration = Toast.LENGTH_SHORT;
                angka1 = 1 + random.nextInt(3);
                angka2 = 1 + random.nextInt(3);
                Toast toast = Toast.makeText(getApplicationContext(), "angka1 =" + angka1 + " angka2 = "+ angka2, duration);
                toast.show();
                if(angka1 == 3 && angka2 == 3){
                    tampilNotifikasi("Sungai Gangga12","Waspada");
                }else{
                    Log.d("angka2","" + angka2);
                }
            }

        });


    }
    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "ISeeNero";
            String description = "Sistem Peringatan Banjir";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
    // Get the layouts to use in the custom notification


    public void tampilNotifikasi(String namaSungai, String tingkatBahaya){
        createNotificationChannel();

        Bitmap bitmapIcon = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

        Intent intent = new Intent(this, detailNotif.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)

                .setSmallIcon(R.drawable.logo)
                .setContentTitle(namaSungai)
                .setContentText(tingkatBahaya)
                .setLargeIcon(bitmapIcon)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setLights(Color.RED, 3000, 3000)
                ;

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        String waktu = java.text.DateFormat.getDateTimeInstance().format(new Date());
        notifViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(NotifViewModel.class);
        NotificationISeeNero notif = new NotificationISeeNero(namaSungai,tingkatBahaya,waktu," ","0");
        notifViewModel.insert(notif);
    }
}