package com.share.candyhh.mytestdemo.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.share.appbaseui.PermissionsActivity;
import com.share.appbaseui.base.BaseActivity;
import com.share.appbaseui.entity.CropImageEntity;
import com.share.appbaseui.image.OnImageSelectListener;
import com.share.appbaseui.image.SelectImageUtil;
import com.share.appbaseui.util.PermissionChecker;
import com.share.candyhh.mytestdemo.R;
import com.share.candyhh.mytestdemo.ui.popmenu.SelectImagePopMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */

public class SelectPicActivity extends BaseActivity {

    public static void start(Context context) {
        CygStartActivity.start(context, SelectPicActivity.class);
    }

    @BindView(R.id.asp_iv_image)
    ImageView aspIvImage;
    private SelectImagePopMenu selectImagePopMenu;
    private boolean isRequestPermission = false;

    private SelectImageUtil selectImageUtil;
    private PermissionChecker permissionChecker;
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pic);
        ButterKnife.bind(this);

        selectImagePopMenu = new SelectImagePopMenu(this);
        selectImageUtil = new SelectImageUtil(this);
        selectImageUtil.setCropImageEntity(new CropImageEntity(500, 500, true, false, false, true, true));
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
        if (!isRequestPermission) {
            if (permissionChecker.lacksPermissions(PERMISSIONS)) {
                PermissionsActivity.startActivityForResult(this, 0, PERMISSIONS);
                isRequestPermission = true;
            }
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