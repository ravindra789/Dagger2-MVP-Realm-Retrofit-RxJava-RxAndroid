package com.headysample.di.module;

import com.headysample.di.scope.PerActivity;
import com.headysample.util.RestApi;
import com.headysample.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by nndra on 06-Dec-17.
 */
@Module
public class HeadyModule {

    private MainView mView;

    public HeadyModule(MainView mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    RestApi provideApiService(Retrofit retrofit){
        return retrofit.create(RestApi.class);
    }

    @PerActivity
    @Provides
    MainView provideView(){
        return mView;
    }

}
