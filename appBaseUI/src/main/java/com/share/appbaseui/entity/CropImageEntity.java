package com.share.appbaseui.entity;

/**
 * Created by jack on 17/2/17
 */

public class CropImageEntity {
    private int width;
    private int height;
    private boolean isScaleEnable;
    private boolean isRotateEnable;
    private boolean isOvalDimmedLayer;
    private boolean isShowCropFrame;
    private boolean isShowCropGrid;

    public CropImageEntity(int width, int height, boolean isScaleEnable, boolean isRotateEnable
            , boolean isOvalDimmedLayer, boolean isShowCropFrame, boolean isShowCropGrid) {
        this.width = width;
        this.height = height;
        this.isScaleEnable = isScaleEnable;
        this.isRotateEnable = isRotateEnable;
        this.isOvalDimmedLayer = isOvalDimmedLayer;
        this.isShowCropFrame = isShowCropFrame;
        this.isShowCropGrid = isShowCropGrid;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isScaleEnable() {
        return isScaleEnable;
    }

    public void setScaleEnable(boolean scaleEnable) {
        isScaleEnable = scaleEnable;
    }

    public boolean isRotateEnable() {
        return isRotateEnable;
    }

    public void setRotateEnable(boolean rotateEnable) {
        isRotateEnable = rotateEnable;
    }

    public boolean isOvalDimmedLayer() {
        return isOvalDimmedLayer;
    }

    public void setOvalDimmedLayer(boolean ovalDimmedLayer) {
        isOvalDimmedLayer = ovalDimmedLayer;
    }

    public boolean isShowCropFrame() {
        return isShowCropFrame;
    }

    public void setShowCropFrame(boolean showCropFrame) {
        isShowCropFrame = showCropFrame;
    }

    public boolean isShowCropGrid() {
        return isShowCropGrid;
    }

    public void setShowCropGrid(boolean showCropGrid) {
        isShowCropGrid = showCropGrid;
    }
}