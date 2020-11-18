package com.sashamprog.githubsearch.di


import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

val navigationModule = module(createdAtStart = true) {
    single { provideRouter() }
    single { provideNavigatorHolder() }
}


private val cicerone: Cicerone<Router> = Cicerone.create()

fun provideRouter(): Router {
    return cicerone.router
}

fun provideNavigatorHolder(): NavigatorHolder {
    return cicerone.navigatorHolder
}