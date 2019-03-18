package cn.cbapay.ympay.mvp.presenter;


public interface OnCaptureCallback {

	public void onCapture(boolean success, String filePath);
}