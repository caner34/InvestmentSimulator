package com.finance.portfollio.ViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finance.portfollio.R;
import com.finance.portfollio.utils.FinancialNewsElement;


public class FinancialNewsRecyclerViewAdapter extends RecyclerView.Adapter<FinancialNewsRecyclerViewAdapter.ViewHolder>{
    public List<FinancialNewsElement> financialNewsElementList;
    LayoutInflater layoutInflater;
    Context context;

    public FinancialNewsRecyclerViewAdapter(Context context, List<FinancialNewsElement> financialNewsElementList) {
        this.context = context;
        this.financialNewsElementList = financialNewsElementList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v =layoutInflater.inflate(R.layout.financial_news_card_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(financialNewsElementList.size() == 0){
            holder.title.setText("empty");
            holder.source.setText("empty");
        }else{
            holder.title.setText(financialNewsElementList.get(position).getTitle());
            holder.source.setText(financialNewsElementList.get(position).getSource());
        }
        //card view
        holder.card.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return (financialNewsElementList == null) ? 0 : financialNewsElementList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView source;
        LinearLayout card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title_text_view);
            source = itemView.findViewById(R.id.news_source_text_view);
            card = itemView.findViewById(R.id.card_linear_layout);
        }

    }
}