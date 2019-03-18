package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.mvp.presenter.AccountInfoPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.AccountInfoPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;

/**
 * 账户信息页面
 * Created by icewater on 16/9/19.
 */
public class AccountInfoActivity extends BaseActivity implements View.OnClickListener, AccountInfoPresenter.AccountInfoView {
    private AccountInfoPresenter mAccountInfoPresenter;

    private Toolbar toolbar;
    private TextView toolbarTitle;

    private TextView textAccountName;
    private LinearLayout layStat;
    private TextView textStat;
    private LinearLayout layPhone;
    private TextView textPhone;
    private LinearLayout layEmail;
    private TextView textEmail;
    private LinearLayout layCareer;
    private TextView textCareer;
    private LinearLayout layArea;
    private TextView textArea;
    private String provinces_city;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_account_info;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        textAccountName = (TextView) findViewById(R.id.text_account_name);
        layStat = (LinearLayout) findViewById(R.id.lay_stat);
        textStat = (TextView) findViewById(R.id.text_stat);
        layPhone = (LinearLayout) findViewById(R.id.lay_phone);
        textPhone = (TextView) findViewById(R.id.text_phone);
        layEmail = (LinearLayout) findViewById(R.id.lay_email);
        textEmail = (TextView) findViewById(R.id.text_email);
        layCareer = (LinearLayout) findViewById(R.id.lay_career);
        textCareer = (TextView) findViewById(R.id.text_career);
        layArea = (LinearLayout) findViewById(R.id.lay_area);
        textArea = (TextView) findViewById(R.id.text_area);
        mAccountInfoPresenter = new AccountInfoPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        toolbarTitle.setText("账户信息");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layStat.setOnClickListener(this);
        layPhone.setOnClickListener(this);
        layEmail.setOnClickListener(this);
        layCareer.setOnClickListener(this);
        layArea.setOnClickListener(this);

        Intent intent = getIntent();
        provinces_city = intent.getStringExtra("provinces_city");
        textArea.setText(provinces_city);
        mAccountInfoPresenter.getAccountInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccountInfoPresenter.updateAccountInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAccountInfoPresenter.onDestroy();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.lay_stat:
                mAccountInfoPresenter.clickAuthState();
                break;
            case R.id.lay_phone:
                mAccountInfoPresenter.clickPhone();
                break;
            case R.id.lay_email:
                mAccountInfoPresenter.clickEmail();
                break;
            case R.id.lay_career:
                mAccountInfoPresenter.clickCareer();
                break;
            case R.id.lay_area:
                mAccountInfoPresenter.clickArea();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    textCareer.setText(data.getStringExtra("job"));
                    break;
                case 2:
                    textArea.setText(data.getStringExtra("location"));
                    break;
                case 3:
                    textEmail.setText(data.getStringExtra("email"));
                    break;
                case 4:
                    textPhone.setText(data.getStringExtra("phone"));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置账户名
     *
     * @param name 名称
     */
    @Override
    public void setAccountName(String name) {
        textAccountName.setText(name);
    }

    /**
     * 设置认证状态
     *
     * @param state 状态
     */
    @Override
    public void setAuthState(String state) {
        textStat.setText(state);
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    @Override
    public void setPhone(String phone) {
        textPhone.setText(phone);
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    @Override
    public void setEmail(String email) {
        textEmail.setText(email);
    }

    /**
     * 设置职业
     *
     * @param career 职业
     */
    @Override
    public void setCareer(String career) {
        textCareer.setText(career);
    }

    /**
     * 设置地区
     *
     * @param area 地区
     */
    @Override
    public void setArea(String area) {
        textArea.setText(area);
    }

    /**
     * 启动首次实名认证
     */
    @Override
    public void startAuthentication() {
        openActivity(AuthenticationInfoActivity.class);
    }

    /**
     * 启动完善认证信息
     */
    @Override
    public void startIdVerification() {
        openActivity(IdVerificationActivity.class);
    }

    /**
     * 启动未实名修改手机号流程
     */
    @Override
    public void startInputPassword() {
        Intent loginIntent = new Intent();
        loginIntent.setClass(this, InputPasswordActivity.class);
        startActivityForResult(loginIntent, 4);
    }

    /**
     * 启动已实名修改手机号流程
     */
    @Override
    public void startChangePhone() {
        Intent phoneIntent = new Intent();
        phoneIntent.setClass(AccountInfoActivity.this, ChangePhoneActivity.class);
        startActivityForResult(phoneIntent, 4);
    }

    /**
     * 启动修改邮箱流程
     */
    @Override
    public void startEmail() {
        Intent emailIntent = new Intent();
        emailIntent.setClass(this, EmailActivity.class);
        startActivityForResult(emailIntent, 3);
    }

    /**
     * 启动修改职业流程
     */
    @Override
    public void startCareer() {
        Intent careerIntent = new Intent();
        careerIntent.setClass(this, CareerActivity.class);
        startActivityForResult(careerIntent, 1);
    }

    /**
     * 启动修改地区流程
     */
    @Override
    public void startArea() {
        Intent intent = new Intent();
        intent.setClass(this, ProvinceActivity.class);
        startActivityForResult(intent, 2);
    }

    /**
     * 显示信息
     *
     * @param msg 信息
     */
    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
