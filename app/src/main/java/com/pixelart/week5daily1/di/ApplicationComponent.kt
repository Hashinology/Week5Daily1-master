package com.pixelart.week5daily1.di

import com.pixelart.week5daily1.ui.mainscreen.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun injectMainScreen(mainScreen: MainActivity)
}