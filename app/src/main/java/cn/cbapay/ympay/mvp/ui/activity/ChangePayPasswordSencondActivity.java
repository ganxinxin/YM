package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.ChangePayPswIdBean;
import cn.cbapay.ympay.mvp.presenter.ChangePaypasswordSecondPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.ChangePaySecondPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.ActivityUtils;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by gaowei on 2016/9/23.
 */
public class ChangePayPasswordSencondActivity extends BaseActivity implements ChangePaypasswordSecondPresenter.ChangePayPasswordSecondView {
    @InjectView(R.id.title)
    TextView tytle;
    @InjectView(R.id.back)
    Toolbar back;
    @InjectView(R.id.et_card_code)
    EditText etCardCode;
    @InjectView(R.id.bt_next)
    Button btNext;
    private ChangePaySecondPresenterImpl mChangePaySecondPresenter;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mChangePaySecondPresenter.showCardLength(s.length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private String randomCode;
    private String tvAccoutName;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_change_pay_sencond;
    }

    @Override
    protected void initViews() {
        mChangePaySecondPresenter = new ChangePaySecondPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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
    public void checkSucess(String randomCode) {
        finish();
        ActivityUtils.getInstance().addActivity(this);
        Intent intent = new Intent(this,ChangePayPasswordThridActivity.class);
        intent.putExtra("randomCode",randomCode);
        startActivity(intent);
    }

    @Override
    public void checkError() {

    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void tokenFail() {

    }

    @Override
    public void toLogin() {
        MyApplication.StartLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        tytle.setText("修改支付密码");
        etCardCode.addTextChangedListener(watcher);
        etCardCode.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});

        Intent intent = getIntent();
        randomCode = intent.getStringExtra("randomCode");
        tvAccoutName = intent.getStringExtra("tvAccoutName");
    }

    @OnClick({R.id.back, R.id.bt_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_next:
                Boolean aBoolean = mChangePaySecondPresenter.chenckPersonCardCode(etCardCode.getText().toString());
                if(aBoolean){
                    ChangePayPswIdBean bean = new ChangePayPswIdBean();
                    bean.setCustLogin(tvAccoutName);
                    bean.setRandomCode(randomCode);
                    bean.setToken(ShareUtil.getValue("token",this));
                    bean.setIdCard(etCardCode.getText().toString());
                    mChangePaySecondPresenter.checkIdNumber(bean);
                }else {
                    Toast.makeText(this,"身份证输入有误，请检查后重新输入",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
