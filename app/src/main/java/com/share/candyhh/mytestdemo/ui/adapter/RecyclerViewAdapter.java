package com.share.candyhh.mytestdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jack.mc.cyg.cygtools.adapter.recyclerview.CygBaseRecyclerAdapter;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;
import com.share.candyhh.mytestdemo.ui.adapter.viewholder.RecyclerViewHolder;

/**
 * Created by jack on 17/2/17
 */

public class RecyclerViewAdapter extends CygBaseRecyclerAdapter<ListViewBean, RecyclerViewHolder> {

    private RecyclerViewHolder.OnDetailsClickListener onDetailsClickListener;

    public RecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener) {
        super(context,onItemClickListener);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false), onDetailsClickListener);
    }

    public void setOnDetailsClickListener(RecyclerViewHolder.OnDetailsClickListener onDetailsClickListener) {
        this.onDetailsClickListener = onDetailsClickListener;
    }
}