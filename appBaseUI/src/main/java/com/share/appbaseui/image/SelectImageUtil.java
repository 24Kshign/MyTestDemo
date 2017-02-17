package com.share.appbaseui.image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.jack.mc.cyg.cygtools.util.CygEnvironment;
import com.jack.mc.cyg.cygtools.util.CygLog;
import com.jack.mc.cyg.cygtools.util.CygToast;
import com.kevin.crop.UCrop;
import com.share.appbaseui.CropActivity;
import com.share.appbaseui.entity.CropImageEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 */

public class SelectImageUtil {

    private static final int GALLERY_REQUEST_CODE = 0;    // 相册选图标记
    private static final int CAMERA_REQUEST_CODE = 1;    // 相机拍照标记
    private String mTempPhotoPath;   // 拍照临时图片
    private Uri mDestinationUri;    // 剪切后图像文件
    private Activity mActivity;
    private OnImageSelectListener onImageSelectListener;

    private CropImageEntity cropImageEntity;

    public SelectImageUtil(Activity activity) {
        mActivity = activity;
        mDestinationUri = Uri.fromFile(new File(activity.getCacheDir(), "cropImage.jpeg"));
        mTempPhotoPath = CygEnvironment.getExternalStoragePath() + File.separator + "photo.jpeg";
    }

    public SelectImageUtil(Fragment fragment) {
        this(fragment.getActivity());
    }

    public void takePhoto() {
        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //下面这句指定调用相机拍照后的照片存储的路径
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTempPhotoPath)));
        mActivity.startActivityForResult(takeIntent, CAMERA_REQUEST_CODE);
    }

    public void pickFromGallery() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        mActivity.startActivityForResult(pickIntent, GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == mActivity.RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:   // 调用相机拍照
                    File temp = new File(mTempPhotoPath);
                    startCropActivity(Uri.fromFile(temp));
                    break;
                case GALLERY_REQUEST_CODE:  // 直接从相册获取
                    startCropActivity(data.getData());
                    break;
                case UCrop.REQUEST_CROP:    // 裁剪图片结果
                    handleCropResult(data);
                    break;
                case UCrop.RESULT_ERROR:    // 裁剪图片错误
                    handleCropError(data);
                    break;
                default:
                    break;
            }
        }
    }

    public void setCropImageEntity(CropImageEntity cropImageEntity) {
        this.cropImageEntity = cropImageEntity;
    }

    public void setOnImageSelectListener(OnImageSelectListener onImageSelectListener) {
        this.onImageSelectListener = onImageSelectListener;
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startCropActivity(Uri uri) {
        UCrop.of(uri, mDestinationUri)
                .withAspectRatio(cropImageEntity.getWidth(), cropImageEntity.getHeight())
                .withMaxResultSize(cropImageEntity.getWidth(), cropImageEntity.getHeight())
                .withTargetActivity(CropActivity.class)
                /**
                 * 1、是否允许缩放   2、是否允许旋转   3、周围阴影是否为椭圆(如果false则为矩形)
                 * 4、是否显示裁剪边框   5、是否显示裁剪网格
                 */
                .setCropParameter(cropImageEntity.isScaleEnable(), cropImageEntity.isRotateEnable()
                        , cropImageEntity.isOvalDimmedLayer(), cropImageEntity.isShowCropFrame(), cropImageEntity.isShowCropGrid())
                .start(mActivity);
    }

    /**
     * 处理剪切成功的返回值
     *
     * @param result
     */
    private void handleCropResult(Intent result) {
        deleteTempPhotoFile();
        final Uri resultUri = UCrop.getOutput(result);
        if (null != resultUri && null != onImageSelectListener) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), resultUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            onImageSelectListener.onPictureSelected(resultUri, bitmap);
        } else {
            CygToast.showToast("无法剪切选择图片");
        }
    }

    /**
     * 处理剪切失败的返回值
     *
     * @param result
     */
    private void handleCropError(Intent result) {
        deleteTempPhotoFile();
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            CygLog.error("handleCropError: ", cropError);
            CygToast.showToast(cropError.getMessage());
        } else {
            CygToast.showToast("无法剪切选择图片");
        }
    }

    /**
     * 删除拍照临时文件
     */
    private void deleteTempPhotoFile() {
        File tempFile = new File(mTempPhotoPath);
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }
}