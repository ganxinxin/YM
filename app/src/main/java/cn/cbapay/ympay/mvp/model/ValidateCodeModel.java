package cn.cbapay.ympay.mvp.model;

import cn.cbapay.ympay.bean.CodeRequestBean;

public interface ValidateCodeModel {

    void getValidateCode(CodeRequestBean bean);

    void onDestroy();

    interface OnGetCodeListener {
        void onGetCodeError(String msg);

        void onGetCodeNextFailure(String msg);

        void onGetCodeSuccess();
    }

}
