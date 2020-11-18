package com.sashamprog.githubsearch.data.provider.github.repository

import com.sashamprog.githubsearch.base.BaseRepository
import com.sashamprog.githubsearch.data.provider.github.entity.SearchRepositoryResult

class GitHubRepository(
    private val mApi: GitHubApi
) : BaseRepository(), GitHubRepositoryI {

    override suspend fun searchRepositories(
        query: String,
        perPage: Int,
        page: Int
    ): SearchRepositoryResult? {
        return try {
            mApi.searchRepositories(query, perPage, page)
                .execute()
                .body()
                ?.let { body ->
                    SearchRepositoryResult(
                        body.items.map { item -> SearchRepositoryResult.Item(item.fullName) }
                    )
                }
        } catch (e: Exception) {
            null
        }
    }
}