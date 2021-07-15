package com.example.notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ImageView;

import androidx.core.app.NotificationManagerCompat;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity(tableName = "dataNotif1")
public class NotificationISeeNero {
    @PrimaryKey
    @NonNull
    private String namaSungai;

    public void setNamaSungai(@NonNull String namaSungai) {
        this.namaSungai = namaSungai;
    }

    public void setTingkatBahaya(String tingkatBahaya) {
        this.tingkatBahaya = tingkatBahaya;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public void setTandaSeru(String tandaSeru) {
        this.tandaSeru = tandaSeru;
    }
    private String status;
    private String tingkatBahaya;
    private String waktu;
    private String tandaSeru;

    public void setStatus(String status) {
        this.status = status;
    }






public NotificationISeeNero(String namaSungai, String tingkatBahaya, String waktu, String tandaSeru, String status){
    this.namaSungai = namaSungai;
    this.tingkatBahaya = tingkatBahaya;
    this.waktu = waktu;
    this.tandaSeru = tandaSeru;
    this.status = status;
}
    public String getNamaSungai() {
        return namaSungai;
    }
    public String getTingkatBahaya() {
        return tingkatBahaya;
    }
    public String getWaktu() {
        return waktu;
    }
    public String getStatus() {
        return status;
    }

    public String getTandaSeru() {
        return tandaSeru;
    }
}
