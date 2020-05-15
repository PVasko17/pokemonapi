package com.vasko.pokemonapi.di

import android.app.Application
import com.vasko.pokemonapi.di.component.ApplicationComponent
import com.vasko.pokemonapi.di.component.DaggerApplicationComponent

enum class Injector private constructor() {
    INSTANCE;

    lateinit var applicationComponent: ApplicationComponent
        internal set

    fun initAppComponent(application: Application) {
        applicationComponent = DaggerApplicationComponent.builder()
            .application(application)
            .build()
    }
}