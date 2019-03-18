// Generated code from Butter Knife. Do not modify!
package cn.cbapay.ympay.mvp.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RegisterActivity$$ViewInjector {
  public static void inject(Finder finder, final cn.cbapay.ympay.mvp.ui.activity.RegisterActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427457, "field 'ivOpen' and method 'onClick'");
    target.ivOpen = (android.widget.ImageView) view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
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
    view = finder.findRequiredView(source, 2131427378, "field 'mTytle'");
    target.mTytle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131427494, "field 'etPhone'");
    target.etPhone = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427456, "field 'etPassword'");
    target.etPassword = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131427495, "field 'cbAgree'");
    target.cbAgree = (android.widget.CheckBox) view;
    view = finder.findRequiredView(source, 2131427485, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427496, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  public static void reset(cn.cbapay.ympay.mvp.ui.activity.RegisterActivity target) {
    target.ivOpen = null;
    target.btRegister = null;
    target.mTytle = null;
    target.etPhone = null;
    target.etPassword = null;
    target.cbAgree = null;
  }
}
