package com.share.appbaseui.image;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 *
 */
public interface OnImageSelectListener {
    void onPictureSelected(Uri fileUri, Bitmap bitmap);
}