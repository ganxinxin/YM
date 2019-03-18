package cn.cbapay.ympay.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.ChangePayPswRequestBean;
import cn.cbapay.ympay.mvp.presenter.ChangePayFourPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.ChangePayFourPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.ActivityUtils;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;


/**
 * Created by gaowei on 2016/9/23.
 */
public class ChangePayPasswordFourActivity extends BaseActivity implements ChangePayFourPresenter.ChangePayFourView{
    @InjectView(R.id.title)
    TextView tytle;
    @InjectView(R.id.back)
    Toolbar back;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.gpv_input_pwd)
    GridPasswordView gpvInputPwd;

    private String password;

    private ChangePayFourPresenter changePayFourPresenter;
    private String randomCode;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_change_pay_four;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        changePayFourPresenter = new ChangePayFourPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        tytle.setText("修改支付密码");
        password = getIntent().getStringExtra("psw");
        randomCode = getIntent().getStringExtra("randomCode");
        gpvInputPwd.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                if (password.equals(psw)) {
                    //请求数据
                    ChangePayPswRequestBean bean = new ChangePayPswRequestBean();
                    bean.setCustLogin(GlobalData.getStpusrinf().getCustLogin());
                    bean.setToken(ShareUtil.getValue("token",ChangePayPasswordFourActivity.this));
                    bean.setNewPayPass(password);
                    bean.setRandomCode(randomCode);
                    changePayFourPresenter.changePsw(bean);
                } else {
                    Toast.makeText(ChangePayPasswordFourActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                    ChangePayPasswordFourActivity.this.finish();
                    openActivity(ChangePayPasswordThridActivity.class);

                }
            }
        });
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeSuccess() {
        ChangePayPasswordFourActivity.this.finish();
        ActivityUtils.getInstance().exit();
    }

    @Override
    public void changeError() {

    }
}
