package cn.cbapay.ympay.network;

import cn.cbapay.ympay.app.AppUrl;
import cn.cbapay.ympay.bean.ResultBean;
import cn.cbapay.ympay.network.api.GatewayApi;
import cn.cbapay.ympay.network.api.HttpApi;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Network {

    private static GatewayApi gatewayApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static GatewayApi getGatewayApi() {
        if (gatewayApi == null) {
            synchronized (Network.class) {
                if (gatewayApi == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl(AppUrl.BASE_URL)
                            .addConverterFactory(gsonConverterFactory)
                            .addCallAdapterFactory(rxJavaCallAdapterFactory)
                            .build();
                    gatewayApi = retrofit.create(GatewayApi.class);
                }
            }
        }
        return gatewayApi;
    }

    public static Observable<ResultBean> getObservable(String jsonParam, final HttpApi mHttpApi) {
        return Observable.just(jsonParam).map(new Func1<String, String>() {

            @Override
            public String call(String s) {
                // TODO 加密请求参数
                return s;
            }
        }).flatMap(new Func1<String, Observable<ResultBean>>() {

            @Override
            public Observable<ResultBean> call(String s) {
                return mHttpApi.call(s);
            }
        }).map(new Func1<ResultBean, ResultBean>() {

            @Override
            public ResultBean call(ResultBean bean) {
                if ("1".equals(bean.getResCode()) && bean.getData() != null) {
                    // TODO 解密返回数据
                    bean.setJsonData(bean.getData().toString());
                }
                return bean;
            }
        }).subscribeOn(Schedulers.io());
    }
}
