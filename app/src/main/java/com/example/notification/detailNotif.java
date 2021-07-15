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

        AntaresAPIASync antaresAPIASync = new AntaresAPIASync();
        antaresAPIASync.execute();

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

//            tketinggian.setText(ketinggian);
//            tphAir.setText(phAir);
//            tkecepatanAir.setText(kecepatanAir);
        }

        private class AntaresAPIASync extends AsyncTask<Void, Void, Void> {
            private Response response;
            private String strBody;
            private JSONObject content;
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://platform.antares.id:8443/~/antares-cse/antares-id/TestArduino/Arduino/la")
                            .get()
                            .addHeader("X-M2M-ORIGIN","02e878afacff7fd2:9cd80e42eb8bb12a" )
                            .addHeader("Accept","application/json")
                            .addHeader("cache-control","no-cache")
                            .addHeader("Postman-Token","a84486c4-9c68-46da-98e4-513e6bf76081")
                            .build();

                    response = client.newCall(request).execute();
                    strBody = response.body().string();
                    JSONObject body = new JSONObject(strBody);
                    content = new JSONObject(body.getJSONObject("m2m:cin").getString("con").replaceAll("NaN","-1"));

                    kecepatanAir = content.getString("Arus");
                    phAir = content.getString("pH");
                    ketinggian = content.getString("Jarak");

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(kecepatanAir != null && phAir != null && ketinggian != null){
                    tketinggian.setText(ketinggian);
                    tphAir.setText(phAir);
                    tkecepatanAir.setText(kecepatanAir);
                }
            }

        }

}
