package cn.cbapay.ympay.mvp.presenter.impl;

import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.CardBinRequestBean;
import cn.cbapay.ympay.bean.CardBinResponseBean;
import cn.cbapay.ympay.mvp.model.FindBankBinModel;
import cn.cbapay.ympay.mvp.model.impl.FindBankBinModelImpl;
import cn.cbapay.ympay.mvp.presenter.InputCardNumberPresenter;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * 输入卡号,验证卡 bin
 * Created by icewater on 2016/10/31.
 */

public class InputCardNumberPresenterImpl implements InputCardNumberPresenter, FindBankBinModel.FindBankBinListener {
    private static final String TAG = "InputCardNumberPresenterImpl";

    private FindBankBinModel mFindBankBinModel;
    private InputCardNumberView mInputCardNumberView;
    private String mCardNumber;

    public InputCardNumberPresenterImpl(InputCardNumberView v) {
        this.mInputCardNumberView = v;
        this.mFindBankBinModel = new FindBankBinModelImpl(this);
    }

    @Override
    public void verify(String cardNumber) {
        mCardNumber = cardNumber;
        CardBinRequestBean bean = new CardBinRequestBean();
        bean.setBankCardNo(cardNumber);
        bean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        mFindBankBinModel.findBankBin(bean);
    }

    @Override
    public void onDestroy() {
        mFindBankBinModel.onDestroy();
    }

    @Override
    public void onFindBankBinSuccess(CardBinResponseBean bean) {
        bean.setBankAcNo(mCardNumber);
        mInputCardNumberView.onVerifyCardSucc(bean);
    }

    @Override
    public void onFindBankBinFailed(String msg) {
        mInputCardNumberView.onVerifyCardFail(msg);
    }
}
