package com.example.owner.nearplace.di.components;

import com.example.owner.nearplace.di.module.NearbyPlaceModule;
import com.example.owner.nearplace.di.scope.PerActivity;
import com.example.owner.nearplace.modules.home.MainActivity;

import dagger.Component;

@PerActivity
@Component(modules ={NearbyPlaceModule.class}, dependencies = ApplicationComponent.class)
public interface NearbyPlaceComponent {

    void inject(MainActivity activity);

}
