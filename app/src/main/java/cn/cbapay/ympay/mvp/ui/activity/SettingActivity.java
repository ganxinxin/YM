package cn.cbapay.ympay.mvp.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.SettingRequestBean;
import cn.cbapay.ympay.bean.SettingResultBean;
import cn.cbapay.ympay.mvp.presenter.SettingPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.SettingPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.ActivityUtils;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by gaowei on 2016/9/22.
 */
public class SettingActivity extends BaseActivity implements SettingPresenter.SettingView {
    @InjectView(R.id.title)
    TextView tytle;
    @InjectView(R.id.back)
    Toolbar back;
    @InjectView(R.id.tl_chang_login_password)
    RelativeLayout tlChangLoginPassword;
    @InjectView(R.id.tv_phone)
    TextView tvPhone;
    @InjectView(R.id.tv_certification)
    TextView tvCertification;
    @InjectView(R.id.tv_pay)
    TextView tvPay;
    @InjectView(R.id.unLogin)
    TextView unLogin;

    private SettingPresenter settingPresenter;
    private SettingResultBean mBean;
    private String token;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        settingPresenter = new SettingPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        tytle.setText("设  置");


        //退出登陆的隐藏与显示
        token = ShareUtil.getValue("token", this);
        if (token.equals("")) {
            unLogin.setVisibility(View.GONE);
        } else {
            unLogin.setVisibility(View.VISIBLE);
            SettingRequestBean bean = new SettingRequestBean();
            bean.setCustLogin(ShareUtil.getValue("phoneNumber", this));
            bean.setToken(ShareUtil.getValue("token", this));
            settingPresenter.getData(bean);
        }
    }


    @OnClick({R.id.back, R.id.tl_chang_login_password, R.id.tv_phone, R.id.rl_change_pay, R.id.unLogin,R.id.rl_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.rl_user:
                if (token.equals("")) {
                    openActivity(LoginActivity.class);
                } else {
                    if (mBean.getUsrStatus().equals("-1")) {
                        //usrStatus=-1是‘未实名’跳转到【立即实名认证】
                        openActivity(AuthenticationInfoActivity.class);
                    }
                    if (mBean.getUsrStatus().equals("2")) {
                        //'2' 是 '已通过认证'跳转到【已实名且已上传身份证信息】
                        openActivity(IdVerificationActivity.class);
                    }
                    if (mBean.getUsrStatus().equals("0")) {
                        //'0' 是'未认证'跳转到【立即拍摄页面】
                        openActivity(IdVerificationActivity.class);
                    }
                    if (mBean.getUsrStatus().equals("1")) {
                        //'1' 是 '认证审核中' 跳转到【身份证认证审核中】
                        openActivity(IdVerificationActivity.class);
                    }
                    if (mBean.getUsrStatus().equals("3")) {
                        // '3' 是 '未通过认证' 跳转到【身份证件审核不通过】
                        openActivity(IdVerificationActivity.class);
                    }
                }

                break;
            case R.id.tl_chang_login_password:
                if (token.equals("")) {
                    openActivity(LoginActivity.class);
                } else {
                    ActivityUtils.getInstance().addActivity(this);
                    openActivity(ChangeLoginPasswordActivity.class);
                }
                break;
            case R.id.tv_phone:
                showCall();
                break;
            case R.id.rl_change_pay:
                if (token.equals("")) {
                    openActivity(LoginActivity.class);
                } else {
                    if (mBean.getPayPwdStatus().equals("1") && mBean.getUsrStatus().equals("-1")) {
                        //已经设置支付密码未实名
                        tvPay.setText("");
                        showPasswordDlg();
                    }
                    if (mBean.getPayPwdStatus().equals("1") && mBean.getUsrStatus().equals("2")) {
                        //已经设置支付密码已实名
                        openActivity(ChangePayPasswordActivity.class);
                    }
                    if (mBean.getPayPwdStatus().equals("-1")) {
                        //未设置支付密码
                        tvPay.setText("未设置");
                        showPasswordDlg();
                    }
                    if (mBean.getPayPwdStatus().equals("1") && mBean.getUsrStatus().equals("0")) {
                        //已经设置支付密码已实名
                        openActivity(ChangePayPasswordActivity.class);
                    }
                    if (mBean.getPayPwdStatus().equals("1") && mBean.getUsrStatus().equals("1")) {
                        //已经设置支付密码已实名
                        openActivity(ChangePayPasswordActivity.class);
                    }
                    if (mBean.getPayPwdStatus().equals("1") && mBean.getUsrStatus().equals("3")) {
                        //已经设置支付密码已实名
                        openActivity(ChangePayPasswordActivity.class);
                    }

                }
                break;
            case R.id.unLogin:
                new AlertDialog.Builder(this).setTitle("确认退出吗？")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                ShareUtil.setValue("token", "", SettingActivity.this);
                                ShareUtil.setValue("phoneNumber", "", SettingActivity.this);
                                SettingActivity.this.finish();
                            }
                        })
                        .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();

                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDataSuccess(SettingResultBean bean) {
        mBean = bean;
        if (bean.getUsrStatus().equals("-1")) {
            //未实名
            tvCertification.setText("未实名");
        }
        if (bean.getUsrStatus().equals("2")) {
            //以实名
            tvCertification.setText("已实名");
        }
        if (bean.getUsrStatus().equals("0")||bean.getUsrStatus().equals("1")||bean.getUsrStatus().equals("3")){
            //以实名
            tvCertification.setText("待完善");
        }
        if (bean.getPayPwdStatus().equals("1")) {
            //已经设置支付密码
            tvPay.setText("");
        }
        if (bean.getPayPwdStatus().equals("-1")) {
            //未设置支付密码
            tvPay.setText("未设置");

        }
    }

    public void showPasswordDlg() {
        final AppCompatDialog dialog = new AppCompatDialog(this, R.style.Dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_window, null, false);
        dialog.setContentView(view);
        TextView text = (TextView) view.findViewById(R.id.tv_text);
        Button btCancle = (Button) view.findViewById(R.id.bt_cancle);
        Button btLogin = (Button) view.findViewById(R.id.bt_login);
        text.setText("您的账户未实名认证，为保障您的账户安全，请先实名认证");
        btLogin.setText("去认证");
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //跳转到添加银行卡界面
                openActivity(AuthenticationInfoActivity.class);

            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }
    public void showCall() {
        final AppCompatDialog dialog = new AppCompatDialog(this, R.style.Dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_phone, null, false);
        dialog.setContentView(view);
        Button btCancle = (Button) view.findViewById(R.id.bt_cancle);
        Button bt_call = (Button) view.findViewById(R.id.bt_call);
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4000191077"));
                if (ActivityCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


    }
}
