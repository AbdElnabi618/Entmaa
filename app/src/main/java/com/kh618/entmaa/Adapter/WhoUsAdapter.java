package com.kh618.entmaa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kh618.entmaa.MyClasses.WhoUsItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;

public class WhoUsAdapter extends RecyclerView.Adapter<WhoUsAdapter.WhoUsHolder> {

    Context context;
    ArrayList<WhoUsItem> list;

    public WhoUsAdapter(Context context, ArrayList<WhoUsItem> listItems) {
        this.context=context;
        list = listItems;
    }


    @Override
    public WhoUsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.who_list,parent,false);

        return new WhoUsHolder(v);
    }

    @Override
    public void onBindViewHolder(WhoUsHolder holder, int position) {
        WhoUsItem item = list.get(position);

        holder.termName.setText(String.valueOf(position+1)+" - "+item.getTermName());
        holder.term.setText(item.getTerm());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    class WhoUsHolder extends RecyclerView.ViewHolder{
        TextView termName , term ;
        public WhoUsHolder(View itemView) {
            super(itemView);
            termName =itemView.findViewById(R.id.termName);
            term = itemView.findViewById(R.id.term);
        }
    }

}
