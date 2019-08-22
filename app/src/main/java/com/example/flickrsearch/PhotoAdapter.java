package com.example.flickrsearch;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    public List<Photo> photos;
    public EventListener listener;
    public CircularProgressDrawable circularProgressDrawable;

    public PhotoAdapter(List<Photo> photos, EventListener listener) {
        this.photos = photos;
        this.listener = listener;
    }

    public interface EventListener {
        void onClickPhoto(Photo photo);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        View view = LayoutInflater.from(context).inflate(R.layout.row_photo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Photo photo = photos.get(position);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickPhoto(photo);
                Log.d("click", String.valueOf(position));
            }
        });
        Glide.with(holder.imageView.getContext())
                .load(Uri.parse(photo.getURL()))
                .centerCrop()
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
