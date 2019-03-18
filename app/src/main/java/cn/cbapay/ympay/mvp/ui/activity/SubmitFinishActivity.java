package cn.cbapay.ympay.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.app.AppManager;
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
 * create by wangkezheng
 */

public class SubmitFinishActivity extends BaseActivity implements  CustomerInfoModel.FindCustomerInfoListener {
    String TAG = "SubmitFinishActivity";
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Button btn_return;
    private CustomerInfoModel mCustomerInfoModel = new CustomerInfoModelImpl(this);

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_submit_finish;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        btn_return = (Button) findViewById(R.id.btn_return);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText("身份证验证");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity(IdVerificationActivity.class);
                finish();
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                返回操作
                finish();
                openActivity(IdVerificationActivity.class);
            }
        });
        RequestBean requestBean = new RequestBean();
        requestBean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));

        mCustomerInfoModel.findCustomerInfo(requestBean);


    }

    @Override
    public void onFindCustomerInfoSuccess(ArpAcProfileBean arpAcProfile, StpusrinfBean stpusrinf, StpcusinfBean stpcusinf, String payPwdStatus) {
        GlobalData.setArpAcProfile(arpAcProfile);
        GlobalData.setStpcusinf(stpcusinf);
        GlobalData.setStpusrinf(stpusrinf);
        GlobalData.setmPayPwdStatus(payPwdStatus);
    }

    @Override
    public void onFindCustomerInfoFailed(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
