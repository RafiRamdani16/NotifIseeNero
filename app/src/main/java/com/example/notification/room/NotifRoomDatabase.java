package com.example.notification.room;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notification.NotificationISeeNero;
import com.example.notification.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {NotificationISeeNero.class}, version = 1, exportSchema = false)
public abstract class NotifRoomDatabase extends RoomDatabase {
    private static volatile NotifRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract NotificationDAO notificationDAO();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                NotificationDAO dao = INSTANCE.notificationDAO();
                String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

                NotificationISeeNero tugas1 = new NotificationISeeNero("Sungai Citarum", "Bahaya",currentDateTimeString," ","0");
                dao.insert(tugas1);
//
                NotificationISeeNero tugas = new NotificationISeeNero("Sungai Cikapundung", "Bahaya",currentDateTimeString," ","0");
                dao.insert(tugas);
            });
        }
    };

    static NotifRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotifRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotifRoomDatabase.class, "dataNotif1")
//                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `dataNotif2` (`tingkatBahaya` String, `tandaSeru` String "
                    + ", `waktu` String , PRIMARY KEY(`namaSungai`))");
        }
    };
}
