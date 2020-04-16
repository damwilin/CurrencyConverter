package com.wili.rcurrencyconverter

import android.app.Application
import com.wili.core.di.CoreComponent
import com.wili.core.di.DaggerCoreComponent
import com.wili.rcurrencyconverter.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent.create()
    }

    private fun initAppDependencyInjection() {
        DaggerAppComponent.builder()
            .application(this)
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }


    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}