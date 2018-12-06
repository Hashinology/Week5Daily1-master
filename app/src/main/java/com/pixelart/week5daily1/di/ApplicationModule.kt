package com.pixelart.week5daily1.di

import android.app.Activity
import android.databinding.DataBindingUtil
import com.pixelart.week5daily1.R
import com.pixelart.week5daily1.base.BaseActivity
import com.pixelart.week5daily1.databinding.ActivityMainBinding
import com.pixelart.week5daily1.remote.RemoteService
import com.pixelart.week5daily1.ui.mainscreen.MainActivity
import com.pixelart.week5daily1.ui.mainscreen.MainScreenContract
import com.pixelart.week5daily1.ui.mainscreen.MainScreenPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class ApplicationModule(private val baseActivity: BaseActivity<*>) {
    @Provides
    fun providesMainContractPresenter(remoteService: RemoteService):MainScreenContract.Presenter = MainScreenPresenter(baseActivity as MainActivity, remoteService)

    @Provides
    fun funProvidesMainScreenBindin():ActivityMainBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_main)
}