package cn.cbapay.ympay.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.CardBinResponseBean;
import cn.cbapay.ympay.mvp.presenter.InputCardNumberPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.InputCardNumberPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.Tools;

/**
 * 卡号输入页面
 * Created by icewater on 16/9/26.
 */

public class InputCardNumberActivity extends BaseActivity implements InputCardNumberPresenter.InputCardNumberView {
    private InputCardNumberPresenter mInputCardNumberPresenter;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private EditText editCardNumber;
    private Button buttonNext;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_input_card_number;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        editCardNumber = (EditText) findViewById(R.id.edit_card_number);
        buttonNext = (Button) findViewById(R.id.button_next);
        mInputCardNumberPresenter = new InputCardNumberPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        toolbarTitle.setText("添加银行卡");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInputCardNumberPresenter.verify(editCardNumber.getText().toString().replace(" ", ""));
                buttonNext.setEnabled(false);
            }
        });

        Tools.bankCardNumAddSpace(editCardNumber);
        editCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editCardNumber.getText().length() < 19) {
                    buttonNext.setEnabled(false);
                } else {
                    buttonNext.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInputCardNumberPresenter.onDestroy();
    }

    @Override
    public void onVerifyCardSucc(CardBinResponseBean bean) {
        buttonNext.setEnabled(true);
        if ("01".equals(bean.getCardType())) {
            // 借记卡
            Bundle b = new Bundle();
            b.putSerializable("bean", bean);
            openActivity(AddDebitCardActivity.class, b);
        } else if ("02".equals(bean.getCardType())) {
            // 贷记卡
            Bundle b = new Bundle();
            b.putSerializable("bean", bean);
            openActivity(AddCreditCardActivity.class, b);
        } else if ("03".equals(bean.getCardType())) {
            // 准贷记卡
            Bundle b = new Bundle();
            b.putSerializable("bean", bean);
            openActivity(AddCreditCardActivity.class, b);
        } else if ("04".equals(bean.getCardType())) {
            // 预付卡
            Toast.makeText(getApplicationContext(), "暂不支持预付卡", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "暂不支持该类型的卡", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onVerifyCardFail(String msg) {
        buttonNext.setEnabled(true);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
