package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.adapter.CityAdapter;
import cn.cbapay.ympay.app.MyApplication;
import cn.cbapay.ympay.bean.AreaBean;
import cn.cbapay.ympay.bean.AreaModifyRequestBean;
import cn.cbapay.ympay.bean.CityNameBean;
import cn.cbapay.ympay.mvp.presenter.AreaModifyPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.AreaModifyPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * Created by Administrator on 2016/9/21.
 */
public class CityActivity extends BaseActivity implements View.OnClickListener, AreaModifyPresenter.AreaModifyView {

    private ListView listCity;
    private TextView title;
    private Toolbar back;
    private AreaBean areaBean;
    private String province;
    private String city;
    private List<CityNameBean> cityList;
    private AreaModifyPresenter areaModifyPresenter;

    @Override
    protected int getLayoutResID() {

        return R.layout.activity_city;
    }

    @Override
    protected void initViews() {
        listCity = (ListView) findViewById(R.id.lv_city);
        title = (TextView) findViewById(R.id.title);
        back = (Toolbar) findViewById(R.id.back);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        title.setText("地  区");
        back.setOnClickListener(this);
        areaModifyPresenter = new AreaModifyPresenterImpl(this);
        final Intent intent = getIntent();
        if (intent != null) {
            areaBean = MyApplication.getInstance().getAreaBean();
            province = intent.getStringExtra("province");
        }
        List<AreaBean.CitylistBean> citylistBeen = areaBean.getCitylist();
        cityList = new ArrayList<CityNameBean>();
        for (AreaBean.CitylistBean tempP : citylistBeen) {
            if (province.equals(tempP.getP())) {
                if (tempP.getC() != null && tempP.getC().size() > 0) {
                    for (AreaBean.CitylistBean.CBean tempC : tempP.getC()) {
                        CityNameBean tempBean = new CityNameBean(tempC.getN());
                        cityList.add(tempBean);
                    }
                }
            }
        }
        Collections.sort(cityList);
        listCity.setAdapter(new CityAdapter(cityList, this));
        listCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = cityList.get(position).getName();
                String area = province + "  " + city;
                AreaModifyRequestBean bean = new AreaModifyRequestBean();
                bean.setToken(ShareUtil.getValue("token", CityActivity.this));
                bean.setPhoneAddress(area);
                areaModifyPresenter.updatePhoneAddress(bean);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
        }
    }

    @Override
    public void onAreaModifySuccess() {
        Intent i = new Intent();
        String area = province + "  " + city;
        i.putExtra("location", area);
        setResult(RESULT_OK, i);
        if (GlobalData.getStpusrinf() != null) {
            GlobalData.getStpusrinf().setPhoneAddress(area);
        }
        finish();
    }

    @Override
    public void onAreaModifyFail(String msg) {
        Tools.showToast(this, msg);
    }
}
