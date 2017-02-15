package com.share.candyhh.mytestdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jack.mc.cyg.cygtools.adapter.listview.CygBaseListAdapter;
import com.jack.mc.cyg.cygtools.adapter.listview.CygBaseListViewHolder;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.bean.MainBean;
import com.share.candyhh.mytestdemo.viewholder.MainViewHolder;

/**
 * Created by jack on 17/2/14
 */

public class MainAdapter extends CygBaseListAdapter<MainBean> {

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    protected CygBaseListViewHolder<MainBean> getHolder(ViewGroup parent) {
        return new MainViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false));
    }
}
