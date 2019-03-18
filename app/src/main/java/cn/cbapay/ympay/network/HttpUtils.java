package cn.cbapay.ympay.network;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;

import java.io.File;
import java.util.Map;

import cn.cbapay.ympay.bean.AreaModifyRequestBean;
import cn.cbapay.ympay.bean.BankVerifyRequestBean;
import cn.cbapay.ympay.bean.BindBankCardRequestBean;
import cn.cbapay.ympay.bean.CardBinRequestBean;
import cn.cbapay.ympay.bean.CareerUpdateRequestBean;
import cn.cbapay.ympay.bean.ChangeLoginPassWordBean;
import cn.cbapay.ympay.bean.ChangePayPswIdBean;
import cn.cbapay.ympay.bean.ChangePayPswRequestBean;
import cn.cbapay.ympay.bean.EmailVerifyRequestBean;
import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.ForgetPasswordNextBean;
import cn.cbapay.ympay.bean.FrogetPasswordSenRequestBean;
import cn.cbapay.ympay.bean.GetNewPhoneVerifyCodeRequestBean;
import cn.cbapay.ympay.bean.IDVerifyRequestBean;
import cn.cbapay.ympay.bean.LoginPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.LoginRequestBean;
import cn.cbapay.ympay.bean.NewPhoneVerifyRequestBean;
import cn.cbapay.ympay.bean.OriginalPhoneVerifyRequestBean;
import cn.cbapay.ympay.bean.PayPasswordVerifyRequestBean;
import cn.cbapay.ympay.bean.RegistRequestbean;
import cn.cbapay.ympay.bean.RequestBean;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.bean.SetPayPasswordRequestBean;
import cn.cbapay.ympay.bean.SettingRequestBean;
import cn.cbapay.ympay.bean.SubPhotoBean;
import cn.cbapay.ympay.network.api.HttpApi;
import cn.cbapay.ympay.utils.CaremaUtils;
import cn.cbapay.ympay.utils.LogUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

