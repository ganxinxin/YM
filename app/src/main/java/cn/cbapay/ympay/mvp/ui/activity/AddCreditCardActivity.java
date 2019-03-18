package cn.cbapay.ympay.mvp.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
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
import cn.cbapay.ympay.utils.Tools;

/**
 * 添加信用卡
 * Created by icewater on 16/9/27.
 */

public class AddCreditCardActivity extends BaseActivity implements AddCardPresenter.AddCardView {
    private AddCardPresenter mAddCardPresenter;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private ImageView imageBankIcon;
    private TextView textBankName;
    private TextView textCardType;
    private EditText editName;
    private EditText editIdentity;
    private TextView textExpire;
    private EditText editCVN2;
    private EditText editPhone;
    private CheckBox checkboxAgree;
    private TextView textAgreement;
    private Button buttonNext;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_add_credit_card;
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
        textExpire = (TextView) findViewById(R.id.text_expire);
        editCVN2 = (EditText) findViewById(R.id.edit_cvn2);
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
        textExpire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddCardPresenter.verifyCreditCard(editName.getText().toString(),
                        editIdentity.getText().toString(),
                        textExpire.getText().toString(),
                        editCVN2.getText().toString(),
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

    private void showDatePickerDialog() {
        String today = Tools.getStringToday("yyyy-MM-dd");
        String[] mArray = today.split("-");

        int year = Integer.parseInt(mArray[0]);
        int monthOfYear = Integer.parseInt(mArray[1]);
        int dayOfMonth = Integer.parseInt(mArray[2]);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                String monthStr;
                if (monthOfYear < 10) {
                    monthStr = "0" + monthOfYear;
                } else {
                    monthStr = "" + monthOfYear;
                }
                String yearStr = "" + year;
                yearStr = yearStr.substring(2);
                textExpire.setText(monthStr + "/" + yearStr);
                textExpire.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        }, year, monthOfYear - 1, dayOfMonth);
        datePickerDialog.show();
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
