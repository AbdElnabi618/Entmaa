package com.kh618.entmaa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kh618.entmaa.MyClasses.ListItem;
import com.kh618.entmaa.R;
import com.kh618.entmaa.RestaurantClasses.Restaurants;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {
    private Context context;
    private ArrayList<ListItem.Item> listItems ;


    public HomeAdapter(Context context, ArrayList<ListItem.Item> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.home_list,parent,false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final ListItem.Item list = listItems.get(position);
        try{
            Picasso.with(context).load(list.getImage()).into(holder.image);
            holder.title.setText(list.getName());
            if(position %2 != 0 && position != 2){
            holder.params.setMarginStart(22);
            holder.relativeLayout.setLayoutParams(holder.params);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,Restaurants.class);
                    i.putExtra("id",list.getId());
                    context.startActivity(i);
                }
            });
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    class Holder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        RelativeLayout.LayoutParams params;
        RelativeLayout relativeLayout;

        public Holder(View itemView) {
            super(itemView);

             image = itemView.findViewById(R.id.recycle_Image);
             title = itemView.findViewById(R.id.recycle_Text);
             relativeLayout = itemView.findViewById(R.id.layout);

            params= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,22,0,0);

        }
    }


}
