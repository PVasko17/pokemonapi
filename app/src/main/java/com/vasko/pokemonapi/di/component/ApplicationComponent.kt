package com.vasko.pokemonapi.di.component

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.vasko.pokemonapi.data.repository.PokemonRepository
import com.vasko.pokemonapi.di.ViewModelModule
import com.vasko.pokemonapi.di.module.ApplicationModule
import com.vasko.pokemonapi.di.module.NetworkModule
import com.vasko.pokemonapi.di.module.ServiceModule
import dagger.BindsInstance
import dagger.Component
import java.io.File
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class, ServiceModule::class])
interface ApplicationComponent {

    fun context(): Context

    @Named("cache")
    fun provideCacheDir(): File

    fun provideResources(): Resources

    fun provideCurrentLocale(): Locale

    fun provideViewModelFactory(): ViewModelProvider.Factory

    fun providePokemonRepository(): PokemonRepository

    @Component.Builder
    interface ApplicationComponentBuilder {
        @BindsInstance
        fun application(application: Application): ApplicationComponentBuilder

        fun build(): ApplicationComponent
    }
}