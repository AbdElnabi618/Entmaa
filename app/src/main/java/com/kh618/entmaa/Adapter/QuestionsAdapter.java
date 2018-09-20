package com.kh618.entmaa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kh618.entmaa.Activitys.Replies;
import com.kh618.entmaa.MyClasses.QuizeItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuizHolder>{

    Context context;
    ArrayList<QuizeItem> list ;

    public QuestionsAdapter(Context context, ArrayList<QuizeItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.questions_list,parent,false);
        return new QuizHolder(v);
    }

    @Override
    public void onBindViewHolder(final QuizHolder holder, int position) {
        QuizeItem item = list.get(position);
        holder.quiz.setText(item.getQueiz());
        holder.quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,Replies.class);
                i.putExtra("question",holder.quiz.getText());
                context.startActivity(i);
            }
        });
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

    class QuizHolder extends RecyclerView.ViewHolder{
        View line1,line2;
        TextView quiz;
        public QuizHolder(View itemView) {
            super(itemView);
            line1=itemView.findViewById(R.id.Line1);
            line2=itemView.findViewById(R.id.Line2);
            quiz=itemView.findViewById(R.id.quize);
        }
    }
    
}
