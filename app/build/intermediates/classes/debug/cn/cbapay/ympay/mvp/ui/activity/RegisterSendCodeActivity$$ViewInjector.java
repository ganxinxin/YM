// Generated code from Butter Knife. Do not modify!
package cn.cbapay.ympay.mvp.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RegisterSendCodeActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.cbapay.ympay.mvp.ui.activity.RegisterSendCodeActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427378, "field 'mTytle'");
    target.mTytle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427453, "field 'etCode'");
    target.etCode = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427497, "field 'btRegister' and method 'onClick'");
    target.btRegister = (android.widget.Button) view;
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
    view = finder.findRequiredView(source, 2131427485, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  public static void reset(cn.cbapay.ympay.mvp.ui.activity.RegisterSendCodeActivity target) {
    target.mTytle = null;
    target.etCode = null;
    target.btRegister = null;
    target.btCode = null;
  }
}
