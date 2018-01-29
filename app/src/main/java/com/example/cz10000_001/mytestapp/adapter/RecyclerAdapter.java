package com.example.cz10000_001.mytestapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cz10000_001.mytestapp.R;

import java.util.List;

/**
 * Created by cz10000_001 on 2018/1/11.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    private Context mContext;
    private List<Integer> mDatas;
    private ViewGroup parent ;



    public RecyclerAdapter(Context context, List<Integer> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.parent = recyclerView ;
        super.onAttachedToRecyclerView(recyclerView);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_gallery,parent,false) ;
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.iv.setImageResource(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // 获取position 位置的resId
    public int getResId(int position){
        return mDatas==null?0:mDatas.get(position) ;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }
}
