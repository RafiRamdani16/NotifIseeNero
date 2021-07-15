package com.example.notification.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notification.NotificationISeeNero;

import java.util.List;

class NotifRepository {
    private NotificationDAO notifDAO;
    private LiveData<List<NotificationISeeNero>> allNotif;


    NotifRepository(Application application){
        NotifRoomDatabase dbTugas =NotifRoomDatabase.getDatabase(application);
        notifDAO = dbTugas.notificationDAO();
        allNotif =notifDAO.getAlphabetizedTugas();
    }

    LiveData<List<NotificationISeeNero>> getAllNotif() {
        return allNotif;
    }

    void insert(NotificationISeeNero notif) {
        NotifRoomDatabase.databaseWriteExecutor.execute(() -> {
            notifDAO.insert(notif);
        });
    }

//    void delete(String Dnama){
//        NotifRoomDatabase.databaseWriteExecutor.execute(()->{
//            notifDAO.delete(Dnama);
//        });
//    }

    void update(NotificationISeeNero notif){
        NotifRoomDatabase.databaseWriteExecutor.execute(() -> {
            notifDAO.update(notif);
        });
    }

}
