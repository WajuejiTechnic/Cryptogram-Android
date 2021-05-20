package edu.gatech.seclass.crypto6300.Model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class,Cryptogram.class,PlayRef.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract CryptogramDao cryptogramDao();
    public abstract PlayRefDao playrefDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database-cryptogram")
                                // allow queries on the main thread.
                                // Don't do this on a real app! See PersistenceBasicSample for an example.
                                .allowMainThreadQueries()
                                .build();
            }
        }
            return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}