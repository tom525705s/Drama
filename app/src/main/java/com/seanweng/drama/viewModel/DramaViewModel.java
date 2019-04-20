package com.seanweng.drama.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.seanweng.drama.dataBean.Drama;
import com.seanweng.drama.repository.DramaRepository;

import java.util.List;

public class DramaViewModel extends AndroidViewModel {
    private DramaRepository dramaRepository;
    private MutableLiveData<List<Drama>> allDramas;
    private MutableLiveData<Drama> selectedDrama;

    public DramaViewModel(@NonNull Application application) {
        super(application);
        dramaRepository = new DramaRepository(application);
        allDramas = dramaRepository.getAllDramas();
        selectedDrama = dramaRepository.getSelectedDrama();
    }

    public MutableLiveData<List<Drama>> getAllDramas() {
        return allDramas;
    }

    public MutableLiveData<Drama> getSelectedDrama() {
        return selectedDrama;
    }

    public void selectDramasByKeyword(String searchKeyWord) {
        dramaRepository.selectDramasByKeyword(searchKeyWord);
    }

    public void selectDramasById(String dramaId) {
        dramaRepository.selectDramaById(dramaId);
    }

    public void updateDramas() {
        dramaRepository.updateDramas();
    }
}
