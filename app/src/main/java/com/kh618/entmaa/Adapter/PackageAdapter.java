package com.kh618.entmaa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kh618.entmaa.MyClasses.*;
import com.kh618.entmaa.R;

import java.util.ArrayList;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageHolder> {

    Context context;
    ArrayList <PackageItem> list;
    @Override
    public PackageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.package_list,parent,false);
        return new PackageHolder(v);
    }

    public PackageAdapter(Context context, ArrayList<PackageItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(PackageHolder holder, int position) {
        PackageItem item = list.get(position);

        holder.packagePrice.setText(item.getPackagrPrice());
        holder.packageName.setText(item.getPackageName());
        holder.packageImage.setImageResource(item.getImageId());
        holder.background.setImageResource(item.getBackgroundId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PackageHolder extends RecyclerView.ViewHolder {
        ImageView background,packageImage;
        TextView packageName,packagePrice;
        public PackageHolder(View itemView) {
            super(itemView);
            background =itemView.findViewById(R.id.package_background);
            packageImage =itemView.findViewById(R.id.package_image);
            packageName =itemView.findViewById(R.id.packageName);
            packagePrice =itemView.findViewById(R.id.packagePrice);
        }
    }
}
