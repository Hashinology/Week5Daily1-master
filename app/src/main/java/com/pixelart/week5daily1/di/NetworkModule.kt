package com.pixelart.week5daily1.di

import android.app.Activity
import android.databinding.DataBindingUtil
import com.pixelart.week5daily1.R
import com.pixelart.week5daily1.base.BaseActivity
import com.pixelart.week5daily1.common.USERS_BASE_URL
import com.pixelart.week5daily1.databinding.ActivityMainBinding
import com.pixelart.week5daily1.remote.RemoteService
import com.pixelart.week5daily1.ui.mainscreen.MainActivity
import com.pixelart.week5daily1.ui.mainscreen.MainScreenContract
import com.pixelart.week5daily1.ui.mainscreen.MainScreenPresenter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule{

    @Provides
    fun providesRemoteService(retrofit: Retrofit):RemoteService{
        return retrofit.create(RemoteService::class.java)
    }

    @Provides
    fun providesUserRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(USERS_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun providesOkHttpClient():OkHttpClient{
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}