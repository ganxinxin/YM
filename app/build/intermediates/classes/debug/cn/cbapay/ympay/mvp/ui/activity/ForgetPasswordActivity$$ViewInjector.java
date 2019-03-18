// Generated code from Butter Knife. Do not modify!
package cn.cbapay.ympay.mvp.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ForgetPasswordActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.cbapay.ympay.mvp.ui.activity.ForgetPasswordActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427378, "field 'tytle'");
    target.tytle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427455, "field 'btNext' and method 'onClick'");
    target.btNext = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427469, "field 'etNumber'");
    target.etNumber = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427453, "field 'etCode'");
    target.etCode = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427485, "field 'back' and method 'onClick'");
    target.back = (android.support.v7.widget.Toolbar) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427454, "field 'btCode' and method 'onClick'");
    target.btCode = (android.widget.Button) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  public static void reset(cn.cbapay.ympay.mvp.ui.activity.ForgetPasswordActivity target) {
    target.tytle = null;
    target.btNext = null;
    target.etNumber = null;
    target.etCode = null;
    target.back = null;
    target.btCode = null;
  }
}
