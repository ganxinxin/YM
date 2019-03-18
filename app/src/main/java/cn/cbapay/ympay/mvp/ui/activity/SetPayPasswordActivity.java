package cn.cbapay.ympay.mvp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.BindBankCardRequestBean;
import cn.cbapay.ympay.mvp.presenter.BindCardPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.BindCardPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;

/**
 * 设置支付密码
 * Created by icewater on 16/9/27.
 */

public class SetPayPasswordActivity extends BaseActivity implements BindCardPresenter.BindCardView {
    private BindCardPresenter mBindCardPresenter;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private EditText editCode;
    private Button buttonVerify;
    private Button buttonOk;

    private String password = "";

    private BindBankCardRequestBean bindBean;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_set_pay_password;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        editCode = (EditText) findViewById(R.id.edit_code);
        buttonVerify = (Button) findViewById(R.id.button_verify);
        buttonOk = (Button) findViewById(R.id.button_ok);
        mBindCardPresenter = new BindCardPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText("添加银行卡");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBindCardPresenter.sendVerifyCode();
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBindCardPresenter.bind(editCode.getText().toString());
            }
        });
        Bundle b = getIntent().getExtras();
        if (b != null){
            BindBankCardRequestBean bind = (BindBankCardRequestBean)b.getSerializable("bean");
            if (bind != null){
                bindBean = bind;
                mBindCardPresenter.setCardInfo(bindBean);
            }
            boolean s = b.getBoolean("set");
            if (s){
                // 已设置支付密码
                mBindCardPresenter.startCountdown();
            }else{
                // 未设置支付密码
                showPasswordDlg();
            }
        }
    }

    public void showPasswordDlg() {
        final AppCompatDialog dialog = new AppCompatDialog(this, R.style.Dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pay_password, null, false);
        dialog.setContentView(view);

        final ImageView imageReturn;
        final ImageView imageClose;
        final TextView textTitle;
        final GridPasswordView editPassword;

        imageReturn = (ImageView) view.findViewById(R.id.image_return);
        imageClose = (ImageView) view.findViewById(R.id.image_close);
        textTitle = (TextView) view.findViewById(R.id.text_title);
        editPassword = (GridPasswordView) view.findViewById(R.id.edit_password);

        imageReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageReturn.setVisibility(View.INVISIBLE);
                imageClose.setVisibility(View.VISIBLE);
                textTitle.setText("设置支付密码");
                password = "";
                editPassword.clearPassword();
            }
        });
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
        editPassword.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                if ("".equals(password)) {
                    password = psw;
                    imageReturn.setVisibility(View.VISIBLE);
                    imageClose.setVisibility(View.INVISIBLE);
                    textTitle.setText("请确认支付密码");
                    editPassword.clearPassword();
                } else {
                    if (password.equals(psw)) {
                        mBindCardPresenter.setPayPassword(psw);
                        dialog.dismiss();
                    } else {
                        imageReturn.setVisibility(View.INVISIBLE);
                        imageClose.setVisibility(View.VISIBLE);
                        textTitle.setText("设置支付密码");
                        password = "";
                        editPassword.clearPassword();
                        Toast.makeText(getApplicationContext(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        // 目的是弹出软键盘
        // 对于刚跳到一个新的界面就要弹出软键盘的情况下面代码可能由于界面为加载完全而无法弹出软键盘。
        // 此时应该适当的延迟弹出软键盘如998毫秒（保证界面的数据加载完成）
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // GridPasswordView的onclick事件可以弹出软键盘
                editPassword.callOnClick();
            }
        }, 998);
    }

    @Override
    public void showSendCodeProgress(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideSendCodeProgress() {
        buttonVerify.setText("获取验证码");
        buttonVerify.setEnabled(true);
    }

    @Override
    public void showSendStatus(String text) {
        buttonVerify.setText(text);
        buttonVerify.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBindCardPresenter.onDestroy();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPayPasswordFail() {
        showPasswordDlg();
    }

    @Override
    public void setPayPasswordSucc() {
        Toast.makeText(getApplicationContext(), "支付密码设置成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendVerifyCodeSucc() {
        Toast.makeText(getApplicationContext(), "验证码发送成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendVerifyCodeFail(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindSuccess() {
        Toast.makeText(getApplicationContext(), "绑卡成功", Toast.LENGTH_SHORT).show();
        openActivity(AuthenticationCompleteActivity.class);
    }

    @Override
    public void bindFail(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
