package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.ForgetPasswordResultBean;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.mvp.model.ForgetPasswordModel;
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.presenter.ForgetPasswordPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.ForgetPasswordPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.PhoneUtils;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordPresenter.ForgetPasswordView {
    @InjectView(R.id.title)
    TextView tytle;
    @InjectView(R.id.bt_next)
    Button btNext;
    @InjectView(R.id.et_number)
    EditText etNumber;
    @InjectView(R.id.et_code)
    EditText etCode;
    @InjectView(R.id.back)
    Toolbar back;
    @InjectView(R.id.bt_code)
    Button btCode;

    private long interval; // 时间间隔;
    private static final int CODE_TIME = 60;
    private DataModel mDataModel = new SharedPreferencesModel();
    private TextWatcher watcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            forgetPasswordPresenter.showNumberLength(s.length());
            forgetPasswordPresenter.showButtonState();
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

            forgetPasswordPresenter.showCodeLength(s.length());
            forgetPasswordPresenter.showButtonState();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private ForgetPasswordPresenterImpl forgetPasswordPresenter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initViews() {
        forgetPasswordPresenter = new ForgetPasswordPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        etNumber.addTextChangedListener(watcher1);
        etCode.addTextChangedListener(watcher2);
        etNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        etCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        tytle.setText("忘记密码");
        //获取上次获取验证码的手机号
        String forgetNumber = ShareUtil.getValue("forgetNumber", this);
        etNumber.setText(forgetNumber);

        forgetPasswordPresenter.initCodeButton();
        long codeTime = (long) mDataModel.getValue("mSendCodeTime", 0L);
        interval = new Date().getTime() - codeTime;
        int time = (int) (CODE_TIME - interval / 1000);
        if(time > 0){
            Toast.makeText(this, "请输入您上次收到的短信验证码", Toast.LENGTH_SHORT).show();
        }

    }


    @OnClick({R.id.back, R.id.bt_next, R.id.bt_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.bt_next:
                ForgetPasswordNextBean nextBean = new ForgetPasswordNextBean();
                nextBean.setCustLogin(etNumber.getText().toString());
                nextBean.setValidateCode(etCode.getText().toString());
                forgetPasswordPresenter.checkNumberCode(etNumber.getText().toString(), etCode.getText().toString());
                forgetPasswordPresenter.onNext(nextBean);

                break;
            case R.id.bt_code:
               /* boolean mobileNO = PhoneUtils.isMobileNO(etNumber.getText().toString());
                if(mobileNO){*/
                        ForgetPasswordCodeRequsetBean bean = new ForgetPasswordCodeRequsetBean();
                        bean.setCustLogin(etNumber.getText().toString());
                        forgetPasswordPresenter.getCode(bean);
                         //记录本次获取验证码的手机号
                        ShareUtil.setValue("forgetNumber",etNumber.getText().toString(),ForgetPasswordActivity.this);
              /*  }else {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }*/
                break;
        }
    }

    @Override
    public void noButton() {
        btNext.setBackgroundResource(R.mipmap.register);
    }

    @Override
    public void yesButton() {
        btNext.setBackgroundResource(R.mipmap.register_available);
        btNext.setTextColor(Color.WHITE);
        btNext.setEnabled(true);

    }

    @Override
    public void showErrorMsg(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toActivity(ForgetPasswordResultBean bean) {
        this.finish();
        Intent intent = new Intent(this,ForgetPasswordSencondActivity.class);
        intent.putExtra("randomCode",bean.getRandomCode());
        intent.putExtra("phoneNumber",etNumber.getText().toString());
        startActivity(intent);
    }

    @Override
    public void hideSendCodeProgress() {
        btCode.setText("获取验证码");
        btCode.setEnabled(true);
        btCode.setBackgroundResource(R.mipmap.code_selected);
        btCode.setTextColor(Color.parseColor("#E2483C"));
    }

    @Override
    public void showSendStatus(String msg) {
        btCode.setText(msg);
        btCode.setEnabled(false);
        btCode.setBackgroundResource(R.mipmap.code);
        btCode.setTextColor(Color.WHITE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        forgetPasswordPresenter.onDestory();
    }
}