public class HttpUtils {

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public static Observable<ResultBean> login(String username, String password) {

        String jsonParam = "{}";

        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().login(params);
            }
        });
    }

    /**
     * 获取验证码（忘记密码）
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> getForgetPasswordCode(ForgetPasswordCodeRequsetBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().getForrgetPasswordCode(params);
            }
        });
    }

    /**
     * 校验验证码（忘记密码）
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> forgetPasswordNext(ForgetPasswordNextBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().forgetPasswordNext(params);
            }
        });
    }

    /**
     * 获取验证码（注册）
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> registCode(ForgetPasswordCodeRequsetBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().registCode(params);
            }
        });
    }

    /**
     * 注册提交
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> registSubmit(RegistRequestbean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().registSubmit(params);
            }
        });
    }

    /**
     * 登陆
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> login(LoginRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().login(params);
            }
        });
    }

    /**
     * 忘记密码
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> requestPassword(FrogetPasswordSenRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().requestPassword(params);
            }
        });
    }

    /**
     * 获取验证码（修改登陆密码）
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> changeLoginPasswordCode(ForgetPasswordCodeRequsetBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().changeLoginPasswordCode(params);
            }
        });
    }

    /**
     * 修改登陆密码
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> changeLoginPassword(ChangeLoginPassWordBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().changeLoginPassword(params);
            }
        });
    }

    /**
     * 校验验证码（忘记密码）
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> changeLoginPasswordNext(ForgetPasswordNextBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().changeLoginPasswordNext(params);
            }
        });
    }

    /**
     * 设置
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> setting(SettingRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().setting(params);
            }
        });
    }

    /**
     * 查询最新数据（账户信息），适用于修改数据之后更新数据
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> findCustomerInfo(RequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().findCustomerInfo(params);
            }
        });
    }

    /**
     * 查询账户信息
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> findAll(RequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().findAll(params);
            }
        });
    }

    /**
     * 账户信息->手机号->我的原手机号可用->获取原手机号验证码
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> sendMCode(RequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().sendMCode(params);
            }
        });
    }

    /**
     * 账户信息->手机号->我的原手机号可用->原手机号验证码校验
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> matching(OriginalPhoneVerifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().matching(params);
            }
        });
    }

    /**
     * 账户信息->手机号->我的原手机号可用->获取新手机号验证码
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> sendCode(GetNewPhoneVerifyCodeRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().sendCode(params);
            }
        });
    }

    /**
     * 账户信息->手机号->我的原手机号可用->新手机号验证码校验，更改手机号
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> updateMAlready(NewPhoneVerifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().updateMAlready(params);
            }
        });
    }

    /**
     * 账户信息->手机号->我的原手机号不可用->支付密码校验
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> payPwd(PayPasswordVerifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().payPwd(params);
            }
        });
    }

    /**
     * 账户信息->手机号->我的原手机号不可用->身份证校验
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> isIdRight(IDVerifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().isIdRight(params);
            }
        });
    }

    /**
     * 账户信息->手机号->未实名->验证登录密码
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> findPwd(LoginPasswordVerifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().findPwd(params);
            }
        });
    }

    /**
     * 账户信息->手机号->未实名->验证更改手机号
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> updateM(NewPhoneVerifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().updateM(params);
            }
        });
    }

    /**
     * 实名认证->首次实名完整流程->获取卡bin验证银行卡是否已绑定
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> findBankBin(CardBinRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().findBankBin(params);
            }
        });
    }

    /**
     * 实名认证->首次实名完整流程->判断是否设置支付密码和发送验证码，重新发送
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> getBankVerifyCode(BankVerifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().getBankVerifyCode(params);
            }
        });
    }

    /**
     * 实名认证->首次实名完整流程->设置支付密码
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> updateArpAcCustRel(SetPayPasswordRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().updateArpAcCustRel(params);
            }
        });
    }

    /**
     * 实名认证->首次实名完整流程->校验验证码并绑定银行卡
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> toBindBankCard(BindBankCardRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().toBindBankCard(params);
            }
        });
    }
    /**
     * 身份证照片
     * @param subPhotoBean
     * @return
     */

    public static Observable<ResultBean> subPhoto(final SubPhotoBean subPhotoBean) {
        String jsonParam = new Gson().toJson(subPhotoBean);
        LogUtil.d("tag", jsonParam);

        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                SubPhotoBean subPhotoBean1 = new Gson().fromJson(params, SubPhotoBean.class);
                LogUtil.d("tag", subPhotoBean1.getFront());
                Map<String, RequestBody> map = new LinkedHashTreeMap<>();
                map.put("token", RequestBody.create(MediaType.parse("multipart/form-data"), subPhotoBean1.getToken()));
                map.put("validityStart", RequestBody.create(MediaType.parse("multipart/form-data"), subPhotoBean1.getValidityStart()));
                map.put("validity", RequestBody.create(MediaType.parse("multipart/form-data"), subPhotoBean1.getValidity()));
                map.put("front\";filename=\"" + CaremaUtils.substring(subPhotoBean1.getFront()) + "", RequestBody.create(MediaType.parse("multipart/form-data"), new File(subPhotoBean1.getFront())));
                map.put("back\";filename=\"" + CaremaUtils.substring(subPhotoBean1.getBack()) + "", RequestBody.create(MediaType.parse("multipart/form-data"), new File(subPhotoBean1.getBack())));
                map.put("handheld\";filename=\"" + CaremaUtils.substring(subPhotoBean1.getHandheld()) + "", RequestBody.create(MediaType.parse("multipart/form-data"), new File(subPhotoBean1.getHandheld())));
                return Network.getGatewayApi().subMitPhoto(map);
            }
        });
    }

    /**
     * 更新职业
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> updateUsrJob(CareerUpdateRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().updateUsrJob(params);
            }
        });
    }

    /**
     * 获取全部职业
     *
     * @return
     */
    public static Observable<ResultBean> getAllJob() {
        String jsonParam = "";
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().allJob(params);
            }
        });
    }
    /**
     * 校验邮箱验证码
     * @param bean
     * @return
     */
    public static Observable<ResultBean> verificationEmailCode(EmailVerifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().verificationEmailCode(params);
            }
        });
    }

    /**
     * 修改地区
     *
     * @param bean
     * @return
     */
    public static Observable<ResultBean> updatePhoneAddress(AreaModifyRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        return Network.getObservable(jsonParam, new HttpApi() {

            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().updatePhoneAddress(params);
            }
        });
    }

    /**
     * 获取验证码（修改登陆密码）
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> changePayPasswordCode(ForgetPasswordCodeRequsetBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().changePayPasswordCode(params);
            }
        });
    }

    /**
     * 校验验证码（修改支付密码）
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> checkPayPswCode(ForgetPasswordNextBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().checkPayPswCode(params);
            }
        });
    }
    /**
     * 校验身份证号码（修改支付密码）
     *
     * @param
     * @return
     */
    public static Observable<ResultBean> checkIdNumber(ChangePayPswIdBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().checkIdNumber(params);
            }
        });
    }

    /**
     * 修改支付密码
     * @param bean
     * @return
     */
    public static Observable<ResultBean> changePayPsw(ChangePayPswRequestBean bean) {
        String jsonParam = new Gson().toJson(bean);
        LogUtil.e("HttpUtils", "---jsonParam---->>" + jsonParam);
        return Network.getObservable(jsonParam, new HttpApi() {
            @Override
            public Observable<ResultBean> call(String params) {
                return Network.getGatewayApi().changePayPsw(params);
            }
        });
    }
}
