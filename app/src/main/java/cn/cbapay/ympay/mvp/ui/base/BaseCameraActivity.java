package cn.cbapay.ympay.mvp.ui.base;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.OrientationEventListener;
import android.view.View;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.mvp.ui.activity.IdVerificationActivity;
import cn.cbapay.ympay.utils.LogUtil;

/**
 * Created by Administrator on 2016/9/26.相机的基类
 */
public class BaseCameraActivity extends BaseActivity {
    private String TAG = "BaseCameraActivity";
    private Toolbar mToolbar;


    //    手机旋转角度监听
    public OrientationEventListener mOrientationListener;
    //    手机屏幕是不是反的
    public static Boolean isReserved = false;

    @Override
    protected int getLayoutResID() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


    }

    public void initListener() {
        mOrientationListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                if ((orientation >= 58 && orientation <= 124)) {
                    isReserved = true;
                } else {
                    isReserved = false;
                }
                LogUtil.v(TAG, "Orientation changed to " + orientation);
            }
        };

        if (mOrientationListener.canDetectOrientation()) {
            LogUtil.v(TAG, "Can detect orientation");
            mOrientationListener.enable();
        } else {
            LogUtil.v(TAG, "Cannot detect orientation");
            mOrientationListener.disable();
        }
    }

    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_photo);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                openActivity(IdVerificationActivity.class);
            }
        });
    }

}