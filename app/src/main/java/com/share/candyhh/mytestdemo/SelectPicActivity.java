package com.share.candyhh.mytestdemo;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.share.appbaseui.base.BaseActivity;
import com.share.appbaseui.image.OnImageSelectListener;
import com.share.appbaseui.image.SelectImageUtil;
import com.share.candyhh.mytestdemo.popmenu.SelectImagePopMenu;
import com.share.candyhh.mytestdemo.util.PermissionChecker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jack on 17/2/16
 */

public class SelectPicActivity extends BaseActivity {

    private static final String TAG = "SelectPicActivity";

    @BindView(R.id.asp_iv_image)
    ImageView aspIvImage;
    private SelectImagePopMenu selectImagePopMenu;

    private SelectImageUtil selectImageUtil;
    private PermissionChecker permissionChecker;
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pic);
        ButterKnife.bind(this);

        selectImagePopMenu = new SelectImagePopMenu(this);
        selectImageUtil = new SelectImageUtil(this);
        permissionChecker = new PermissionChecker(this);

        selectImagePopMenu.setOnPicSelectListener(new SelectImagePopMenu.OnPicSelectListener() {
            @Override
            public void takePhoto() {
                selectImageUtil.takePhoto();
                selectImagePopMenu.dismiss();
            }

            @Override
            public void gallery() {
                selectImageUtil.pickFromGallery();
                selectImagePopMenu.dismiss();
            }
        });

        selectImageUtil.setOnImageSelectListener(new OnImageSelectListener() {
            @Override
            public void onPictureSelected(Uri fileUri, Bitmap bitmap) {
                aspIvImage.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (permissionChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, 0, PERMISSIONS);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectImageUtil.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.asp_btn_select_pic)
    public void onClick() {
        selectImagePopMenu.show();
    }
}