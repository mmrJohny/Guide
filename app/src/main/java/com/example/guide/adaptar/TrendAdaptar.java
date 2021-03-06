package com.example.guide.adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guide.activity.Details;
import com.example.guide.R;
import com.example.guide.helper.Constant;
import com.example.guide.model.trending.Result_trend;
import com.example.guide.activity.Tv_Detail;

import java.util.List;

public class TrendAdaptar extends RecyclerView.Adapter<TrendAdaptar.ViewHolder> {

    List<Result_trend> result_trends;
    Context context;

    public TrendAdaptar(List<Result_trend> result_trends, Context context) {
        this.result_trends = result_trends;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (result_trends.get(position).getMediaType().equals("movie")){

            holder.title.setText(result_trends.get(position).getOriginalTitle());
            holder.date.setText(result_trends.get(position).getReleaseDate());
            holder.vote.setText(result_trends.get(position).getVoteCount().toString());
            Glide.with(context).load(Constant.IMAGE_URL+result_trends.get(position).getPosterPath()).into(holder.poster);

        }
        else{
            holder.title.setText(result_trends.get(position).getName());
            holder.date.setText(result_trends.get(position).getFirstAirDate().replace("-","/"));
            holder.vote.setText(result_trends.get(position).getVoteCount().toString());
            Glide.with(context).load(Constant.IMAGE_URL+result_trends.get(position).getPosterPath()).into(holder.poster);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result_trends.get(position).getMediaType().equals("tv")){
                    context.startActivity(new Intent(context, Tv_Detail.class).putExtra("tv_id",result_trends.get(position).getId().toString()));
                }
                else {
                    context.startActivity(new Intent(context, Details.class).putExtra("id",result_trends.get(position).getId().toString()));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return result_trends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,date,vote;
        ImageView poster;
        RelativeLayout cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mTitle);
            date = itemView.findViewById(R.id.r_date);
            vote = itemView.findViewById(R.id.vote_count);
            poster = itemView.findViewById(R.id.poster);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
