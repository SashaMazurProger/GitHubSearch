package com.sashamprog.githubsearch.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sashamprog.githubsearch.BuildConfig
import com.sashamprog.githubsearch.data.provider.github.repository.GitHubApi
import com.sashamprog.githubsearch.data.provider.github.repository.GitHubRepository
import com.sashamprog.githubsearch.data.provider.github.repository.GitHubRepositoryI
import com.sashamprog.githubsearch.domain.user.GitHubInteractor
import com.sashamprog.githubsearch.domain.user.GitHubInteractorI
import com.sashamprog.githubsearch.ui.activity.SingleActivityI
import com.sashamprog.githubsearch.ui.activity.SingleActivityPresenter
import com.sashamprog.githubsearch.ui.fragment.search.SearchI
import com.sashamprog.githubsearch.ui.fragment.search.SearchPresenter
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    factory<SingleActivityI.Presenter> { SingleActivityPresenter(get()) }
    factory<SearchI.Presenter> { SearchPresenter(get(), get()) }
    single<GitHubRepositoryI>(createdAtStart = true) { GitHubRepository(get()) }
    factory<GitHubInteractorI> { GitHubInteractor(get()) }


    single<GitHubApi> {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(GitHubApi::class.java)
    }

}

// Gather all app modules
val listWithModules = listOf(appModule, navigationModule)