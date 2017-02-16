package com.share.candyhh.mytestdemo.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.mc.cyg.cygtools.adapter.listview.CygBaseListViewHolder;
import com.jack.mc.cyg.cygtools.glide.GlideUtil;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.bean.ListViewBean;

/**
 * Created by jack on 17/2/14
 */

public class ListViewHolder extends CygBaseListViewHolder<ListViewBean> {

    private ImageView ivAvatar;
    private TextView tvTitle;
    private TextView tvContent;

    public ListViewHolder(View itemView) {
        super(itemView);
        ivAvatar = getView(R.id.im_iv_avatar);
        tvTitle = getView(R.id.im_tv_title);
        tvContent = getView(R.id.im_tv_content);
    }

    @Override
    public void setData(ListViewBean listViewBean) {
        GlideUtil.setCircleTransforms(listViewBean.getPicSmall(), ivAvatar, R.mipmap.ic_launcher);
        tvTitle.setText(listViewBean.getName());
        tvContent.setText(listViewBean.getDescription());
    }
}
