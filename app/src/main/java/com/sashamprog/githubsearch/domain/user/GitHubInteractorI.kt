package com.sashamprog.githubsearch.domain.user

import com.sashamprog.githubsearch.base.InteractorI
import com.sashamprog.githubsearch.data.provider.github.entity.SearchRepositoryResult

interface GitHubInteractorI : InteractorI {

    suspend fun searchRepositories(query: String): SearchRepositoryResult?
}