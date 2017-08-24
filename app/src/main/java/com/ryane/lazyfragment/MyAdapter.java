package com.ryane.lazyfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Creator: lijianchang
 * Create Time: 2017/8/15.
 * Email: lijianchang@yy.com
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    private List<String> mPhotos;
    private Context mContext;

    public MyAdapter(List<String> mPhotos, Context mContext) {
        this.mPhotos = mPhotos;
        this.mContext = mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(mContext).load(mPhotos.get(position)).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mPhotos == null ? 0 : mPhotos.size();
    }

    public void setPhotos(List<String> mPhotos) {
        this.mPhotos = mPhotos;
    }

    class Holder extends RecyclerView.ViewHolder {
        private ImageView mImg;

        public Holder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.mImg);
        }
    }
}
