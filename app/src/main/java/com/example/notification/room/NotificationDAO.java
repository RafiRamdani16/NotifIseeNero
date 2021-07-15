package com.example.notification.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notification.NotificationAdapter;
import com.example.notification.NotificationISeeNero;

import java.util.List;
@Dao
public interface NotificationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NotificationISeeNero tugas);

//    @Query("DELETE FROM dataNotif Where namaSungai = :Dnama")
//    void delete(String Dnama);

    @Query("SELECT * FROM dataNotif1 ORDER BY waktu ASC")
    LiveData<List<NotificationISeeNero>> getAlphabetizedTugas();

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(NotificationISeeNero notif);
}
