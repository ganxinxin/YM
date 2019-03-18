package cn.cbapay.ympay.network.api;


import java.util.Map;

import cn.cbapay.ympay.bean.ResultBean;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

public interface GatewayApi {





    /**
     * 获取验证码（忘记密码）
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/getVerifyCode")
    Observable<ResultBean> getForrgetPasswordCode(@Field("params") String params);

    /**
     * 校验验证码（忘记密码）
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/checkVerifyCode")
    Observable<ResultBean> forgetPasswordNext(@Field("params") String params);

    /**
     * 获取验证码（注册）
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/regCheckLogin")
    Observable<ResultBean> registCode(@Field("params") String params);

    /**
     *注册提交
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<ResultBean> registSubmit(@Field("params") String params);

    /**
     * 登陆
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("user/userLogin")
    Observable<ResultBean> login(@Field("params") String params);

    /**
     * 设置
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("set/setting")
    Observable<ResultBean> setting(@Field("params") String params);
    /**
     * 查询最新数据（账户信息），适用于修改数据之后更新数据
     */
    @FormUrlEncoded
    @POST("user/findCustomerInfo")
    Observable<ResultBean> findCustomerInfo(@Field("params") String params);

    /**
     * 查询账户信息
     */
    @FormUrlEncoded
    @POST("accountInformation/findAll")
    Observable<ResultBean> findAll(@Field("params") String params);

    /**
     * 账户信息->手机号->我的原手机号可用->获取原手机号验证码
     */
    @FormUrlEncoded
    @POST("accountInformation/sendMCode")
    Observable<ResultBean> sendMCode(@Field("params") String params);

    /**
     * 账户信息->手机号->我的原手机号可用->原手机号验证码校验
     */
    @FormUrlEncoded
    @POST("accountInformation/matching")
    Observable<ResultBean> matching(@Field("params") String params);

    /**
     * 账户信息->手机号->我的原手机号可用->获取新手机号验证码
     */
    @FormUrlEncoded
    @POST("accountInformation/sendCode")
    Observable<ResultBean> sendCode(@Field("params") String params);

    /**
     * 账户信息->手机号->我的原手机号可用->新手机号验证码校验，更改手机号
     */
    @FormUrlEncoded
    @POST("accountInformation/updateMAlready")
    Observable<ResultBean> updateMAlready(@Field("params") String params);

    /**
     * 账户信息->手机号->我的原手机号不可用->支付密码校验
     */
    @FormUrlEncoded
    @POST("accountInformation/payPwd")
    Observable<ResultBean> payPwd(@Field("params") String params);

    /**
     * 账户信息->手机号->我的原手机号不可用->身份证校验
     */
    @FormUrlEncoded
    @POST("set/isIdRight")
    Observable<ResultBean> isIdRight(@Field("params") String params);

    /**
     * 账户信息->手机号->未实名->验证登录密码
     */
    @FormUrlEncoded
    @POST("accountInformation/findPwd")
    Observable<ResultBean> findPwd(@Field("params") String params);

    /**
     * 账户信息->手机号->未实名->验证更改手机号
     */
    @FormUrlEncoded
    @POST("accountInformation/updateM")
    Observable<ResultBean> updateM(@Field("params") String params);

    /**
     * 账户信息->邮箱->验证更改邮箱
     */
    @FormUrlEncoded
    @POST("accountInformation/verificationEmailCode")
    Observable<ResultBean> verificationEmailCode(@Field("params") String params);

    /**
     * 账户信息->地区->修改地区信息
     */
    @FormUrlEncoded
    @POST("accountInformation/updatePhoneAddress")
    Observable<ResultBean> updatePhoneAddress(@Field("params") String params);

    /**
     * 账户信息->职业->获取职业信息列表
     */
    @FormUrlEncoded
    @POST("accountInformation/allJob")
    Observable<ResultBean> allJob(@Field("params") String params);

    /**
     * 账户信息->职业->修改职业信息
     */
    @FormUrlEncoded
    @POST("accountInformation/updateUsrJob")
    Observable<ResultBean> updateUsrJob(@Field("params") String params);

    /**
     * 实名认证->首次实名完整流程->获取卡bin验证银行卡是否已绑定
     */
    @FormUrlEncoded
    @POST("realNameAuthent/findBankBin")
    Observable<ResultBean> findBankBin(@Field("params") String params);

    /**
     * 实名认证->首次实名完整流程->判断是否设置支付密码和发送验证码，重新发送
     */
    @FormUrlEncoded
    @POST("realNameAuthent/getBankVerifyCode")
    Observable<ResultBean> getBankVerifyCode(@Field("params") String params);

    /**
     * 实名认证->首次实名完整流程->设置支付密码
     */
    @FormUrlEncoded
    @POST("realNameAuthent/updateArpAcCustRel")
    Observable<ResultBean> updateArpAcCustRel(@Field("params") String params);

    /**
     * 实名认证->首次实名完整流程->校验验证码并绑定银行卡
     */
    @FormUrlEncoded
    @POST("realNameAuthent/toBindBankCard")
    Observable<ResultBean> toBindBankCard(@Field("params") String params);


    /**
     *  传身份证照片
     *
     * @return
     */

    @Multipart
    @POST("idSubmitAudit/submit")
    Observable<ResultBean> subMitPhoto( @PartMap Map<String,RequestBody> params);

    /**
     *忘记密码
     */
    @FormUrlEncoded
    @POST("user/findBackPWD")
    Observable<ResultBean> requestPassword(@Field("params") String params);

    /**
     *获取验证码（修改登录密码）
     */
    @FormUrlEncoded
    @POST("set/getLoginPwdCode")
    Observable<ResultBean> changeLoginPasswordCode(@Field("params") String params);

    /**
     *校验验证码（修改登录密码）
     */
    @FormUrlEncoded
    @POST("set/checkLoginPwdCode")
    Observable<ResultBean> changeLoginPasswordNext(@Field("params") String params);

    /**
     *修改登录密码
     */
    @FormUrlEncoded
    @POST("set/upCustPwd")
    Observable<ResultBean> changeLoginPassword(@Field("params") String params);

    /**
     *获取验证码（修改支付密码）
     */
    @FormUrlEncoded
    @POST("set/getPayPwdCode")
    Observable<ResultBean> changePayPasswordCode(@Field("params") String params);

    /**
     *校验验证码（修改支付密码）
     */
    @FormUrlEncoded
    @POST("user/checkVerifyCode")
    Observable<ResultBean> checkPayPswCode(@Field("params") String params);

    /**
     *校验身份证号码（修改支付密码）
     */
    @FormUrlEncoded
    @POST("set/isIdRight")
    Observable<ResultBean> checkIdNumber(@Field("params") String params);

    /**
     *修改支付密码
     */
    @FormUrlEncoded
    @POST("set/upPayPwd")
    Observable<ResultBean> changePayPsw(@Field("params") String params);

}
