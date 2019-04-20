package com.seanweng.drama.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.seanweng.drama.dataBean.Keyword;
import com.seanweng.drama.interfaces.KeywordDao;

/**
 * 搜尋紀錄db
 */

@Database(entities = {Keyword.class}, version = 1)
public abstract class KeywordRoomDatabase extends RoomDatabase {
    public abstract KeywordDao keywordDao();

    private static volatile KeywordRoomDatabase INSTANCE;

    public static KeywordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (KeywordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            KeywordRoomDatabase.class, "keyword_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
