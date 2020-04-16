package com.wili.rcurrencyconverter.di

import android.app.Application
import com.wili.core.di.AppScope
import com.wili.core.di.CoreComponent
import com.wili.rcurrencyconverter.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [AndroidInjectionModule::class, ViewModelModule::class, MainActivityModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }
}