// Generated code from Butter Knife. Do not modify!
package cn.cbapay.ympay.mvp.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChangePayPasswordThridActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.cbapay.ympay.mvp.ui.activity.ChangePayPasswordThridActivity target, Object source) {
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
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131165421, "field 'tv'");
    target.tv = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131165288, "field 'gpvInputPwd'");
    target.gpvInputPwd = (com.jungly.gridpasswordview.GridPasswordView) view;
  }

  public static void reset(cn.cbapay.ympay.mvp.ui.activity.ChangePayPasswordThridActivity target) {
    target.tytle = null;
    target.back = null;
    target.tv = null;
    target.gpvInputPwd = null;
  }
}
