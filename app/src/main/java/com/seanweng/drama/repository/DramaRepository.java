package com.seanweng.drama.repository;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.seanweng.drama.dataBean.Drama;
import com.seanweng.drama.dataBean.Dramas;
import com.seanweng.drama.database.DramaRoomDatabase;
import com.seanweng.drama.interfaces.DramaAPIInterface;
import com.seanweng.drama.interfaces.DramaDao;
import com.seanweng.drama.network.DramaService;
import com.seanweng.drama.utils.ThreadManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DramaRepository {
    private DramaDao dramaDao;
    private DramaAPIInterface dramaAPIInterface;
    private MutableLiveData<List<Drama>> allDramas;
    private MutableLiveData<Drama> selectedDrama;

    public DramaRepository(Application application) {
        DramaRoomDatabase db = DramaRoomDatabase.getDatabase(application);
        dramaDao = db.dramaDao();

        DramaService dramaService = DramaService.getDramaClient();
        dramaAPIInterface = dramaService.getRetrofit().create(DramaAPIInterface.class);
    }

    public MutableLiveData<List<Drama>> getAllDramas() {
        if (allDramas == null) {
            allDramas = new MutableLiveData<>();
        }
        return allDramas;
    }

    public MutableLiveData<Drama> getSelectedDrama() {
        if (selectedDrama == null) {
            selectedDrama = new MutableLiveData<>();
        }
        return selectedDrama;
    }

    private void selectAll() {
        ThreadManager.getInstance()
                .postToBackgroungThread(() -> getAllDramas().postValue(dramaDao.getAllDramas()));
    }

    public void selectDramasByKeyword(String keyword) {
        ThreadManager.getInstance()
                .postToBackgroungThread(() -> getAllDramas().postValue(dramaDao.getDramasByKeyword(keyword)));
    }

    public void selectDramaById(String dramaId) {
        ThreadManager.getInstance()
                .postToBackgroungThread(() -> getSelectedDrama().postValue(dramaDao.getDramaById(dramaId)));

    }

    public void updateDramas() {
        // 儲存前次抓取結果，離線狀態或呼叫api失敗就抓取前次紀錄
        Call<Dramas> call = dramaAPIInterface.getDramas();
        call.enqueue(new Callback<Dramas>() {
            @Override
            public void onResponse(Call<Dramas> call, Response<Dramas> response) {
                if (response.body() != null) {
                    deleteAll();
                    insertList(response.body().getData());
                    getAllDramas().setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Dramas> call, Throwable t) {
                selectAll();
            }
        });
    }

    private void deleteAll() {
        ThreadManager.getInstance().postToBackgroungThread(() -> dramaDao.deleteAll());
    }

    private void insertList(List<Drama> dramas) {
        ThreadManager.getInstance().postToBackgroungThread(() -> dramaDao.insertList(dramas));
    }


}
