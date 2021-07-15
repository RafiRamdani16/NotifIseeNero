package com.example.notification.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notification.NotificationISeeNero;

import java.util.List;

public class NotifViewModel extends AndroidViewModel {

    private NotifRepository notifRepository;

    private final LiveData<List<NotificationISeeNero>> allNotif;

    public NotifViewModel(@NonNull Application application) {
        super(application);
        notifRepository = new NotifRepository(application);
        allNotif =notifRepository.getAllNotif();
    }

    public LiveData<List<NotificationISeeNero>> getAllNotif() { return allNotif; }
    public void insert(NotificationISeeNero notif) { notifRepository.insert(notif); }
//    public void delete(String Dnama){notifRepository.delete(Dnama);}
    public void update(NotificationISeeNero notif){notifRepository.update(notif);}
}
