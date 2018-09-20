package com.kh618.entmaa.Adapter;

import android.content.Context;
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

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Holder> {
    private Context context;
    private ArrayList<ListItem> listItems ;

    public HomeAdapter(Context context, ArrayList<ListItem> listItems) {
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
        ListItem list = listItems.get(position);
        try{
            holder.image.setImageResource(list.getImageId());
            holder.title.setText(list.getTitle());
            if(position %2 != 0 && position != 2){
            holder.params.setMarginStart(22);
            holder.relativeLayout.setLayoutParams(holder.params);
            }
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
