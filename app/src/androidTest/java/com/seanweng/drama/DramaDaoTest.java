package com.seanweng.drama;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.seanweng.drama.database.DramaRoomDatabase;
import com.seanweng.drama.interfaces.DramaDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class DramaDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DramaDao dao;
    private DramaRoomDatabase db;
    private Gson gson = new Gson();

    @Before
    public void createDB() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, DramaRoomDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.dramaDao();
    }

    @After
    public void closeDb() {
        db.close();
    }


    @Test
    public void getAll() throws Exception {
//        Context context = InstrumentationRegistry.getTargetContext();
//        Dramas dramas = gson.fromJson(context.getString(R.string.example_data), Dramas.class);
//        dao.insertList(dramas.getData());
//        List<Drama> dramaList = LiveDataTestUtil.getValue(dao.getAllDramas());
//        Drama drama = dramaList.stream().filter(tempDrama ->
//                tempDrama.getDramaId() == 3)
//                .findFirst()
//                .orElse(null);

//        Log.d("!!!",drama.toString());
    }
}
