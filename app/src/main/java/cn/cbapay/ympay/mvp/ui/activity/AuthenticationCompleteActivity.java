package cn.cbapay.ympay.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.ArpAcProfileBean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.StpcusinfBean;
import cn.cbapay.ympay.bean.StpusrinfBean;
import cn.cbapay.ympay.mvp.model.CustomerInfoModel;
import cn.cbapay.ympay.mvp.model.impl.CustomerInfoModelImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * 初级认证完成
 * Created by icewater on 16/9/27.
 */

public class AuthenticationCompleteActivity extends BaseActivity implements CustomerInfoModel.FindCustomerInfoListener {

    private Toolbar toolbar;
    private TextView toolbarTitle;

    private Button buttonFinish;
    private Button buttonUpgrade;

    private CustomerInfoModel mCustomerInfoModel;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_authentication_complete;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        buttonFinish = (Button) findViewById(R.id.button_finish);
        buttonUpgrade = (Button) findViewById(R.id.button_upgrade);
        mCustomerInfoModel = new CustomerInfoModelImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText("实名认证");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity(AccountInfoActivity.class);
                finish();
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(AccountInfoActivity.class);
                finish();
            }
        });
        buttonUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(IdVerificationActivity.class);
            }
        });
        RequestBean bean = new RequestBean();
        bean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        buttonUpgrade.setEnabled(false);
        mCustomerInfoModel.findCustomerInfo(bean);
    }

    @Override
    public void onBackPressed() {
        openActivity(AccountInfoActivity.class);
        finish();
    }

    @Override
    public void onFindCustomerInfoSuccess(ArpAcProfileBean arpAcProfile, StpusrinfBean stpusrinf, StpcusinfBean stpcusinf, String payPwdStatus) {
        GlobalData.setArpAcProfile(arpAcProfile);
        GlobalData.setStpusrinf(stpusrinf);
        GlobalData.setStpcusinf(stpcusinf);
        GlobalData.setmPayPwdStatus(payPwdStatus);
        buttonUpgrade.setEnabled(true);
    }

    @Override
    public void onFindCustomerInfoFailed(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
