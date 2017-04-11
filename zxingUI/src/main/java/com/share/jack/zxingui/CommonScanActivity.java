package com.share.jack.zxingui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.zxing.decode.Utils;
import com.android.zxing.manager.ScanListener;
import com.android.zxing.manager.ScanManager;
import com.google.zxing.Result;
import com.jack.mc.cyg.cygtools.activity.CygStartActivity;
import com.jack.mc.cyg.cygtools.util.CygLog;
import com.jack.mc.cyg.cygtools.util.CygToast;
import com.share.appbaseui.base.BaseActivity;
import com.share.appbaseui.titlebar.TitleBarUIComponent;
import com.share.jack.zxingui.utils.Constant;


/**
 *
 */
public class CommonScanActivity extends BaseActivity implements View.OnClickListener
        , ScanListener {

    public static void start(Context context) {
        CygStartActivity.start(context, CommonScanActivity.class);
    }

    SurfaceView scanPreview = null;
    View scanContainer;
    View scanCropView;
    ImageView scanLine;
    ScanManager scanManager;
    TextView iv_light;
    TextView qrcode_g_gallery;
    TextView qrcode_ic_back;
    final int PHOTOREQUESTCODE = 0x001;

    TextView scan_hint;
    Button rescan;

    private int scanMode;//扫描模型（条形，二维码，全部）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_common_scan);
        scanMode = getIntent().getIntExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
        initView();
    }

    private void initView() {

        TitleBarUIComponent mTitleBarComponent = (TitleBarUIComponent)findViewById(R.id.scanning_titlebar);
        mTitleBarComponent.setTitle("二维码扫描");

        scanPreview = (SurfaceView) findViewById(R.id.capture_preview);
        scanContainer = findViewById(R.id.capture_container);
        scanCropView = findViewById(R.id.capture_crop_view);
        scan_hint = (TextView) findViewById(R.id.scan_hint);
        rescan = (Button) findViewById(R.id.service_register_rescan);
        scanLine = (ImageView) findViewById(R.id.capture_scan_line);
        qrcode_g_gallery = (TextView) findViewById(R.id.qrcode_g_gallery);
        qrcode_g_gallery.setOnClickListener(this);
        qrcode_ic_back = (TextView) findViewById(R.id.qrcode_ic_back);
        qrcode_ic_back.setOnClickListener(this);
        iv_light = (TextView) findViewById(R.id.iv_light);
        rescan.setOnClickListener(this);
        iv_light.setOnClickListener(this);
        scan_hint.setText(R.string.scan_allcode_hint);
        //构造出扫描管理器
        scanManager = new ScanManager(this, scanPreview, scanContainer, scanCropView, scanLine, scanMode, this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.qrcode_g_gallery) {
            showPictures(PHOTOREQUESTCODE);
        } else if (id == R.id.iv_light) {
            scanManager.switchLight();
        } else if (id == R.id.qrcode_ic_back) {
            finish();
        } else if (id == R.id.service_register_rescan) {
            startScan();
        }
    }

    @Override
    public void scanResult(Result rawResult, Bundle bundle) {
        //扫描成功后，扫描器不会再连续扫描，如需连续扫描，调用reScan()方法。
        //scanManager.reScan();
        if (!scanManager.isScanning()) { //如果当前不是在扫描状态
            //设置再次扫描按钮出现
            rescan.setVisibility(View.VISIBLE);
        }
        rescan.setVisibility(View.VISIBLE);
        CygLog.debug("result----->" + rawResult.getText());
//        Activity.start(CommonScanActivity.this, rawResult.getText());
//        finish();
    }

    @Override
    public void scanError(Exception e) {
        CygToast.showToast(e.getMessage().toString());
        //相机扫描出错时
        if (e.getMessage() != null && e.getMessage().startsWith("相机")) {
            scanPreview.setVisibility(View.INVISIBLE);
        }
    }

    public void showPictures(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    private void startScan() {
        if (rescan.getVisibility() == View.VISIBLE) {
            rescan.setVisibility(View.INVISIBLE);
            scanManager.reScan();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String photo_path;
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTOREQUESTCODE) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = this.getContentResolver().query(data.getData(), proj, null, null, null);
                if (cursor.moveToFirst()) {
                    int colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    photo_path = cursor.getString(colum_index);
                    if (photo_path == null) {
                        photo_path = Utils.getPath(getApplicationContext(), data.getData());
                    }
                    scanManager.scanningImage(photo_path);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        scanManager.onResume();
        rescan.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        scanManager.onPause();
    }
}
