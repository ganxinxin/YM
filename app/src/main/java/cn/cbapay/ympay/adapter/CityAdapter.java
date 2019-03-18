package cn.cbapay.ympay.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.CityNameBean;

/**
 * Created by Administrator on 2016/9/21.
 */
public class CityAdapter extends BaseAdapter {
    private List<CityNameBean> citys;
    private Context mContext;

    public CityAdapter(List<CityNameBean> s, Context context) {
        super();
        this.citys = s;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return citys.size();
    }

    @Override
    public Object getItem(int position) {
        return citys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_citys, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCity.setText(citys.get(position).getName());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_city)
        TextView tvCity;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
