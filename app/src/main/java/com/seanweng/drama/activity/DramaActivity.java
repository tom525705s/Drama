package com.seanweng.drama.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.seanweng.drama.R;
import com.seanweng.drama.adapter.DramaListAdapter;
import com.seanweng.drama.interfaces.DramaInterface;
import com.seanweng.drama.viewModel.DramaViewModel;
import com.seanweng.drama.viewModel.KeywordViewModel;

public class DramaActivity extends BaseActivity {
    private DramaViewModel dramaViewModel;
    private KeywordViewModel keywordViewModel;

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private String searchKeyWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_drama;
    }

    @Override
    protected void findView() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
    }

    @Override
    protected void setupViewComponent() {
        setSupportActionBar(toolbar);

        DramaListAdapter adapter = new DramaListAdapter(this, dramaInterface);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        keywordViewModel = ViewModelProviders.of(this).get(KeywordViewModel.class);
        dramaViewModel = ViewModelProviders.of(this).get(DramaViewModel.class);

        // 刷新戲劇列表
        dramaViewModel.getAllDramas().observe(this, adapter::setDramas);
        // 如果有找到deep link帶入id的戲劇, 跳轉戲劇資訊頁
        dramaViewModel.getSelectedDrama().observe(this, drama -> dramaInterface.gotoDramaDetail(drama));
    }

    private void handleIntent(Intent intent) {
        Uri appLinkData = intent.getData();
        if (appLinkData != null) {
            String id = appLinkData.getQueryParameter("id");
            if (id != null) {
                dramaViewModel.selectDramasById(id);
            }
        }
    }

    public DramaInterface dramaInterface = drama -> {
        if (drama == null)
            return;
        Intent intent = new Intent();
        intent.setClass(DramaActivity.this, DramaDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DramaDetailActivity.DRAMA_INFO, drama);
        intent.putExtras(bundle);
        DramaActivity.this.startActivity(intent);
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView search = (SearchView) menuItem.getActionView();
        search.setQueryHint("Search");

        // 過濾出含有關鍵字的戲劇
        search.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchKeyWord = s;
                dramaViewModel.selectDramasByKeyword(s);
                return false;
            }
        });

        // 顯示上次搜尋後狀態或刷新戲劇列表
        keywordViewModel.getAllKeyword().observe(this, keyword -> {
            if (keyword != null && !keyword.getKeyword().isEmpty()) {
                search.setIconified(false);
                search.setQuery(keyword.getKeyword(), false);
            } else {
                dramaViewModel.updateDramas();
            }
        });
        keywordViewModel.selectSearchKeyword();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 紀錄搜尋紀錄
        keywordViewModel.insertKeyword(searchKeyWord);

    }
}
