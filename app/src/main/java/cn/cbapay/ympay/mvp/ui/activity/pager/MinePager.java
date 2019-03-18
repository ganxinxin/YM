package cn.cbapay.ympay.mvp.ui.activity.pager;

import android.app.Activity;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.CustomerInfoResponseBean;
import cn.cbapay.ympay.bean.MinePagerRequestBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.StpusrinfBean;
import cn.cbapay.ympay.mvp.model.DataModel;
import cn.cbapay.ympay.mvp.presenter.MinePagerPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.MinePagerPresenterImpl;
import cn.cbapay.ympay.mvp.ui.activity.AccountInfoActivity;
import cn.cbapay.ympay.mvp.ui.activity.AuthenticationInfoActivity;
import cn.cbapay.ympay.mvp.ui.activity.LoginActivity;
import cn.cbapay.ympay.mvp.ui.activity.MainActivity;
import cn.cbapay.ympay.mvp.ui.activity.SettingActivity;
import cn.cbapay.ympay.mvp.ui.base.BasePager;
import cn.cbapay.ympay.utils.ActivityUtils;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MinePager extends BasePager implements View.OnClickListener, MinePagerPresenter.MinePagerView {


    private RelativeLayout mRelativeLayout;

    private MinePagerPresenterImpl minePagerPresenter;
    private ImageView ivOpen;
    private EditText etMoney;
    private TextView tvPhoneNumber;
    private TextView mSetting;
    private String token;
    private LinearLayout ll_login;
    private LinearLayout ll_user;
    private LinearLayout ll_money;
    private CustomerInfoResponseBean mBean;
    private String firstRegist;
    private GlobalData data;
    private TextView tv_certification;
    private ImageView iv_bigUser;
    private ActivityUtils instance;
    private Intent intent;

    public MinePager(Activity activity) {
        super(activity);
    }


    @Override
    public void initData() {
        View view = View.inflate(mActivity, R.layout.pager_mine, null);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_login_register);
        ivOpen = (ImageView) view.findViewById(R.id.iv_open);
        etMoney = (EditText) view.findViewById(R.id.et_money);
        tvPhoneNumber = (TextView) view.findViewById(R.id.tv_phoneNumber);
        mSetting = (TextView) view.findViewById(R.id.setting);
        ll_login = (LinearLayout) view.findViewById(R.id.ll_login);
        ll_money = (LinearLayout) view.findViewById(R.id.ll_money);
        ll_user = (LinearLayout) view.findViewById(R.id.ll_user);
        tv_certification = (TextView) view.findViewById(R.id.tv_certification);
        iv_bigUser = (ImageView) view.findViewById(R.id.iv_bigUser);
        fl_contaner.addView(view);

        mRelativeLayout.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        ivOpen.setOnClickListener(this);
        tvPhoneNumber.setOnClickListener(this);
        tv_certification.setOnClickListener(this);
        iv_bigUser.setOnClickListener(this);

        minePagerPresenter = new MinePagerPresenterImpl(this);
        token = ShareUtil.getValue("token", mActivity);


        if (token.equals("")) {
            //未登录
            ll_login.setVisibility(View.VISIBLE);
            ll_money.setVisibility(View.GONE);
            tvPhoneNumber.setVisibility(View.GONE);
        } else {
            //已登录
            ll_login.setVisibility(View.GONE);
            ll_money.setVisibility(View.VISIBLE);
            //如果数据为空获取数据
            if (data.getStpusrinf() == null) {
                //获取数据
                RequestBean bean = new RequestBean();
                bean.setToken(token);
                minePagerPresenter.getData(bean);

            } else {
                firstRegist = ShareUtil.getValue("firstRegist", mActivity);
                //第一次注册需要获取数据
                if (firstRegist.equals("1")) {
                    //获取数据
                    RequestBean bean = new RequestBean();
                    bean.setToken(token);
                    minePagerPresenter.getData(bean);
                    ShareUtil.setValue("firstRegist", "-1", mActivity);
                } else {
                    String number = phoneNumber(data.getStpusrinf().getUsrMobile());
                    tvPhoneNumber.setText(number);
                    System.out.println(number);
                    String usrStatus = data.getStpusrinf().getUsrStatus();
                    if (!usrStatus.equals("-1")) {
                        //以实名
                        ll_user.setVisibility(View.GONE);
                        //账户余额
                        String cashAcBal = data.getArpAcProfile().getCashAcBal();
                        etMoney.setText(cashAcBal);

                    } else {
                        ll_user.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_login_register:
                instance = ActivityUtils.getInstance();
                instance.addActivity(this.mActivity);
                intent = new Intent(mActivity, LoginActivity.class);
                mActivity.startActivity(intent);
                break;
            case R.id.iv_open:
                minePagerPresenter.showOrHideMoeny();
                break;
            case R.id.tv_phoneNumber:
                Intent intentAccount = new Intent(mActivity, AccountInfoActivity.class);
                mActivity.startActivity(intentAccount);
                break;
            case R.id.setting:
                //把MainActivity加入集合退出登陆时从新请求数据
                /*ActivityUtils.getInstance().addActivity(mActivity);*/
                Intent intentSetting = new Intent(mActivity, SettingActivity.class);
                mActivity.startActivity(intentSetting);
                break;
            case R.id.tv_certification:
                String usrStatus = data.getStpusrinf().getUsrStatus();
                if (usrStatus.equals("-1")) {
                    //-1是‘未实名’跳转到添加银行卡界面
                    Intent intent = new Intent(mActivity, AuthenticationInfoActivity.class);
                    mActivity.startActivity(intent);
                }

                break;

            case R.id.iv_bigUser:
                instance = ActivityUtils.getInstance();
                instance.addActivity(this.mActivity);
                intent = new Intent(mActivity, LoginActivity.class);
                mActivity.startActivity(intent);
                break;
        }
    }

    @Override
    public void showMoney() {
        etMoney.setTransformationMethod(HideReturnsTransformationMethod
                .getInstance());
        ivOpen.setImageResource(R.mipmap.my_close);
    }

    @Override
    public void hideMoney() {
        etMoney.setTransformationMethod(PasswordTransformationMethod
                .getInstance());

        ivOpen.setImageResource(R.mipmap.my_open);
    }

    @Override
    public void getDataSuccess(CustomerInfoResponseBean bean) {
        if (bean != null) {
            mBean = bean;
            //保存到全局
            data.setArpAcProfile(bean.getArpAcProfile());
            data.setStpcusinf(bean.getStpcusinf());
            data.setStpusrinf(bean.getStpusrinf());
            data.setmPayPwdStatus(bean.getPayPwdStatus());

            String number = phoneNumber(bean.getStpusrinf().getUsrMobile());
            tvPhoneNumber.setText(number);
            System.out.println(number);
            String usrStatus = bean.getStpusrinf().getUsrStatus();
            if (usrStatus.equals("-1")) {
                //未实名
                ll_user.setVisibility(View.VISIBLE);
                etMoney.setText("0.00");

            } else {
                //以实名
                ll_user.setVisibility(View.GONE);
                //账户余额
                String cashAcBal = bean.getArpAcProfile().getCashAcBal();
                etMoney.setText(cashAcBal);
                ivOpen.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void getDataFail(String s) {
        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMsg(String s) {
        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toLogin() {
        MyApplication.StartLogin();
    }

    public String phoneNumber(String phoneNumber) {
        String start = phoneNumber.substring(0, 3);
        String end = phoneNumber.substring(7, 11);
        String number = start + "***" + end;
        return number;
    }
}