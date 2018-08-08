package cn.com.quickpark.sspdelicacy.ui;

import android.Manifest;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import cn.com.quickpark.sspdelicacy.R;
import cn.com.quickpark.sspdelicacy.base.BaseActivity;
import cn.com.quickpark.sspdelicacy.databinding.ActivityScannerBinding;
import io.reactivex.functions.Consumer;

public class ScannerActivity extends BaseActivity<ActivityScannerBinding> implements QRCodeView.Delegate {
    private static final String TAG = "ScannerActivity";
    private ZXingView zxingview;

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {

        rxPermissions.requestEach(Manifest.permission.CAMERA, Manifest.permission.VIBRATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        Log.d(TAG, permission.name + " is granted.");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        Log.d(TAG, permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        Log.d(TAG, permission.name + " is denied.");
                    }
                });

        zxingview = bindingView.zxingview;
        zxingview.setDelegate(this);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scanner;
    }

    @Override
    protected void onStart() {
        super.onStart();
        zxingview.startCamera();
        zxingview.startSpot();
    }

    @Override
    protected void onResume() {
        zxingview.showScanRect();
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        zxingview.stopCamera();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zxingview.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        //扫描成功后调用震动器
        vibrator();
        //显示扫描结果
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        //再次延时1.5秒后启动
        zxingview.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "相机打开失败", Toast.LENGTH_SHORT).show();
    }

    private void vibrator() {
        //获取系统震动服务
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        assert vibrator != null;
        vibrator.vibrate(200);
    }
}
