package cn.cbapay.ympay.utils;

import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * sharePerferences存储的文件在/data/data/package name/shared_perfs目录下
 * 
 * @author Administrator
 *
 */
public class ShareUtil {
	Context context = null;

	public ShareUtil(Context context) {
		this.context = context;
	}

	/**
	 * 保存
	 * 
	 * @param filename
	 *            保存的文件名
	 * @param mode
	 *            访问模式:Context.MODE_PRIVATE:文件是该程序的私有 ,新内容覆盖旧内容
	 *            MODE_APPEND向文件最后追加内容(这两种模式创建的文件只能是程序自己可以使用的)
	 *            MODE_WORLD_READABLE 表示可以被其他程序读取,MODE_WORLD_WEITEABLE
	 *            可以被其他程序写入.最后两个可以使用+号组合(文件名需要使用准确的路径+文件名)
	 *            (APPEND+READABLE+WEITEABLE)组合被允许
	 * @param map
	 *            保存的设置 key 为保存的设置名,value 设置值
	 */
	public void save(String filename, int mode, Map<String, String> map) {
		SharedPreferences preferences = context.getSharedPreferences(filename,
				mode);
		Editor editor = preferences.edit();
		Set<String> keys = map.keySet();
		for (String string : keys) {
			editor.putString(string, map.get(string));
		}
		editor.commit();
	}

	/**
	 * 获得本程序下的配置
	 * 
	 * @param filename
	 * @param mode
	 * @param pername
	 * @return
	 */
	public String getPer(String filename, int mode, String pername) {
		SharedPreferences preferences = context.getSharedPreferences(filename,
				mode);
		String per = preferences.getString(pername, "");
		return per;
	}

	/**
	 * 获得其他程序配置信息
	 * 
	 * @param filename
	 *            文件名
	 * @param mode
	 *            模式
	 * @param pername
	 *            参数名
	 * @param packages
	 *            其他应用的包名
	 * @return
	 * @throws Exception
	 */
	public String getPer(String filename, int mode, String pername,
			String packages) throws Exception {
		Context excontext = context.createPackageContext(packages,
				Context.CONTEXT_IGNORE_SECURITY);// 获得其他程序的上下文
		SharedPreferences preferences = excontext.getSharedPreferences(
				filename, mode);
		String per = preferences.getString(pername, "");
		return per;
	}

	public String getPer(String pername) {
		return getPer("properties", Context.MODE_PRIVATE, pername);
	}

	public void savePer(Map<String, String> map) {
		save("properties", Context.MODE_PRIVATE, map);
	}

	public void savePer(String key, String per) {
		SharedPreferences preferences = context.getSharedPreferences(
				"properties", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(key, per);
		editor.commit();
	}
	
	/**
	 * 设置水电煤缴费默认城市
	 * 
	 * @param context
	 * @param city
	 *            格式为： 省份 + "," + 城市
	 */
	public static void setDefaultRegion(Context context, String region) {// 写入XML数据
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString("default_region", region);
		editor.commit(); // 一定要记得提交
	}

	/**
	 * 水电煤缴费获取默认城市
	 */
	public static String getDefaultRegion(Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		return sp.getString("default_region", "");
	}
	
	public static String getValue(String key, Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		return sp.getString(key, "");
	}
	
	public static void setValue(String key, String value, Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit(); // 一定要记得提交
	}
	
	public static long getLongValue(String key, Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		return sp.getLong(key, 0);
	}
	
	public static void setLongValue(String key, long value, Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit(); // 一定要记得提交
	}
	
	public static boolean getBooleanValue(String key, Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		return sp.getBoolean(key, true);
	}
	
	public static void setBooleanValue(String key, boolean value, Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit(); // 一定要记得提交
	}
	
}
