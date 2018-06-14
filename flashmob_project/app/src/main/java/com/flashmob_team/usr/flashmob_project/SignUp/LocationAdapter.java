package com.flashmob_team.usr.flashmob_project.SignUp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<SignViewHolder> {
    private Context context;
    private ArrayList<LocationData> locationData;
    private View.OnClickListener onClickListener;

    private List<Integer> selectLocation;

    boolean isSelect;


    public LocationAdapter(final Context context, ArrayList<LocationData> locationData, View.OnClickListener onClickListener) {
        this.context = context;
        this.locationData = locationData;
        this.onClickListener = onClickListener;

        SignLocationActivity.sign_loca_right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SignCategoryActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public void setAdapter(ArrayList<LocationData> locationData) {
        this.locationData = locationData;
        notifyDataSetChanged();
    }

    @Override
    public SignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_loca_recyclerview_content, parent, false);
        SignViewHolder viewHolder = new SignViewHolder(view);

        view.setOnClickListener(onClickListener);
        selectLocation = new ArrayList<>();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SignViewHolder holder, final int position) {
        holder.VH_loca_name.setText(locationData.get(position).loca_name);

        final int blackFilter = context.getResources().getColor(R.color.black_color_filter);
        PorterDuffColorFilter blakcColorFilter = new PorterDuffColorFilter(blackFilter, PorterDuff.Mode.SRC_ATOP);

        if (locationData.get(position).loca_image == null) {
            Log.d("Log", "Location Adapter loca_image x : " + locationData.get(position).loca_image);
            Glide.with(context)
                    .load("https://s3.ap-northeast-2.amazonaws.com/flashmob.bucket/gangnam2.jpg")
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.VH_loca_image);

            holder.VH_loca_image.setColorFilter(blakcColorFilter);
        } else {
            Log.d("Log", "Location Adapter loca_image o : " + locationData.get(position).loca_image);
            Log.d("Log", locationData.get(position).loca_image);
            Glide.with(context)
                    .load(locationData.get(position).loca_image)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.VH_loca_image);
            holder.VH_loca_image.setColorFilter(blakcColorFilter);
        }

        holder.VH_loca_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Log", "loca id : " + String.valueOf(locationData.get(position).loca_id));

                int yellowFilter = context.getResources().getColor(R.color.yellow_color_filter);
                PorterDuffColorFilter yellowColorFilter = new PorterDuffColorFilter(yellowFilter, PorterDuff.Mode.SRC_ATOP);

                final int blackFilter = context.getResources().getColor(R.color.black_color_filter);
                PorterDuffColorFilter blakcColorFilter = new PorterDuffColorFilter(blackFilter, PorterDuff.Mode.SRC_ATOP);

//                for (int i = 0; i < selectLocation.size(); i++) {
//                    Log.d("Log", "for");
//                    if (selectLocation.get(i) == locationData.get(position).loca_id) {
//                        alreadySelect = 1;
//                        selectId = i;
//                    }
//                    else {
//                        alreadySelect = 0;
//                    }
//                }
//
//                if (alreadySelect == 1) {
//                    Log.d("Log", "remove");
//                    selectLocation.remove(selectId);
//                    holder.VH_loca_image.setColorFilter(blakcColorFilter);
//                    Log.d("Log", "size : " + selectLocation.size());
//                }
//                else {
//                    Log.d("Log", "add");
//                    selectLocation.add(locationData.get(position).loca_id);
//                    holder.VH_loca_image.setColorFilter(yellowColorFilter);
//                    Log.d("Log", "size : " + selectLocation.size());
//                }


            }
        });

    }

    public boolean isSelect(int id) {
        for (int i = 0; i < selectLocation.size(); i++) {
            if (selectLocation.get(i) == id){
                break;
            }
        }
        return true;
    }


    @Override
    public int getItemCount() {
        return locationData != null ? locationData.size() : 0;
    }

}
