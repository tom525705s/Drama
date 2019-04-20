package com.seanweng.drama.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seanweng.drama.R;
import com.seanweng.drama.dataBean.Drama;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DramaDetailActivity extends BaseActivity {

    public static final String DRAMA_INFO = "DramaInfo";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Drama drama;
    private ImageView thumb;
    private TextView name;
    private TextView createAt;
    private TextView totalViews;
    private RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void findView() {
        thumb = findViewById(R.id.thumb);
        name = findViewById(R.id.name);
        createAt = findViewById(R.id.created_at);
        totalViews = findViewById(R.id.total_views);
        rating = findViewById(R.id.rating);
    }

    @Override
    protected void setupViewComponent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            drama = bundle.getParcelable(DRAMA_INFO);
        }
        if (drama == null) {
            finish();
        }
        name.setText(drama.getName());
        try {
            Date date = dateFormat.parse(drama.getCreatedAt());
            String dateString = dateFormat.format(date);
            createAt.setText(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String totalView = Integer.toString(drama.getTotalViews());
        totalViews.setText(totalView);
        rating.setRating(drama.getRating());
        Glide.with(this).load(drama.getThumb()).into(thumb);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_drama_detail;
    }
}
