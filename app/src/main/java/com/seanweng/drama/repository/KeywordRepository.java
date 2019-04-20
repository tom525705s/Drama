package com.seanweng.drama.repository;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.seanweng.drama.dataBean.Keyword;
import com.seanweng.drama.database.KeywordRoomDatabase;
import com.seanweng.drama.interfaces.KeywordDao;
import com.seanweng.drama.utils.ThreadManager;

public class KeywordRepository {
    private KeywordDao keywordDao;
    private MutableLiveData<Keyword> allKeyword;


    public KeywordRepository(Application application) {
        KeywordRoomDatabase db = KeywordRoomDatabase.getDatabase(application);
        keywordDao = db.keywordDao();
    }

    public MutableLiveData<Keyword> getAllKeyword() {
        if (allKeyword == null) {
            allKeyword = new MutableLiveData<>();
        }
        return allKeyword;
    }

    public void selectKeyword(){
        ThreadManager.getInstance()
                .postToBackgroungThread(() -> getAllKeyword().postValue(keywordDao.getKeyword()));
    }

    public void deleteAll() {
        ThreadManager.getInstance().postToBackgroungThread(() -> keywordDao.deleteAll());
    }

    public void insert(Keyword keyword) {
        ThreadManager.getInstance().postToBackgroungThread(() -> keywordDao.insert(keyword));
    }

}
