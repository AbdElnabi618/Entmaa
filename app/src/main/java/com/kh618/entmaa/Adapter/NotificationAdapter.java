package com.kh618.entmaa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kh618.entmaa.MyClasses.NotificationsItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    Context context;
    ArrayList<NotificationsItem> list;

    public NotificationAdapter(Context context, ArrayList<NotificationsItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notification_list,parent,false);
        return new NotificationHolder(v);
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        NotificationsItem item= list.get(position);
        holder.icon.setImageResource(item.getImageId());
        holder.notificationContent.setText(item.getNotificationContent());
        holder.postedUser.setText(item.getPostedName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NotificationHolder extends RecyclerView.ViewHolder{
        TextView postedUser,notificationContent;
        ImageView icon;
        public NotificationHolder(View itemView) {
            super(itemView);
            notificationContent=itemView.findViewById(R.id.notificationContent);
            postedUser=itemView.findViewById(R.id.userName);
            icon=itemView.findViewById(R.id.notification_icon);
        }
    }
}
