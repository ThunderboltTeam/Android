package com.flashmob_team.usr.flashmob_project.SignUp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flashmob_team.usr.flashmob_project.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<SignViewHolder> {
    private Context context;
    private ArrayList<CategoryData> categoryData;
    private View.OnClickListener onClickListener;

    public CategoryAdapter(Context context, ArrayList<CategoryData> categoryData, View.OnClickListener onClickListener) {
        this.context = context;
        this.categoryData = categoryData;
        this.onClickListener = onClickListener;
    }

    public void setAdapter(ArrayList<CategoryData> categoryData) {
        this.categoryData = categoryData;
        notifyDataSetChanged();
    }

    @Override
    public SignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_cate_recyclerview_content, parent, false);
        SignViewHolder viewHolder = new SignViewHolder(view);

        view.setOnClickListener(onClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SignViewHolder holder, int position) {
        holder.VH_cate_name.setText(categoryData.get(position).cate_name);
        if (categoryData.get(position).cate_image == null) {
            Log.d("Log", "Category Adapter cate_image x : " + categoryData.get(position).cate_image);
            Glide.with(context)
                    .load(R.drawable.square_button)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.VH_cate_image);
        }
        else {
            Log.d("Log", "Category Adapter cate_image o : " + categoryData.get(position).cate_image);
            Glide.with(context)
                    .load(categoryData.get(position).cate_image)
                    .apply(new RequestOptions()
                    .centerCrop())
                    .into(holder.VH_cate_image);

        }

    }

    @Override
    public int getItemCount() {
        return categoryData != null ? categoryData.size() : 0;
    }
}
