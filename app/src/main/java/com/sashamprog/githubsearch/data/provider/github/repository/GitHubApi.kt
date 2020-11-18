package com.sashamprog.githubsearch.data.provider.github.repository

import com.sashamprog.githubsearch.data.provider.github.entity.SearchRepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitHubApi {

    @Headers("Authorization: token 4ad085cb1a2c09626f746fa9fb3145401bc0acae")
    @GET("search/repositories?sort=stars&order=desc")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Call<SearchRepositoryResponse>
}
