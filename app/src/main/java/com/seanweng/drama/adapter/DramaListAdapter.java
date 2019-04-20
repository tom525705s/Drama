package com.seanweng.drama.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seanweng.drama.R;
import com.seanweng.drama.dataBean.Drama;
import com.seanweng.drama.interfaces.DramaInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DramaListAdapter extends RecyclerView.Adapter<DramaListAdapter.ViewHolder> {

    private Context context;
    private List<Drama> dramas = Collections.emptyList();
    private DramaInterface dramaInterface;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DramaListAdapter(Context context, DramaInterface dramaInterface) {
        this.context = context;
        this.dramaInterface = dramaInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drama_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        Drama drama = dramas.get(pos);
        viewHolder.name.setText(drama.getName());
        try {
            Date date = dateFormat.parse(drama.getCreatedAt());
            String dateString = dateFormat.format(date);
            viewHolder.createAt.setText(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewHolder.rating.setRating(drama.getRating());
        Glide.with(context).load(drama.getThumb()).into(viewHolder.thumb);

        viewHolder.item.setOnClickListener(v -> {
            dramaInterface.gotoDramaDetail(drama);
        });
    }

    @Override
    public int getItemCount() {
        return dramas.size();
    }

    public void setDramas(List<Drama> dramas) {
        this.dramas = dramas;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View item;
        private final ImageView thumb;
        private final TextView name;
        private final TextView createAt;
        private final RatingBar rating;

        private ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            thumb = itemView.findViewById(R.id.thumb);
            name = itemView.findViewById(R.id.name);
            createAt = itemView.findViewById(R.id.created_at);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
