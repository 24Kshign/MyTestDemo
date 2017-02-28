package com.share.candyhh.mytestdemo.ui.popmenu;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.jack.mc.cyg.cygtools.inputmethod.CygView;
import com.share.candyhh.mytestdemo.R;
import com.share.jack.cygwidget.popup_window.SlideFromBottomBasePopupMenu;

/**
 * Created by jack on 17/2/16
 */

public class SelectImagePopMenu {

    private SlideFromBottomBasePopupMenu mSlideFromBottomPopupMenu;
    private TextView tvTakePhoto;
    private TextView tvGallery;
    private OnPicSelectListener onPicSelectListener;

    public SelectImagePopMenu(Activity activity) {
        mSlideFromBottomPopupMenu = new SlideFromBottomBasePopupMenu(activity);
        View view = CygView.inflateLayout(activity, R.layout.layout_select_pic);
        tvTakePhoto = (TextView) view.findViewById(R.id.lsp_tv_takephoto);
        tvGallery = (TextView) view.findViewById(R.id.lsp_tv_gallery);
        mSlideFromBottomPopupMenu.setMenuItems(view);
        initListener();
    }

    private void initListener() {
        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onPicSelectListener) {
                    onPicSelectListener.takePhoto();
                }
            }
        });
        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onPicSelectListener) {
                    onPicSelectListener.gallery();
                }
            }
        });
    }

    public void setOnPicSelectListener(OnPicSelectListener onPicSelectListener) {
        this.onPicSelectListener = onPicSelectListener;
    }

    public void show() {
        mSlideFromBottomPopupMenu.showPopupWindow();
    }

    public void dismiss() {
        mSlideFromBottomPopupMenu.dismiss();
    }

    public interface OnPicSelectListener {
        void takePhoto();

        void gallery();
    }
}