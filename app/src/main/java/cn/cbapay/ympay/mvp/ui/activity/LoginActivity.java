package cn.cbapay.ympay.mvp.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.LoginRequestBean;
import cn.cbapay.ympay.bean.LoginResultBean;
import cn.cbapay.ympay.mvp.model.LoginModel;
import cn.cbapay.ympay.mvp.presenter.LoginPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.LoginPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.ActivityUtils;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.LogUtil;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by gaowei on 2016/9/14.
 */
public class LoginActivity extends BaseActivity implements LoginPresenter.LoginView {


    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.iv_open)
    ImageView ivOpen;
    @InjectView(R.id.tv_phone)
    TextView tvPhone;
    @InjectView(R.id.bt_login)
    Button btLogin;

    private LoginPresenterImpl loginPresenter;
    private TextWatcher watcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            loginPresenter.showUserLength(s);
            loginPresenter.showLoginState();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher watcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            loginPresenter.showPasswordLength(s);
            loginPresenter.showLoginState();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected int getLayoutResID() {

        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        etUsername.addTextChangedListener(watcher1);
        etPassword.addTextChangedListener(watcher2);
        //设置第一次输入为不可见状态
        etPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());


    }

    @OnClick({R.id.back, R.id.bt_login, R.id.tv_forgeter, R.id.tv_register, R.id.tv_phone, R.id.iv_open})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();

                break;
            case R.id.bt_login:
                LoginRequestBean loginRequestBean = new LoginRequestBean();
                loginRequestBean.setCustLogin(etUsername.getText().toString());
                loginRequestBean.setCustPwd(etPassword.getText().toString());
                loginPresenter.login(etUsername.getText().toString(), etPassword.getText().toString(),loginRequestBean);

                break;
            case R.id.tv_forgeter:
                openActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tv_register:
                openActivity(RegisterActivity.class);
                ActivityUtils instance = ActivityUtils.getInstance();
                instance.addActivity(this);
                break;
            case R.id.tv_phone:
                loginPresenter.callPhone();
                break;
            case R.id.iv_open:
                loginPresenter.showOrHidePassword();
                break;

        }
    }

    @Override
    public void showCall() {
        // openActivity(CallPhoneActivity.class);
        showPasswordDlg();
    }

    @Override
    public void yesLogin() {
        btLogin.setEnabled(true);
        btLogin.setBackgroundResource(R.mipmap.register_available);
        btLogin.setTextColor(Color.WHITE);
    }

    @Override
    public void noLogin() {
        btLogin.setBackgroundResource(R.mipmap.register);
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPassword() {

        etPassword.setTransformationMethod(HideReturnsTransformationMethod
                .getInstance());
        ivOpen.setImageResource(R.mipmap.close);
    }

    @Override
    public void hidePassword() {
        etPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());

        ivOpen.setImageResource(R.mipmap.open);
    }

    @Override
    public void toActivity(LoginResultBean bean) {
        this.finish();
        //保存到全局
        GlobalData.setArpAcProfile(bean.getArpAcProfile());
        GlobalData.setStpcusinf(bean.getStpcusinf());
        GlobalData.setStpusrinf(bean.getStpusrinf());
        GlobalData.setmPayPwdStatus(bean.getPayPwdStatus());


        LogUtil.e("login","--------------------------"+etUsername.getText().toString());
        ShareUtil.setValue("token",bean.getToken(),this);
        ShareUtil.setValue("phoneNumber",etUsername.getText().toString(),this);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("token",bean.getToken());
        startActivity(intent);

        ActivityUtils instance = ActivityUtils.getInstance();
        instance.exit();

    }
    public void showPasswordDlg() {
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
                if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
