package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.adapter.ProvinceAdapter;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.AreaBean;
import cn.cbapay.ympay.bean.AreaModifyRequestBean;
import cn.cbapay.ympay.bean.CityNameBean;
import cn.cbapay.ympay.mvp.presenter.AreaModifyPresenter;
import cn.cbapay.ympay.mvp.presenter.GetAreaPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.AreaModifyPresenterImpl;
import cn.cbapay.ympay.mvp.presenter.impl.GetAreaPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.service.LocationService;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * Created by Administrator on 2016/9/20.
 * gaowei
 */
public class ProvinceActivity extends BaseActivity implements AreaModifyPresenter.AreaModifyView, GetAreaPresenter.AreaView, View.OnClickListener {


    private LocationService locationService;
    private StringBuffer sb;
    private GetAreaPresenter getAreaPresenter;
    private AreaModifyPresenter areaModifyPresenter;
    private TextView title;
    private TextView locationResult;
    private ListView listProvince;
    private String location;
    /**
     * 省
     */
    private List<CityNameBean> provinceList;

    private Toolbar back;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_area;
    }

    @Override
    protected void initViews() {
        title = (TextView) findViewById(R.id.title);
        locationResult = (TextView) findViewById(R.id.location);
        listProvince = (ListView) findViewById(R.id.lv_city);
        back = (Toolbar) findViewById(R.id.back);
        back.setOnClickListener(this);
        locationResult.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getAreaPresenter = new GetAreaPresenterImpl(this);
        areaModifyPresenter = new AreaModifyPresenterImpl(this);
        getAreaPresenter.getArea();
        title.setText("地  区");
        listProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityNameBean provinceBean = provinceList.get(position);
                String province = provinceBean.getName();
                Intent i = new Intent();
                i.setClass(ProvinceActivity.this, CityActivity.class);
                i.putExtra("province", province);
                startActivityForResult(i, 1);
            }
        });
        locationResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = locationResult.getText().toString();
                if (!TextUtils.isEmpty(location)) {
                    AreaModifyRequestBean bean = new AreaModifyRequestBean();
                    bean.setToken(ShareUtil.getValue("token", ProvinceActivity.this));
                    bean.setPhoneAddress(location);
                    areaModifyPresenter.updatePhoneAddress(bean);
                }
            }
        });

    }

    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str) {
        try {
            if (str.equals("")) {
                System.out.println(str + "gaowei");
                locationResult.setText("定位失败");
                locationResult.setEnabled(false);

            } else {
                locationResult.setText(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
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


    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {


        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                sb = new StringBuffer(256);
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果*/
                    sb.append(location.getProvince());
                    sb.append("  ");// 城市
                    sb.append(location.getCity());
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    sb.append(location.getProvince());
                    sb.append("  ");// 城市
                    sb.append(location.getCity());
                }
                logMsg(sb.toString());

            }
        }

    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    if (data != null) {
                        setResult(RESULT_OK, data);
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void onGetAreaSuccess(AreaBean bean) {
        MyApplication.getInstance().setAreaBean(bean);
        List<AreaBean.CitylistBean> list = bean.getCitylist();
        provinceList = new ArrayList<CityNameBean>();
        for (AreaBean.CitylistBean temp : list) {
            provinceList.add(new CityNameBean(temp.getP()));
        }
        Collections.sort(provinceList);
        listProvince.setAdapter(new ProvinceAdapter(provinceList, this));
    }

    @Override
    public void onGetAreaFail(String msg) {
        Tools.showToast(this, msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getAreaPresenter.onDestroy();
        areaModifyPresenter.onDestroy();
        MyApplication.getInstance().setAreaBean(null);
    }

    @Override
    public void onAreaModifySuccess() {
        Intent intent = new Intent();
        intent.putExtra("location", location);
        setResult(RESULT_OK, intent);
        if (GlobalData.getStpusrinf() != null) {
            GlobalData.getStpusrinf().setPhoneAddress(location);
        }
        finish();
    }

    @Override
    public void onAreaModifyFail(String msg) {
        Tools.showToast(this, msg);
    }
}
