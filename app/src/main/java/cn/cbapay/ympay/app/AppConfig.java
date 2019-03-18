package cn.cbapay.ympay.app;

/**
 * Created by xuetao on 16/1/7.
 */
public class AppConfig {

    private static AppConfig mAppConfig;



    private AppConfig() {

    }

    public static AppConfig getInstance() {
        // 先检查实例是否存在，如果不存在才进入下面的同步块
        if (mAppConfig == null) {
            // 同步块，线程安全的创建实例
            synchronized (AppConfig.class) {
                // 再次检查实例是否存在，如果不存在才真正的创建实例
                if (mAppConfig == null) {
                    mAppConfig = new AppConfig();
                }
            }
        }
        return mAppConfig;
    }


}
