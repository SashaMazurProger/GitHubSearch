package com.sashamprog.githubsearch.data.provider.github.repository

import com.sashamprog.githubsearch.base.RepositoryI
import com.sashamprog.githubsearch.data.provider.github.entity.SearchRepositoryResult

interface GitHubRepositoryI : RepositoryI {
    suspend fun searchRepositories(query: String, perPage: Int, page: Int): SearchRepositoryResult?
}