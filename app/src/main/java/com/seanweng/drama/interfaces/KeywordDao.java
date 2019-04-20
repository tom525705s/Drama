package com.seanweng.drama.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.seanweng.drama.dataBean.Keyword;

@Dao
public interface KeywordDao {
    @Query("SELECT * FROM keyword_table ORDER BY id DESC LIMIT 1")
    Keyword getKeyword();

    @Insert
    void insert(Keyword searchKeyword);

    @Query("DELETE FROM keyword_table")
    void deleteAll();
}
