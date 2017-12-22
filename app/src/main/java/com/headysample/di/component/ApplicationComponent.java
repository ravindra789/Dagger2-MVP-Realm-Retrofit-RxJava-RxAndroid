package com.headysample.di.component;

import android.content.Context;

import com.headysample.application.HeadyApplication;
import com.headysample.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by nndra on 06-Dec-17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();
    Context exposeContext();


}
