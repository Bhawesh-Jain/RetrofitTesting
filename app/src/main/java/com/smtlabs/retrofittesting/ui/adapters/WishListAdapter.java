package com.smtlabs.retrofittesting.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smtlabs.retrofittesting.R;
import com.smtlabs.retrofittesting.model.WishListResponse;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListViewHolder> {
    List<WishListResponse> data;
    Context context;

    public WishListAdapter(List<WishListResponse> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow_restaurant_list_recycler, parent, false);

        return new WishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {
        String rate = Double.toString(data.get(position).getRating());
        String disc = Double.toString(data.get(position).getDiscount());

        holder.resName.setText(data.get(position).getRestaurantName());
        holder.resDilTime.setText(data.get(position).getDeliveryTime());
        holder.resRating.setText(rate);
        holder.resDisc.setText(disc);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class WishListViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout row;
        TextView resName, resDisc, resDilTime, resRating;


        public WishListViewHolder(@NonNull View itemView) {
            super(itemView);

            row = itemView.findViewById(R.id.singlerow_restaurant_list);
            resName = itemView.findViewById(R.id.textView_restaurant_row_restaurant_name);
            resDisc = itemView.findViewById(R.id.textView_restaurant_row_discout);
            resDilTime = itemView.findViewById(R.id.textView_restaurant_row_delivery_time);
            resRating = itemView.findViewById(R.id.textView_restaurant_row_rating);
        }
    }
}
