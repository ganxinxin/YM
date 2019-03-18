package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
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
import cn.cbapay.ympay.mvp.model.impl.SharedPreferencesModel;
import cn.cbapay.ympay.mvp.presenter.ChangeLoginPasswordPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.ChangeLoginPasswordPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.ActivityUtils;
import cn.cbapay.ympay.utils.GlobalData;

/**
 * Created by gaowei on 2016/9/22.
 */
public class ChangeLoginPasswordActivity extends BaseActivity implements ChangeLoginPasswordPresenter.ChangeLoginPasswordView{
    @InjectView(R.id.title)
    TextView tytle;
    @InjectView(R.id.back)
    Toolbar back;
    @InjectView(R.id.tv_accout_name)
    TextView tvAccoutName;
    @InjectView(R.id.et_code)
    EditText etCode;
    @InjectView(R.id.bt_code)
    Button btCode;
    @InjectView(R.id.bt_next)
    Button btNext;

    private ChangeLoginPasswordPresenterImpl changeLoginPasswordPresenter;
    private long interval; // 时间间隔;
    private static final int CODE_TIME = 60;
    private DataModel mDataModel = new SharedPreferencesModel();

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            changeLoginPasswordPresenter.showCodeLength(s.length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_change_login_password;
    }

    @Override
    protected void initViews() {
        changeLoginPasswordPresenter = new ChangeLoginPasswordPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        etCode.addTextChangedListener(watcher);
        tytle.setText("修改登录密码");

        changeLoginPasswordPresenter.initCodeButton();
        long codeTime = (long) mDataModel.getValue("mSendCodeTime", 0L);
        interval = new Date().getTime() - codeTime;
        int time = (int) (CODE_TIME - interval / 1000);
        if(time > 0){
            Toast.makeText(this, "请输入您上次收到的短信验证码", Toast.LENGTH_SHORT).show();
        }
        tvAccoutName.setText(GlobalData.getStpusrinf().getCustLogin());
    }

    @OnClick({R.id.back, R.id.bt_code, R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_code:
                ForgetPasswordCodeRequsetBean bean = new ForgetPasswordCodeRequsetBean();
                bean.setCustLogin(tvAccoutName.getText().toString());
                changeLoginPasswordPresenter.getCode(bean);
                break;
            case R.id.bt_next:
                ForgetPasswordNextBean nextBean = new ForgetPasswordNextBean();
                nextBean.setCustLogin(tvAccoutName.getText().toString());
                nextBean.setValidateCode(etCode.getText().toString());
                changeLoginPasswordPresenter.onNext(nextBean);
                break;
        }
    }

    @Override
    public void yesNext() {
        btNext.setBackgroundResource(R.mipmap.register_available);
        btNext.setEnabled(true);
        btNext.setTextColor(Color.WHITE);
    }

    @Override
    public void noNext() {
        btNext.setBackgroundResource(R.mipmap.register);
        btNext.setEnabled(false);
    }

    @Override
    public void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toActivity(ForgetPasswordResultBean bean) {
        ActivityUtils.getInstance().addActivity(this);
        Intent intent = new Intent(this,ChangeLoginPasswordSecondActivity.class);
        intent.putExtra("randomCode",bean.getRandomCode());
        intent.putExtra("phoneNumber",tvAccoutName.getText().toString());
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
        changeLoginPasswordPresenter.onDestory();
    }
}
