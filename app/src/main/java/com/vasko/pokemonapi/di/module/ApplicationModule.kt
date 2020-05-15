package com.vasko.pokemonapi.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.io.File
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Named("cache")
    internal fun provideCacheDir(application: Application): File {
        return application.cacheDir
    }

    @Provides
    @Singleton
    internal fun provideResources(application: Application): Resources {
        return application.resources
    }

    @Provides
    @Singleton
    internal fun provideCurrentLocale(resources: Resources): Locale {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resources.configuration.locales.get(0)
        } else {
            resources.configuration.locale
        }
    }

    @Provides
    @Singleton
    internal fun provideApplicationContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return Gson()
    }
}