package com.sashamprog.githubsearch

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.sashamprog.githubsearch.di.listWithModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            androidContext(this@MyApp)
            modules(listWithModules)
        }
    }

    companion object {
        lateinit var context: Context

        // for pre lollipop
        // allow to use drawableLeft/Right
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

}
