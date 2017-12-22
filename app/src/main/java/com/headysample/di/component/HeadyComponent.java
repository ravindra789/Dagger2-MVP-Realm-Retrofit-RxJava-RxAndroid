package com.headysample.di.component;

import com.headysample.di.module.HeadyModule;
import com.headysample.di.scope.PerActivity;
import com.headysample.view.activities.MainActivity;

import dagger.Component;
import dagger.Provides;

/**
 * Created by nndra on 06-Dec-17.
 */
@PerActivity
@Component(modules = HeadyModule.class,dependencies = ApplicationComponent.class)
public interface HeadyComponent {

    void inject(MainActivity activity);

}
