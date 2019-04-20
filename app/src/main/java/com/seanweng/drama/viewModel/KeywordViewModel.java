package com.seanweng.drama.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.seanweng.drama.dataBean.Keyword;
import com.seanweng.drama.repository.KeywordRepository;

public class KeywordViewModel extends AndroidViewModel {
    private KeywordRepository keywordRepository;
    private MutableLiveData<Keyword> allKeyword;

    public KeywordViewModel(@NonNull Application application) {
        super(application);
        keywordRepository = new KeywordRepository(application);
        allKeyword = keywordRepository.getAllKeyword();
    }

    public MutableLiveData<Keyword> getAllKeyword() {
        return allKeyword;
    }

    public void selectSearchKeyword() {
        keywordRepository.selectKeyword();
    }

    public void insertKeyword(String searchKeyWord) {
        Keyword keyword = new Keyword();
        keyword.setKeyword(searchKeyWord);
        keywordRepository.deleteAll();
        keywordRepository.insert(keyword);
    }

}
