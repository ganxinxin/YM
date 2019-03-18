package cn.cbapay.ympay.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.adapter.CareerAdapter;
import cn.cbapay.ympay.bean.CareerBean;
import cn.cbapay.ympay.bean.CareerUpdateRequestBean;
import cn.cbapay.ympay.mvp.presenter.AllJobPresenter;
import cn.cbapay.ympay.mvp.presenter.UpdateJobPresenter;
import cn.cbapay.ympay.mvp.presenter.impl.AllJobPresenterImpl;
import cn.cbapay.ympay.mvp.presenter.impl.UpdateJobPresenterImpl;
import cn.cbapay.ympay.mvp.ui.base.BaseActivity;
import cn.cbapay.ympay.utils.GlobalData;
import cn.cbapay.ympay.utils.ShareUtil;
import cn.cbapay.ympay.utils.Tools;

/**
 * 职业
 * Created by icewater on 16/9/20.
 */
public class CareerActivity extends BaseActivity implements UpdateJobPresenter.UpdateJobView, AllJobPresenter.AllJobView {
    private CareerBean mCareer;
    private UpdateJobPresenter mUpdateJobPresenter;
    private AllJobPresenter mAllJobPresenter;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ListView listView;

    private CareerAdapter adapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_career;
    }

    @Override
    protected void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        listView = (ListView) findViewById(R.id.list);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mUpdateJobPresenter = new UpdateJobPresenterImpl(this);
        toolbarTitle.setText("职业");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new CareerAdapter(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCareer = adapter.getItem(i);
                CareerUpdateRequestBean bean = new CareerUpdateRequestBean();
                bean.setToken(ShareUtil.getValue("token", CareerActivity.this));
                bean.setUsrJob(mCareer.getUsrJob());
                mUpdateJobPresenter.updateJob(bean);
            }
        });
        mAllJobPresenter = new AllJobPresenterImpl(this);
        mAllJobPresenter.getAllJob();
    }

    @Override
    public void updateJobSuccess() {
        Intent intent = new Intent();
        intent.putExtra("job", mCareer.getUsrJob());
        setResult(RESULT_OK, intent);
        if (GlobalData.getStpusrinf() != null) {
            GlobalData.getStpusrinf().setUsrJob(mCareer.getUsrJob());
        }
        finish();
    }

    @Override
    public void updateJobFail(String msg) {
        Tools.showToast(this, msg);
    }

    @Override
    public void getAllJobSuccess(List<CareerBean> jobList) {
        adapter.clearItems();
        adapter.addItems(jobList);
        listView.setAdapter(adapter);
    }

    @Override
    public void getAllJobFail(String msg) {
        Tools.showToast(this, msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAllJobPresenter.onDestroy();
        mUpdateJobPresenter.onDestroy();
    }
}
