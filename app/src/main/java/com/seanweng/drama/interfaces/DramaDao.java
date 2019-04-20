package com.seanweng.drama.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.seanweng.drama.dataBean.Drama;

import java.util.List;

@Dao
public interface DramaDao {
    @Query("SELECT * FROM drama_table ")
    List<Drama> getAllDramas();

    @Query("SELECT * FROM drama_table WHERE name LIKE '%' || :keyword || '%'")
    List<Drama> getDramasByKeyword(String keyword);


    @Query("SELECT * FROM drama_table WHERE dramaId = :dramaId")
    Drama getDramaById(String dramaId);

    @Insert
    void insertList(List<Drama> dramas);

    @Query("DELETE FROM drama_table")
    void deleteAll();
}
