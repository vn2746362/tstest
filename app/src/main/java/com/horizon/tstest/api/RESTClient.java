package com.horizon.tstest.api;

import com.horizon.tstest.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/*By Phat La*/
public class RESTClient {
    public final String TAG = RESTClient.class.getSimpleName();

    //
    private static RESTService mRestService = null;


    //
    public static RESTService getService() {
        if(mRestService == null) {
            //Create HTTP Client with Mock Interceptor
            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new MockInterceptor())
                    .build();

            //Build retrofit with config below
            final Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.API_URI)
                    .client(client)
                    .build();

            mRestService = retrofit.create(RESTService.class);
        }
        return mRestService;
    }
}
