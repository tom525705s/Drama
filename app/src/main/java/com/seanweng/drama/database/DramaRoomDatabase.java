package com.seanweng.drama.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.seanweng.drama.dataBean.Drama;
import com.seanweng.drama.interfaces.DramaDao;

/**
 * 戲劇列表db
 */

@Database(entities = {Drama.class}, version = 1)
public abstract class DramaRoomDatabase extends RoomDatabase {
    public abstract DramaDao dramaDao();

    private static volatile DramaRoomDatabase INSTANCE;

    public static DramaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DramaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DramaRoomDatabase.class, "drama_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
