package cn.cbapay.ympay.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.CityNameBean;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ProvinceAdapter extends BaseAdapter {

    private List<CityNameBean> mProvinces;
    private Context context;

    public ProvinceAdapter(List<CityNameBean> provinces, Context context) {
        super();
        this.mProvinces = provinces;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mProvinces.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_provinces, null);
            viewHolder.tv_index = (TextView) convertView.findViewById(R.id.tv_index);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CityNameBean provincesBean = mProvinces.get(position);

        // 当前首字母
        String currentStr = provincesBean.getPinyin().charAt(0) + "";

        String indexStr = null;
        // 如果是第一个, 直接显示
        if (position == 0) {
            indexStr = currentStr;
        } else {
            // 判断当前首字母和上一个条目的首字母是否一致, 不一致时候显示.
            String lastStr = mProvinces.get(position - 1).getPinyin().charAt(0) + "";
            if (!TextUtils.equals(lastStr, currentStr)) {
                // 不一致时候赋值indexStr
                indexStr = currentStr;
            }

        }

        viewHolder.tv_index.setVisibility(indexStr != null ? View.VISIBLE : View.GONE);
        viewHolder.tv_index.setText(currentStr);
        viewHolder.tv_name.setText(provincesBean.getName());

        return convertView;
    }

    static class ViewHolder {
        TextView tv_index;
        TextView tv_name;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
