package com.example.owner.nearplace.di.components;

import com.example.owner.nearplace.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Retrofit exposeRetrofit();

}
