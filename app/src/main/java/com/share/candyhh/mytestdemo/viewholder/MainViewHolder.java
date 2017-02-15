package com.share.candyhh.mytestdemo.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.mc.cyg.cygtools.adapter.listview.CygBaseListViewHolder;
import com.jack.mc.cyg.cygtools.glide.GlideUtil;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.bean.MainBean;

/**
 * Created by jack on 17/2/14
 */

public class MainViewHolder extends CygBaseListViewHolder<MainBean> {

    private ImageView ivAvatar;
    private TextView tvTitle;
    private TextView tvContent;

    public MainViewHolder(View itemView) {
        super(itemView);
        ivAvatar = getView(R.id.im_iv_avatar);
        tvTitle = getView(R.id.im_tv_title);
        tvContent = getView(R.id.im_tv_content);
    }

    @Override
    public void setData(MainBean mainBean) {
        GlideUtil.setCircleTransforms(mainBean.getPicSmall(), ivAvatar, R.mipmap.ic_launcher);
        tvTitle.setText(mainBean.getName());
        tvContent.setText(mainBean.getDescription());
    }
}
