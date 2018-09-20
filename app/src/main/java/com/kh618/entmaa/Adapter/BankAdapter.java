package com.kh618.entmaa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kh618.entmaa.MyClasses.BankItem;
import com.kh618.entmaa.R;

import java.util.ArrayList;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankHolder> {

    private Context context;
    private ArrayList<BankItem> list;

    public BankAdapter(Context context, ArrayList<BankItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bank_list,parent,false);
        return new BankHolder(v);
    }

    @Override
    public void onBindViewHolder(BankHolder holder, int position) {
        BankItem item =list.get(position);

        holder.bankLogo.setImageResource(item.getBankLoog());
        holder.bankName.setText(item.getBankName());
        holder.bankAccountNumber.setText(item.getbankAccountNumber());
        holder.bankBackground.setImageResource(item.getBankBackground());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BankHolder extends RecyclerView.ViewHolder{
        TextView bankName,bankAccountNumber;
        ImageView bankLogo,bankBackground;
        public BankHolder(View itemView) {
            super(itemView);
           bankName= itemView.findViewById(R.id.bankName);
           bankAccountNumber= itemView.findViewById(R.id.bankAccountNumber);
           bankBackground= itemView.findViewById(R.id.bank_background);
           bankLogo= itemView.findViewById(R.id.bankLogo);

        }
    }
}
