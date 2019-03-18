package cn.cbapay.ympay.mvp.ui.activity;


import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.AppManager;
import cn.cbapay.ympay.bean.StpcusinfBean;
import cn.cbapay.ympay.bean.StpusrinfBean;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.LogUtil;

/**
 * create by wangkezheng
 */

public class IdVerificationActivity extends BaseActivity {
    private static String TAG = "IdVerificationActivity";


    private Toolbar toolbar;
    private TextView toolbarTitle;
    private TextView txt_name, txt_id, identity_fail, txt_title;
    private Button btn_film;
    private ImageView img_top;
    private StpcusinfBean stpcusinf = GlobalData.getStpcusinf();
    private StpusrinfBean info = GlobalData.getStpusrinf();


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_id_verification;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        img_top = (ImageView) findViewById(R.id.img_top);
        txt_name = (TextView) findViewById(R.id.edt_name);
        txt_id = (TextView) findViewById(R.id.edt_id);
        identity_fail = (TextView) findViewById(R.id.identity_fail);
        txt_title = (TextView) findViewById(R.id.txt_title);
        btn_film = (Button) findViewById(R.id.btn_film);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        toolbarTitle.setText("身份证验证");
        changeUI();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                openActivity(FrontIdCardActivity.class);
            }
        });


    }

    private void changeUI() {
        if (stpcusinf == null || info == null) {
            Toast.makeText(this, "未进行实名认证", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            txt_name.setText(stpcusinf.getCustName());
            txt_id.setText(stpcusinf.getCustCredNo());
            switch (info.getUsrStatus()) {
                case "0"://立即拍摄页面
                    identity_fail.setVisibility(View.GONE);
                    txt_title.setVisibility(View.VISIBLE);
                    txt_title.setText("拍摄身份证，完善身份信息");
                    btn_film.setVisibility(View.VISIBLE);
                    img_top.setImageResource(R.mipmap.camera);
                    break;
                case "1"://认证审核中
                    btn_film.setVisibility(View.GONE);
                    identity_fail.setVisibility(View.GONE);
                    txt_title.setVisibility(View.VISIBLE);
                    txt_title.setText("您的身份证件正在审核中");
                    img_top.setImageResource(R.mipmap.certification);
                    break;
                case "2"://已通过认证'跳转到【已实名且已上传身份证信息】
                    txt_title.setVisibility(View.GONE);
                    btn_film.setVisibility(View.GONE);
                    identity_fail.setVisibility(View.GONE);
                    img_top.setImageResource(R.mipmap.shimingrenz);
                    break;
                case "3"://'未通过认证' 跳转到【身份证件审核不通过】
                    identity_fail.setVisibility(View.VISIBLE);
                    txt_title.setVisibility(View.VISIBLE);
                    txt_title.setText("拍摄身份证，完善身份信息");
                    btn_film.setVisibility(View.VISIBLE);
                    img_top.setImageResource(R.mipmap.camera);
                    break;
            }

        }
    }

}
