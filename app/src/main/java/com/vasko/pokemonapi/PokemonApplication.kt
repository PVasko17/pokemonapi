package com.vasko.pokemonapi

import android.app.Application
import com.vasko.pokemonapi.di.Injector

class PokemonApplication: Application() {
    override fun onCreate() {
        super.onCreate()

//        Initialising Dagger's application component
        Injector.INSTANCE.initAppComponent(this)
    }
}