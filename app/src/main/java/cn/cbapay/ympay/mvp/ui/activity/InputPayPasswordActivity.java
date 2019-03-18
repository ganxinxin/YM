package cn.cbapay.ympay.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.PayPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.PayPasswordVerifyResponseBean;
import cn.cbapay.ympay.mvp.presenter.VerifyPayPasswordPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.VerifyPayPasswordPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * 输入支付密码页面
 * Created by icewater on 16/9/22.
 */

public class InputPayPasswordActivity extends BaseActivity implements VerifyPayPasswordPresenter.VerifyPasswordView {
    private static final String TAG = InputPayPasswordActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private GridPasswordView editPassword;
    private VerifyPayPasswordPresenter verifyPayPasswordPresenter;
    private String randomCode;
    private int tryCount = 0;
    private AlertDialog alertDialog;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_input_pay_password;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        editPassword = (GridPasswordView) findViewById(R.id.edit_password);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText("更换手机号");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        verifyPayPasswordPresenter = new VerifyPayPasswordPresenterImpl(this);
        editPassword.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                PayPasswordVerifyRequestBean bean = new PayPasswordVerifyRequestBean();
                bean.setToken(ShareUtil.getValue("token", InputPayPasswordActivity.this));
                bean.setPayPwd(psw);
                verifyPayPasswordPresenter.verifyPassword(bean);
                editPassword.clearPassword();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // GridPasswordView的onclick事件可以弹出软键盘
                editPassword.callOnClick();
            }
        }, 998);
    }

    @Override
    protected void onResume() {
        super.onResume();
        alertDialog = createDialog();
    }

    @Override
    public void onVerifyPasswordSuccess(PayPasswordVerifyResponseBean bean) {
        randomCode = bean.getRandomCode();
        Intent intent = new Intent();
        intent.putExtra("randomCode", randomCode);
        intent.setClass(this, InputIdentityActivity.class);
        LogUtil.i(MyApplication.RANDOM_CODE, TAG + ": onVerifyPasswordSuccess " + randomCode);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onVerifyPasswordFail(String msg) {
        tryCount++;
        if (tryCount >= 5) {
            alertDialog.show();
            tryCount = 0;
        }
        Tools.showToast(this, msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 && requestCode == 1 && data != null) {
            setResult(1, data);
            finish();
        }
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.pay_pwd_wrong_tip));
        builder.setPositiveButton(getString(R.string.find_pay_pwd), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                openActivity(ChangePayPasswordActivity.class);
                finish();
            }
        });

        builder.setNegativeButton(getString(R.string.button_cancel), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        return builder.create();
    }
}
