package com.vasko.pokemonapi.di.module

import android.util.Log
import com.vasko.pokemonapi.BuildConfig
import com.vasko.pokemonapi.data.URLFactory
import com.vasko.pokemonapi.exception.ServerError
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(ApplicationModule::class)])
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 3

        return OkHttpClient.Builder()
//            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .dispatcher(dispatcher)
//            .addNetworkInterceptor(networkInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(URLFactory.provideHttpUrl())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("pre_validation")
    internal fun provideNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val code = response.code()
            if (code >= 500)
                if (BuildConfig.DEBUG)
                    Log.e("response", response.body()!!.string())
                else
                    throw ServerError(
                        "Unknown server error. Error Code :$code",
                        response.body()!!.string()
                    )
            response
        }
    }
}