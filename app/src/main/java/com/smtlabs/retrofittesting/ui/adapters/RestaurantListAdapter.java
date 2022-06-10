package com.smtlabs.retrofittesting.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smtlabs.retrofittesting.R;
import com.smtlabs.retrofittesting.model.RestaurantList;
import com.smtlabs.retrofittesting.repositry.RestaurantMethods;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder> {
    List<RestaurantList> data;
    Context context;
    FloatingActionButton wishlist;
    ArrayList<Integer> selected = new ArrayList<>();
    RestaurantMethods methods;

    public RestaurantListAdapter(List<RestaurantList> data, Context context, FloatingActionButton wishlist, RestaurantMethods methods) {
        this.data = data;
        this.context = context;
        this.wishlist = wishlist;
        this.methods = methods;
    }

    @NonNull
    @Override
    public RestaurantListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow_restaurant_list_recycler, parent, false);

        return new RestaurantListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListViewHolder holder, int position) {
        String rate = Double.toString(data.get(position).getRating());
        String disc = Double.toString(data.get(position).getDiscount());

        int id = data.get(position).getRestaurantId();
        holder.resName.setText(data.get(position).getRestaurantName());
        holder.resDilTime.setText(data.get(position).getDeliveryTime());
        holder.resRating.setText(rate);
        holder.resDisc.setText(disc);

        holder.row.setOnClickListener(view -> {
            Toast.makeText(context, "Added To Wishlist!", Toast.LENGTH_SHORT).show();
            selected.add(id);
        });

        wishlist.setOnClickListener(view -> methods.onWishListClicked(selected));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RestaurantListViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout row;
        TextView resName, resDisc, resDilTime, resRating;

        public RestaurantListViewHolder(@NonNull View itemView) {
            super(itemView);

            row = itemView.findViewById(R.id.singlerow_restaurant_list);
            resName = itemView.findViewById(R.id.textView_restaurant_row_restaurant_name);
            resDisc = itemView.findViewById(R.id.textView_restaurant_row_discout);
            resDilTime = itemView.findViewById(R.id.textView_restaurant_row_delivery_time);
            resRating = itemView.findViewById(R.id.textView_restaurant_row_rating);

        }
    }
}
