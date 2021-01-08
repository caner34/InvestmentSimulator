package com.finance.portfollio.ViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finance.portfollio.R;

import org.jsoup.nodes.Element;

import com.finance.portfollio.utils.GlobalVariables;
import com.squareup.picasso.Picasso;

public class FinancialNewsRecyclerViewAdapter extends RecyclerView.Adapter<FinancialNewsRecyclerViewAdapter.ViewHolder>{
    LayoutInflater layoutInflater;
    Context context;

    public FinancialNewsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.financial_news_card_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(GlobalVariables.FinancialNewsElements.size() == 0){
            holder.title.setText("empty");
        } else {
            Element news = GlobalVariables.FinancialNewsElements.get(position);
            String title = news.select("title").text();
            holder.title.setText(title);
            String imagePath = news.select("image").text();

            // Lazy Load Image With Picasso (also With caching)
            Picasso.get().load(imagePath)
                    .placeholder(R.drawable.please_wait)
                    .error(android.R.drawable.ic_menu_close_clear_cancel)
                    .into(holder.image);
        }
        //card view
        holder.card.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return (GlobalVariables.FinancialNewsElements == null) ? 0 : GlobalVariables.FinancialNewsElements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        LinearLayout card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.news_image_view);
            title = itemView.findViewById(R.id.news_title_text_view);
            card = itemView.findViewById(R.id.card_linear_layout);
        }

    }
}