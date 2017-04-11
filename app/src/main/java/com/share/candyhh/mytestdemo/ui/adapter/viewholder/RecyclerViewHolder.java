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
    private TextView tvDetails;
    private OnDetailsClickListener onDetailsClickListener;

    public RecyclerViewHolder(View view, OnDetailsClickListener onDetailsClickListener) {
        super(view);
        ivAvatar = findView(R.id.im_iv_avatar);
        tvTitle = findView(R.id.im_tv_title);
        tvContent = findView(R.id.im_tv_content);
        tvDetails = findView(R.id.im_tv_details);
        this.onDetailsClickListener = onDetailsClickListener;
        initListener();
    }

    private void initListener() {
        tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onDetailsClickListener) {
                    onDetailsClickListener.onDetailsClick(v, getLayoutPosition());
                }
            }
        });
    }

    @Override
    protected void onItemDataUpdated(@Nullable ListViewBean listViewBean) {
        GlideUtil.setCircleTransforms(listViewBean.getPicSmall(), ivAvatar, R.mipmap.ic_launcher);
        tvTitle.setText(listViewBean.getName());
        tvContent.setText(listViewBean.getDescription());
    }

    public interface OnDetailsClickListener {
        void onDetailsClick(View v, int position);
    }
}