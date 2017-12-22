package com.headysample.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by nndra on 06-Dec-17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {



}
