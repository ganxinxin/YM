package cn.cbapay.ympay.mvp.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.SubPhotoBean;
import cn.cbapay.ympay.mvp.presenter.SubMitPhotoPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.SubMitPhotoPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * create by wangkezheng
 */

public class SubmitAuditActivity extends BaseActivity implements SubMitPhotoPresenter.SubView, View.OnClickListener {
    /**
     * 提交审核activity
     *
     */
    String TAG = "SubmitAuditActivity";
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView img_front,img_back,img_hold,img_begin,img_finish;
    private TextView txt_begin,txt_last;
    private Button btn_submit;
    private DatePickerDialog mDialog;
    private Bundle mBundle;
    private String fileFront = null;
    private String fileBack = null;
    private String fileHold = null;
    private SubMitPhotoPresenter mSubMitPhotoPresenter;


    ProgressDialog progressDialog;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_submit_audit;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        img_front = (ImageView) findViewById(R.id.img_front);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_hold = (ImageView) findViewById(R.id.img_hold);
        img_begin = (ImageView) findViewById(R.id.img_begin);
        img_finish = (ImageView) findViewById(R.id.img_finish);


        txt_begin = (TextView) findViewById(R.id.txt_begin);
        txt_last = (TextView) findViewById(R.id.txt_last);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        fileFront = ShareUtil.getValue("fileFront",this);
        fileBack = ShareUtil.getValue("fileBack",this);
        fileHold = ShareUtil.getValue("fileHold",this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mSubMitPhotoPresenter = new SubMitPhotoPresenterImpl(this);
        toolbarTitle.setText("身份证验证");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                openActivity(IdVerificationActivity.class);
            }
        });
        txt_begin.setOnClickListener(this);
        txt_last.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        mBundle = new Bundle();
        img_front.setImageBitmap(BitmapFactory.decodeFile(fileFront));
        img_back.setImageBitmap(BitmapFactory.decodeFile(fileBack));
        img_hold.setImageBitmap(BitmapFactory.decodeFile(fileHold));
        img_front.setOnClickListener(this);
        img_back.setOnClickListener(this);
        img_hold.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txt_begin:
//                开始时间
                dialog(txt_begin);
                break;
            case R.id.txt_last:
//                结束时间
                dialog(txt_last);

                break;
            case R.id.btn_submit:
//                TODO 上传图片到服务器


                subMit();
                break;
            case R.id.img_front:

                imgDialog(fileFront);

                break;
            case R.id.img_back:

                imgDialog(fileBack);

                break;
            case R.id.img_hold:

                imgDialog(fileHold);
                break;

        }
    }

    /**
     * TODO
     * 上传图片
     */
    private void subMit() {
        String start = txt_begin.getText().toString();
        String last = txt_last.getText().toString();
        SubPhotoBean subPhotoBean = new SubPhotoBean(ShareUtil.getValue("token", MyApplication.getContext()),last.replace(".",""),start.replace(".",""),fileFront,fileBack,fileHold);
        mSubMitPhotoPresenter.subMitPhoto(subPhotoBean);
    }


    /**
     * 提交进度对话框
     */

    private void progressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提交");
        progressDialog.setMessage("正在提交");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }


    public void dialog(final TextView v){
//        日历对话框
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthofyear  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        这个报错并不影响运行 不用管。。
        mDialog = new DatePickerDialog(this,0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String text = year+"."+(monthOfYear+1)+"."+dayOfMonth;
                v.setText(text);
                if(!TextUtils.isEmpty(txt_begin.getText().toString())&&!TextUtils.isEmpty(txt_last.getText().toString())){
                    btn_submit.setEnabled(true);
                    img_begin.setImageResource(R.mipmap.date_available);
                    img_finish.setImageResource(R.mipmap.date_available);
                }
            }
        },year,monthofyear,day);

        mDialog.show();
    }
    public void imgDialog(String filePath){
//        点击大图对话框
        LayoutInflater inflater =getLayoutInflater();
        View layout = inflater.inflate(R.layout.big_img_dialog, null);

        final AlertDialog imgDialog = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).create();
        imgDialog.setView(layout);

        ImageView img = (ImageView) layout.findViewById(R.id.big_img);
        img.setImageBitmap(BitmapFactory.decodeFile(filePath));
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgDialog.dismiss();
            }
        });
        imgDialog.show();
    }



    @Override
    public void subMitSucceed(String message) {
        mSubMitPhotoPresenter.Destroy();
        Toast.makeText(SubmitAuditActivity.this, message,Toast.LENGTH_SHORT).show();
        openActivity(SubmitFinishActivity.class);
        finish();

    }

    @Override
    public void subMitFiled(String message) {
        Toast.makeText(SubmitAuditActivity.this, message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }
}
