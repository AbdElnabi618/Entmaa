package com.kh618.entmaa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kh618.entmaa.MyClasses.QuizeItem;
import com.kh618.entmaa.MyClasses.RepliedItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;

public class RepliesAdapter extends RecyclerView.Adapter<RepliesAdapter.RepliesHolder>{

    Context context;
    ArrayList<RepliedItem.Item> list ;

    public RepliesAdapter(Context context, ArrayList<RepliedItem.Item> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RepliesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.replies_list,parent,false);
        return new RepliesHolder(v);
    }

    @Override
    public void onBindViewHolder(RepliesHolder holder, int position) {
        RepliedItem.Item item = list.get(position);
        holder.replied.setText(item.getAnswer());
        if(position==0){
            holder.line1.setVisibility(View.INVISIBLE);
        }else if(position== list.size() -1){
            holder.line2.setVisibility(View.INVISIBLE);
        }else{
            holder.line1.setVisibility(View.VISIBLE);
            holder.line2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RepliesHolder extends RecyclerView.ViewHolder{
        View line1,line2;
        TextView replied;
        public RepliesHolder(View itemView) {
            super(itemView);
            line1=itemView.findViewById(R.id.Line1);
            line2=itemView.findViewById(R.id.Line2);
            replied=itemView.findViewById(R.id.answer);
        }
    }
    
}
