// Generated code from Butter Knife. Do not modify!
package cn.cbapay.ympay.mvp.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SettingActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.cbapay.ympay.mvp.ui.activity.SettingActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165412, "field 'tytle'");
    target.tytle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131165215, "field 'back' and method 'onClick'");
    target.back = (android.support.v7.widget.Toolbar) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165415, "field 'tlChangLoginPassword' and method 'onClick'");
    target.tlChangLoginPassword = (android.widget.RelativeLayout) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165429, "field 'tvPhone' and method 'onClick'");
    target.tvPhone = (android.widget.TextView) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165423, "field 'tvCertification'");
    target.tvCertification = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131165428, "field 'tvPay'");
    target.tvPay = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131165440, "field 'unLogin' and method 'onClick'");
    target.unLogin = (android.widget.TextView) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165356, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165359, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  public static void reset(cn.cbapay.ympay.mvp.ui.activity.SettingActivity target) {
    target.tytle = null;
    target.back = null;
    target.tlChangLoginPassword = null;
    target.tvPhone = null;
    target.tvCertification = null;
    target.tvPay = null;
    target.unLogin = null;
  }
}
