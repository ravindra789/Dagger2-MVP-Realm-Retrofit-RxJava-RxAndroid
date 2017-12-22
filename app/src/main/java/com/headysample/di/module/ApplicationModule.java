package com.headysample.di.module;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nndra on 06-Dec-17.
 */
@Module
public class ApplicationModule {

    private String mBaseUrl = "";
    private Context mContext;

    public ApplicationModule(Context context,String baseUrl) {
        mContext = context;
        mBaseUrl = baseUrl;
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(){

        GsonConverterFactory gsonConverterFactory =  GsonConverterFactory.create();

       return gsonConverterFactory;
    }


    @Singleton
    @Provides
    @Named("ok-1")
    OkHttpClient provaideOkHttpClient1(){

        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("ok-2")
    OkHttpClient provaideOkHttpClient2(){

        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory(){

        return RxJavaCallAdapterFactory.create();
    }


    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("ok-1") OkHttpClient client, GsonConverterFactory convertFactory, RxJavaCallAdapterFactory rxJavaCallAdapterFactory){

        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(convertFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    Context provideContext(){

        return mContext;
    }

}
