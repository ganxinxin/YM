package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.PassWordUtils;


/**
 * Created by gaowei on 2016/9/23.
 */
public class ChangePayPasswordThridActivity extends BaseActivity {


    @InjectView(R.id.title)
    TextView tytle;
    @InjectView(R.id.back)
    Toolbar back;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.gpv_input_pwd)
    GridPasswordView gpvInputPwd;
    private PassWordUtils mPassWordUtils;
    private String randomCode;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_change_pay_third;
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        tytle.setText("修改支付密码");
        mPassWordUtils = new PassWordUtils();
        randomCode = getIntent().getStringExtra("randomCode");
        gpvInputPwd.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                if (mPassWordUtils.isOrdered(psw)) {
                    Toast.makeText(ChangePayPasswordThridActivity.this, "支付密码不能使用连续的数字", Toast.LENGTH_SHORT).show();
                } else if (mPassWordUtils.isSameSymbol(psw)) {
                    Toast.makeText(ChangePayPasswordThridActivity.this, "支付密码不能使用相同的数字", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ChangePayPasswordThridActivity.this, ChangePayPasswordFourActivity.class);
                    intent.putExtra("psw", psw);
                    intent.putExtra("randomCode",randomCode);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
