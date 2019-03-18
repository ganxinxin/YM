package cn.cbapay.ympay.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.BindBankCardRequestBean;
import cn.cbapay.ympay.bean.CardBinResponseBean;
import cn.cbapay.ympay.mvp.presenter.AddCardPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.AddCardPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;

/**
 * 添加借记卡
 * Created by icewater on 16/9/27.
 */

public class AddDebitCardActivity extends BaseActivity implements AddCardPresenter.AddCardView {
    private AddCardPresenter mAddCardPresenter;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private ImageView imageBankIcon;
    private TextView textBankName;
    private TextView textCardType;
    private EditText editName;
    private EditText editIdentity;
    private EditText editPhone;
    private CheckBox checkboxAgree;
    private TextView textAgreement;
    private Button buttonNext;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_add_debit_card;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        imageBankIcon = (ImageView) findViewById(R.id.image_bank_icon);
        textBankName = (TextView) findViewById(R.id.text_bank_name);
        textCardType = (TextView) findViewById(R.id.text_card_type);
        editName = (EditText) findViewById(R.id.edit_name);
        editIdentity = (EditText) findViewById(R.id.edit_identity);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        checkboxAgree = (CheckBox) findViewById(R.id.checkbox_agree);
        textAgreement = (TextView) findViewById(R.id.text_agreement);
        buttonNext = (Button) findViewById(R.id.button_next);
        mAddCardPresenter = new AddCardPresenterImpl(this);
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
                mAddCardPresenter.verifyDebitCard(editName.getText().toString(),
                        editIdentity.getText().toString(),
                        editPhone.getText().toString(),
                        checkboxAgree.isChecked());
            }
        });
        Bundle b = getIntent().getExtras();
        if (b != null){
            CardBinResponseBean bin = (CardBinResponseBean)b.getSerializable("bean");
            if (bin != null){
                textBankName.setText(bin.getBankName());
                textCardType.setText(bin.getBankCardType());
                mAddCardPresenter.setCardBin(bin);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddCardPresenter.onDestroy();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startPayPassword(BindBankCardRequestBean bean, boolean setPayPassword) {
        Bundle b = new Bundle();
        b.putSerializable("bean", bean);
        b.putBoolean("set", setPayPassword);
        openActivity(SetPayPasswordActivity.class, b);
    }
}
