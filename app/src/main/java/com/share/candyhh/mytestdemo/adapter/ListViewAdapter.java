package com.share.candyhh.mytestdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jack.mc.cyg.cygtools.adapter.listview.CygBaseListAdapter;
import com.jack.mc.cyg.cygtools.adapter.listview.CygBaseListViewHolder;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;
import com.share.candyhh.mytestdemo.adapter.viewholder.ListViewHolder;

/**
 * Created by jack on 17/2/14
 */

public class ListViewAdapter extends CygBaseListAdapter<ListViewBean> {

    public ListViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected CygBaseListViewHolder<ListViewBean> getHolder(ViewGroup parent) {
        return new ListViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false));
    }
}
