// Generated code from Butter Knife. Do not modify!
package cn.cbapay.ympay.mvp.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChangeLoginPasswordSecondActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.cbapay.ympay.mvp.ui.activity.ChangeLoginPasswordSecondActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165412, "field 'tytle'");
    target.tytle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131165274, "field 'etPassword'");
    target.etPassword = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131165314, "field 'ivOpen' and method 'onClick'");
    target.ivOpen = (android.widget.ImageView) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165225, "field 'btOk' and method 'onClick'");
    target.btOk = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165215, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  public static void reset(cn.cbapay.ympay.mvp.ui.activity.ChangeLoginPasswordSecondActivity target) {
    target.tytle = null;
    target.etPassword = null;
    target.ivOpen = null;
    target.btOk = null;
  }
}
