package com.kh618.entmaa.Adabter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kh618.entmaa.R;
import com.kh618.entmaa.RestaurantClasses.RestaurantDeitales;
import com.kh618.entmaa.RestaurantClasses.RestaurantListItem;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.Holder> {
    private Context context;
    private ArrayList<RestaurantListItem> listItems ;

    public RestaurantAdapter(Context context, ArrayList<RestaurantListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.restaurants_list,parent,false);

        return new Holder(v);
    }
    Holder hh;
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        RestaurantListItem list = listItems.get(position);
        try{
            hh= holder;
            holder.image.setImageResource(list.getImageId());
            holder.title.setText(list.getTitle());
            holder.offer.setText(list.getOffer());
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, RestaurantDeitales.class);
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
        TextView title,offer;
        RelativeLayout parent;


        public Holder(View itemView) {
            super(itemView);
             image = itemView.findViewById(R.id.restaurantImage);
             title = itemView.findViewById(R.id.restaurantNameText);
             offer =itemView.findViewById(R.id.restaurantOfferText);
             parent = itemView.findViewById(R.id.parent);
        }
    }

}
