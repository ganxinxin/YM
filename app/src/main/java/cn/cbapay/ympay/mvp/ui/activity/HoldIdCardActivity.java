package cn.cbapay.ympay.mvp.ui.activity;

import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.mvp.presenter.OnCaptureCallback;
import cn.cbapay.ympay.mvp.ui.base.BaseCameraActivity;
import cn.cbapay.ympay.utils.CaremaUtils;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * create by wangkezheng
 */


public class HoldIdCardActivity extends BaseCameraActivity implements View.OnClickListener, OnCaptureCallback {

    private String TAG = "HoldIdCardActivity";
    /**
     * 手持身份证拍摄
     */
    private ImageButton img_change, flashlight;
    private TextView txt_film, txt;
    private Animation mAnimation;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private ImageView img_Id_card;
    private Button btn_restart, btn_finish;
    RelativeLayout rel_begin;
    LinearLayout lin_last;
    private Camera mCamera;
    private int mIndex = 0;
    private String filePath;
    private int mRet;
    private boolean isPortrait;


    @Override
    protected int getLayoutResID() {
        layout();
        return mRet;
    }

    @Override
    protected void initViews() {
        initToolbar();
        img_change = (ImageButton) findViewById(R.id.img_change);
//        调用相机控件
        surfaceView = (SurfaceView) findViewById(R.id.pictureView);
        holder = surfaceView.getHolder();

        img_Id_card = (ImageView) findViewById(R.id.img_Id_card);
        btn_restart = (Button) findViewById(R.id.btn_restart);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        rel_begin = (RelativeLayout) findViewById(R.id.rel_begin);
        lin_last = (LinearLayout) findViewById(R.id.lin_last);

        flashlight = (ImageButton) findViewById(R.id.flashlight);
        txt_film = (TextView) findViewById(R.id.txt_film);
        txt = (TextView) findViewById(R.id.txt);
        initListener();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        initData();

    }

    private void initData() {
        surface();
        img_change.setOnClickListener(this);
        img_change.setOnClickListener(this);
        btn_restart.setOnClickListener(this);
        btn_finish.setOnClickListener(this);
        flashlight.setOnClickListener(this);
        txt_film.setEnabled(true);
        txt_film.setOnClickListener(this);
        txt_film.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //按下操作 动画
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mAnimation = AnimationUtils.loadAnimation(HoldIdCardActivity.this, R.anim.scale);

                    txt_film.startAnimation(mAnimation);
                }
                //抬起操作 动画
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mAnimation = AnimationUtils.loadAnimation(HoldIdCardActivity.this, R.anim.scale0);
                    txt_film.startAnimation(mAnimation);
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_change:
                //    变换摄像头
                mIndex = CaremaUtils.changeCamera(mCamera, mIndex);
                mCamera = Camera.open(mIndex);//打开当前选中的摄像头
                CaremaUtils.openCamera(surfaceView, mCamera, isReserved);
                break;
            case R.id.flashlight:
//              闪光灯开关
                CaremaUtils.changelight(mCamera, surfaceView, flashlight, isReserved);
                break;
            case R.id.txt_film:

//                拍照
                txt_film.setEnabled(false);
                CaremaUtils.takePhotos(this, mCamera, surfaceView, this, false, isReserved, mIndex);

                break;
            case R.id.btn_restart:
//                重拍
                again();
                break;
            case R.id.btn_finish:
                openActivity(SubmitAuditActivity.class);
                finish();
                break;
        }
    }

    public void surface() {


        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                //        调用相机控件
                //        打开相机
                if (mCamera == null) {
                    mCamera = Camera.open(mIndex);
                }
                //打开相机 后置摄像头
                CaremaUtils.openCamera(surfaceView, mCamera, isReserved);


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                CaremaUtils.openCamera(surfaceView, mCamera,isReserved);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //释放相机资源
                if (mCamera != null) {
                    mCamera.setPreviewCallback(null);
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                }

            }
        });
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mCamera.autoFocus(null);
                return true;
            }
        });
    }

    private void again() {
        /**
         * 重新开始拍
         */
        img_change.setVisibility(View.VISIBLE);
        surfaceView.setVisibility(View.VISIBLE);

        rel_begin.setVisibility(View.VISIBLE);
        lin_last.setVisibility(View.GONE);
        txt.setVisibility(View.VISIBLE);
        txt_film.setEnabled(true);
        CaremaUtils.deleteFile(filePath);

        img_Id_card.setImageResource(R.mipmap.handhold);
        CaremaUtils.startPreview(mCamera);
    }

    @Override
    public void onCapture(boolean success, String filePath) {
        this.filePath = filePath;
        ShareUtil.setValue("fileHold", filePath, this);
        String message = "拍照成功";
        if (!success) {
            message = "拍照失败";
            mCamera.startPreview();
            surfaceView.setVisibility(View.VISIBLE);
        } else {
            img_change.setVisibility(View.INVISIBLE);
            surfaceView.setVisibility(View.INVISIBLE);
            rel_begin.setVisibility(View.GONE);
            lin_last.setVisibility(View.VISIBLE);
            txt.setVisibility(View.INVISIBLE);
            img_Id_card.setImageBitmap(BitmapFactory.decodeFile(filePath));
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrientationListener.disable();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        layout();
        setContentView(mRet);
        initViews();
        initData();
    }

    public void layout() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LogUtil.i(TAG, "land"); // 横屏
            mRet = R.layout.activity_land_hold;
            isPortrait = false;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRet = R.layout.activity_hold_id_card;
            LogUtil.i(TAG, "portrait"); // 竖屏
            isPortrait = true;
        }
    }
}
