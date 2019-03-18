package cn.cbapay.ympay.mvp.presenter.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.BankVerifyRequestBean;
import cn.cbapay.ympay.bean.BindBankCardRequestBean;
import cn.cbapay.ympay.bean.CardBinResponseBean;
import cn.cbapay.ympay.mvp.model.SendBankCodeModel;
import cn.cbapay.ympay.mvp.model.impl.SendBankCodeModelImpl;
import cn.cbapay.ympay.mvp.presenter.AddCardPresenter;
import cn.cbapay.ympay.utils.IDCardUtils;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * 添加银行卡
 * Created by icewater on 2016/11/1.
 */

public class AddCardPresenterImpl implements AddCardPresenter, SendBankCodeModel.SendCodeListener {
    private static final String TAG = "AddCardPresenterImpl";

    private CardBinResponseBean cardBean;
    private BindBankCardRequestBean bindBean = new BindBankCardRequestBean();

    private SendBankCodeModel mSendBankCodeModel;
    private AddCardView mAddCardView;

    public AddCardPresenterImpl(AddCardView v) {
        this.mAddCardView = v;
        this.mSendBankCodeModel = new SendBankCodeModelImpl(this);
    }

    /**
     * 设置卡bin
     *
     * @param bean 卡bin
     */
    @Override
    public void setCardBin(CardBinResponseBean bean) {
        this.cardBean = bean;
    }

    /**
     * 校验信用卡
     *
     * @param name   姓名
     * @param id     身份证
     * @param expire 有效期
     * @param cvn2   安全码
     * @param phone  手机号
     * @param agree  是否同意协议
     */
    @Override
    public void verifyCreditCard(String name, String id, String expire, String cvn2, String phone, boolean agree) {
        if (name.length() == 0) {
            mAddCardView.showMessage("请输入持卡人姓名");
            return;
        }
        if (id.length() != 18){
            mAddCardView.showMessage("身份证号格式不正确，请检查后重新输入");
            return;
        }
        if (!IDCardUtils.IDCardValidate(id)){
            mAddCardView.showMessage("身份证输入有误，请检查后重新输入");
            return;
        }
        if (expire.length()==0){
            mAddCardView.showMessage("请选择有效期");
            return;
        }
        if (cvn2.length() != 3){
            mAddCardView.showMessage("请输入卡背面后3位数字");
            return;
        }
        if (phone.length()!=11){
            mAddCardView.showMessage("请输入正确的手机号");
            return;
        }
        Pattern p = Pattern
                .compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher m = p.matcher(phone);
        if (!m.matches()){
            mAddCardView.showMessage("手机号不存在，请重新输入");
            return;
        }
        if (!agree){
            mAddCardView.showMessage("请阅读并同意用户协议");
            return;
        }
        bindBean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        bindBean.setBankName(cardBean.getBankName());
        bindBean.setCardType(cardBean.getCardType());
        bindBean.setCardName(name);
        bindBean.setIdCardNum(id);
        bindBean.setCardDeadline(expire);
        bindBean.setBankPreMobile(phone);
        bindBean.setBankNo(cardBean.getBankNo());
        bindBean.setBankAcNo(cardBean.getBankAcNo());
        bindBean.setSafetyCode(cvn2);

        BankVerifyRequestBean bean = new BankVerifyRequestBean();
        bean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        bean.setBankPreMobile(phone);
        mSendBankCodeModel.sendCode(bean);
    }

    /**
     * 校验借记卡
     *
     * @param name  姓名
     * @param id    身份证
     * @param phone 手机号
     * @param agree 是否同意协议
     */
    @Override
    public void verifyDebitCard(String name, String id, String phone, boolean agree) {
        if (name.length() == 0) {
            mAddCardView.showMessage("请输入持卡人姓名");
            return;
        }
        if (id.length() != 18){
            mAddCardView.showMessage("身份证号格式不正确，请检查后重新输入");
            return;
        }
        if (!IDCardUtils.IDCardValidate(id)){
            mAddCardView.showMessage("身份证输入有误，请检查后重新输入");
            return;
        }
        if (phone.length()!=11){
            mAddCardView.showMessage("请输入正确的手机号");
            return;
        }
        Pattern p = Pattern
                .compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher m = p.matcher(phone);
        if (!m.matches()){
            mAddCardView.showMessage("手机号不存在，请重新输入");
            return;
        }
        if (!agree){
            mAddCardView.showMessage("请阅读并同意用户协议");
            return;
        }
        bindBean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        bindBean.setBankName(cardBean.getBankName());
        bindBean.setCardType(cardBean.getCardType());
        bindBean.setCardName(name);
        bindBean.setIdCardNum(id);
        bindBean.setBankPreMobile(phone);
        bindBean.setBankNo(cardBean.getBankNo());
        bindBean.setBankAcNo(cardBean.getBankAcNo());

        BankVerifyRequestBean bean = new BankVerifyRequestBean();
        bean.setToken(ShareUtil.getValue("token", MyApplication.getContext()));
        bean.setBankPreMobile(phone);
        mSendBankCodeModel.sendCode(bean);
    }

    @Override
    public void onDestroy() {
        mSendBankCodeModel.onDestroy();
    }

    /**
     * 验证码发送成功
     */
    @Override
    public void onSendCodeSuccess() {
        mAddCardView.startPayPassword(bindBean, true);
    }

    /**
     * 验证码发送失败
     *
     * @param code 失败码
     * @param msg  失败信息
     */
    @Override
    public void onSendCodeFailed(String code, String msg) {
        if ("48".equals(code)) {
            // 未设置支付密码
            mAddCardView.startPayPassword(bindBean, false);
        }else if ("36".equals(code)) {
            // 请输入您上次收到的短信验证码
            mAddCardView.showMessage("请输入您上次收到的短信验证码");
            mAddCardView.startPayPassword(bindBean, true);
        } else {
            mAddCardView.showMessage(msg);
        }
    }
}
