package com.share.candyhh.mytestdemo.ui.adapter.viewholder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.mc.cyg.cygtools.adapter.recyclerview.CygBaseRecyclerViewHolder;
import com.jack.mc.cyg.cygtools.glide.GlideUtil;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.model.entity.ListViewBean;

/**
 * Created by jack on 17/2/17
 */

public class RecyclerViewHolder extends CygBaseRecyclerViewHolder<ListViewBean> {
    private ImageView ivAvatar;
    private TextView tvTitle;
    private TextView tvContent;

    public RecyclerViewHolder(View view) {
        super(view);
        ivAvatar = getView(R.id.im_iv_avatar);
        tvTitle = getView(R.id.im_tv_title);
        tvContent = getView(R.id.im_tv_content);
    }

    @Override
    protected void onItemDataUpdated(@Nullable ListViewBean listViewBean) {
        GlideUtil.setCircleTransforms(listViewBean.getPicSmall(), ivAvatar, R.mipmap.ic_launcher);
        tvTitle.setText(listViewBean.getName());
        tvContent.setText(listViewBean.getDescription());
    }
}