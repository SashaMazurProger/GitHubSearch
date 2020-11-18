package com.sashamprog.githubsearch.domain.user

import com.sashamprog.githubsearch.base.BaseInteractor
import com.sashamprog.githubsearch.data.provider.github.entity.SearchRepositoryResult
import com.sashamprog.githubsearch.data.provider.github.repository.GitHubRepositoryI
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
class GitHubInteractor(
    private val mRepository: GitHubRepositoryI
) : BaseInteractor(), GitHubInteractorI {

    override suspend fun searchRepositories(query: String): SearchRepositoryResult? =
        coroutineScope {
            return@coroutineScope awaitAll(
                GlobalScope.async { mRepository.searchRepositories(query, 15, 1) },
                GlobalScope.async { mRepository.searchRepositories(query, 15, 2) },
            ).let { results ->
                if (results.size == 2 && results.none { it == null }) {
                    SearchRepositoryResult(
                        results[0]!!.items.plus(results[1]!!.items)
                    )
                } else {
                    null
                }
            }
        }
}