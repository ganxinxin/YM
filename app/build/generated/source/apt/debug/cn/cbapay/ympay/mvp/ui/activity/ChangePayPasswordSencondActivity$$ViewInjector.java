// Generated code from Butter Knife. Do not modify!
package cn.cbapay.ympay.mvp.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChangePayPasswordSencondActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.cbapay.ympay.mvp.ui.activity.ChangePayPasswordSencondActivity target, Object source) {
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
    view = finder.findRequiredView(source, 2131165269, "field 'etCardCode'");
    target.etCardCode = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131165224, "field 'btNext' and method 'onClick'");
    target.btNext = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  public static void reset(cn.cbapay.ympay.mvp.ui.activity.ChangePayPasswordSencondActivity target) {
    target.tytle = null;
    target.back = null;
    target.etCardCode = null;
    target.btNext = null;
  }
}
