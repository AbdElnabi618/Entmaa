package com.kh618.entmaa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.kh618.entmaa.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageHolder> {
    
    Context context;
    List<String> list;

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_image,parent,false);
        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        String  link = list.get(position);
        Picasso.with(context).load(link).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ImagesAdapter(Context context, List<String> list) { 
        this.context = context;
        this.list = list;
    }

    class ImageHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ImageHolder(View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.listImage_image);
        }
    }
}
