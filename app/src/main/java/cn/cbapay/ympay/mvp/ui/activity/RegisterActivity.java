package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.mvp.presenter.RegisterPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.RegisterPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.ActivityUtils;

/**
 * Created by gaowei on 2016/9/14.
 */
public class RegisterActivity extends BaseActivity implements RegisterPresenter.RegisterView {
    @InjectView(R.id.iv_open)
    ImageView ivOpen;
    @InjectView(R.id.bt_register)
    Button btRegister;


    //密码的隐藏显示状态
    private boolean open;
    @InjectView(R.id.title)
    TextView mTytle;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.cb_agree)
    CheckBox cbAgree;
    private RegisterPresenterImpl mRegisterPresenter;

    private CharSequence mPhone;
    private CharSequence mPassword;

    private PopupWindow pw;

    private TextWatcher watcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPhone = s;
            mRegisterPresenter.numberLength(s.length());
            mRegisterPresenter.showRegisterState();
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
            mPassword = s;
            mRegisterPresenter.passwordLenth(mPassword.length());
            mRegisterPresenter.showRegisterState();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        mRegisterPresenter = new RegisterPresenterImpl(this);


    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        mTytle.setText("注册");
        //设置第一次输入为不可见状态
        etPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        etPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        etPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        /**
         * 给editext设置监听
         */
        etPhone.addTextChangedListener(watcher1);
        etPassword.addTextChangedListener(watcher2);


    }

    @OnClick({R.id.back, R.id.iv_open, R.id.tv_protocol, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.iv_open:
                mRegisterPresenter.showOrHidePassword();
                break;
            case R.id.tv_protocol:

                break;
            case R.id.bt_register:

                Boolean mBoolean = mRegisterPresenter.checkInputBeforeCode(etPhone.getText().toString(), etPassword.getText().toString(), cbAgree.isChecked());
                if (mBoolean) {

                    ForgetPasswordCodeRequsetBean bean = new ForgetPasswordCodeRequsetBean();
                    bean.setCustLogin(etPhone.getText().toString());
                    mRegisterPresenter.getCode(bean);
                }

                break;
        }
    }

    /**
     * 显示密码
     */
    @Override
    public void showPassword() {
        etPassword.setTransformationMethod(HideReturnsTransformationMethod
                .getInstance());
        ivOpen.setImageResource(R.mipmap.close);
    }

    /**
     * 隐藏密码
     */
    @Override
    public void hidePassword() {

        etPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        ivOpen.setImageResource(R.mipmap.open);

    }

    @Override
    public void showRegisterProgress() {

    }

    @Override
    public void hideRegisterProgress() {

    }

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckCodeSuccess() {

    }

    @Override
    public void enabledUI() {

    }

    @Override
    public void disableUI() {

    }

    @Override
    public void noRegist() {
        btRegister.setBackgroundResource(R.mipmap.register);


    }

    @Override
    public void yesRegist() {
        btRegister.setBackgroundResource(R.mipmap.register_available);
        btRegister.setTextColor(Color.WHITE);
        btRegister.setEnabled(true);


    }

    @Override
    public void toActivity() {
        ActivityUtils instance = ActivityUtils.getInstance();
        instance.addActivity(this);

        Intent i = new Intent(this, RegisterSendCodeActivity.class);
        i.putExtra("phoneNumber", etPhone.getText().toString());
        i.putExtra("passWord", etPassword.getText().toString());
        startActivity(i);
    }

    @Override
    public void alreadyRegist() {
        showPasswordDlg();
    }

    public void showPasswordDlg() {
        final AppCompatDialog dialog = new AppCompatDialog(this, R.style.Dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_window, null, false);
        dialog.setContentView(view);
        TextView text = (TextView) view.findViewById(R.id.tv_text);
        Button btCancle = (Button) view.findViewById(R.id.bt_cancle);
        Button btLogin = (Button) view.findViewById(R.id.bt_login);
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterActivity.this.finish();
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


    }
}
