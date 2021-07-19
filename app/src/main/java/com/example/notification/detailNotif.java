package com.example.notification;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.notification.room.NotifViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.co.telkom.iot.AntaresHTTPAPI;
import id.co.telkom.iot.AntaresResponse;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class detailNotif extends AppCompatActivity {
    public ImageView back;
    private JSONObject content;
//    private String arus, ph, ketinggian;
    private NotifViewModel notifViewModel;
    String namaSungai, waktu, tingkatBahaya, ketinggian, phAir, kecepatanAir, tandaSeru;
    private TextView tnamaSungai, twaktu, ttingkatBahaya, tketinggian, tphAir, tkecepatanAir;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_notification);

        tnamaSungai = findViewById(R.id.tnamaSungai);
        twaktu = findViewById(R.id.twaktu);
        ttingkatBahaya = findViewById(R.id.tingkatWaspada);
        tketinggian = findViewById(R.id.ketinggian_air);
        tphAir = findViewById(R.id.ph_air);
        tkecepatanAir = findViewById(R.id.kecepatan_air);
        back = findViewById(R.id.back);


        getData();
        setData();
        notifViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(NotifViewModel.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationISeeNero notif = new NotificationISeeNero(namaSungai,tingkatBahaya,waktu,tandaSeru,"1");
                notifViewModel.update(notif);
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
                v.getContext().startActivity(intent);
            }
        });

    }

    public void getData(){
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if(getIntent().hasExtra("namaSungai")
                && getIntent().hasExtra("tingkatBahaya")
                && getIntent().hasExtra("waktu")){

            namaSungai = intent.getStringExtra("namaSungai");
            tingkatBahaya = bundle.getString("tingkatBahaya");
            waktu = bundle.getString("waktu");
            tandaSeru = bundle.getString("tandaSeru");
        }

    }

    public void setData(){

            tnamaSungai.setText(namaSungai);
            twaktu.setText(waktu);
            ttingkatBahaya.setText(tingkatBahaya);
            tketinggian.setText(ketinggian);
            tphAir.setText(phAir);
            tkecepatanAir.setText(kecepatanAir);
        }

}
