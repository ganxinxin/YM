package cn.cbapay.ympay.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.adapter.base.MyBaseAdapter;
import cn.cbapay.ympay.bean.CareerBean;

/**
 * 职业
 * Created by icewater on 16/9/20.
 */
public class CareerAdapter extends MyBaseAdapter<CareerBean> {

    public CareerAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_career, null);
            viewHolder.viewDivider = view.findViewById(R.id.view_divider);
            viewHolder.textName = (TextView) view.findViewById(R.id.text_name);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        initializeViews(i, getItem(i), viewHolder);
        return view;
    }

    private void initializeViews(int pos, CareerBean object, ViewHolder holder) {
        if (pos == 0) {
            holder.viewDivider.setVisibility(View.GONE);
        } else {
            holder.viewDivider.setVisibility(View.VISIBLE);
        }

        holder.textName.setText(object.getUsrJob());
    }

    static class ViewHolder {
        private View viewDivider;
        private TextView textName;
    }
}
