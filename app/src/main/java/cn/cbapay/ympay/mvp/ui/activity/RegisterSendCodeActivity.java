package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.ForgetPasswordCodeRequsetBean;
import cn.cbapay.ympay.bean.RegistRequestbean;
import cn.cbapay.ympay.bean.RegisterResultBean;
import cn.cbapay.ympay.mvp.presenter.RegisterSendCodePresenter;
import cn.cbapay.ympay.mvp.presenter.impl.RegisterSendCodePresenterImpl;
import cn.cbapay.ympay.mvp.ui.activity.pager.MinePager;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.service.LocationService;
import cn.cbapay.ympay.utils.ActivityUtils;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;

/**
 * Created by gaowei on 2016/9/18.
 */
public class RegisterSendCodeActivity extends BaseActivity implements RegisterSendCodePresenter.RegisterSendCodeView {


    @InjectView(R.id.title)
    TextView mTytle;
    @InjectView(R.id.et_code)
    EditText etCode;
    @InjectView(R.id.bt_register)
    Button btRegister;
    @InjectView(R.id.bt_code)
    Button btCode;


    private RegisterSendCodePresenterImpl registerSendCodePresenter;
    private LocationService locationService;
    private StringBuffer sb;
    private String mPhoneNumber;
    private String mPassWord;
    private ActivityUtils instance;
    //经度
    private String longitude;
    //纬度
    private String latitude;
    //手机版本号
    private String model;
    //ip地址
    private String ipAdrress;
    //mac地址
    private String localMacAddressFromWifiInfo;
    //IMEI
    private String deviceId;

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            registerSendCodePresenter.showSubmitState(s.length());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register_send_code;
    }

    @Override
    protected void initViews() {
        registerSendCodePresenter = new RegisterSendCodePresenterImpl(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                sb = new StringBuffer(256);
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果*/
                    //国家
                    sb.append(location.getCountry());
                    sb.append("-");
                    //省
                    sb.append(location.getProvince());
                    sb.append("-");
                    //市
                    sb.append(location.getCity());
                    sb.append("-");
                    //区
                    sb.append(location.getDistrict());
                    //街道
                    sb.append(location.getStreet());
                    longitude = location.getLongitude() + "";
                    latitude = location.getLatitude() + "";

                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    //国家
                    sb.append(location.getCountry());
                    sb.append("-");
                    //省
                    sb.append(location.getProvince());
                    sb.append("-");
                    //市
                    sb.append(location.getCity());
                    sb.append("-");
                    //区
                    sb.append(location.getDistrict());
                    //街道
                    sb.append(location.getStreet());
                    longitude = location.getLongitude() + "";
                    latitude = location.getLatitude() + "";
                }
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        mTytle.setText("注册");
        etCode.addTextChangedListener(watcher);
        Intent intent = getIntent();
        mPhoneNumber = intent.getStringExtra("phoneNumber");
        mPassWord = intent.getStringExtra("passWord");
        registerSendCodePresenter.initCodeButton();
        instance = ActivityUtils.getInstance();
        instance.addActivity(this);
        model = Build.MODEL;
    }


    @Override
    public void to(RegisterResultBean bean) {
        ShareUtil.setValue("token", bean.getToken(), this);
        ShareUtil.setValue("phoneNumber", mPhoneNumber, this);
        ShareUtil.setValue("firstRegist", "1", this);
        openActivity(MainActivity.class);
        instance.exit();

    }

    @Override
    public void yesSubmit() {
        btRegister.setBackgroundResource(R.mipmap.register_available);
        btRegister.setEnabled(true);
        btRegister.setTextColor(Color.WHITE);

    }

    @Override
    public void noSubmit() {
        btRegister.setBackgroundResource(R.mipmap.register);
        btRegister.setEnabled(false);

    }

    @Override
    public void hideSendCodeProgress() {
        btCode.setText("获取验证码");
        btCode.setEnabled(true);
        btCode.setBackgroundResource(R.mipmap.code_selected);
        btCode.setTextColor(Color.parseColor("#E2483C"));
    }

    @Override
    public void showSendStatus(String msg) {
        btCode.setText(msg);
        btCode.setEnabled(false);
        btCode.setBackgroundResource(R.mipmap.code);
        btCode.setTextColor(Color.WHITE);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.back, R.id.bt_code, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.bt_code:
                ForgetPasswordCodeRequsetBean bean = new ForgetPasswordCodeRequsetBean();
                bean.setCustLogin(mPhoneNumber);
                registerSendCodePresenter.getCode(bean);
                break;
            case R.id.bt_register:
                ipAdrress = "";
                deviceId = getDeviceId();
                localMacAddressFromWifiInfo = getLocalMacAddressFromWifiInfo(this);
                RegistRequestbean requestBean = new RegistRequestbean();
                requestBean.setCustLogin(mPhoneNumber);
                requestBean.setCustPwd(mPassWord);
                requestBean.setPhoneAddress(sb.toString());
                requestBean.setLongitude(longitude);
                requestBean.setLatitude(latitude);
                requestBean.setPhoneModel(model);
                requestBean.setIpAddress(ipAdrress);
                requestBean.setMacAddress(localMacAddressFromWifiInfo);
                requestBean.setValidateCode(etCode.getText().toString());
                requestBean.setMachineNum(deviceId);
                registerSendCodePresenter.submit(requestBean);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // -----------location config ------------
        locationService = new LocationService(this);
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();
    }

    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    /**
     * 获取mac地址
     * @param context
     * @return
     */
    public static String getLocalMacAddressFromWifiInfo(Context context){
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    public String getDeviceId(){
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        return tm.getDeviceId();
    }
}
