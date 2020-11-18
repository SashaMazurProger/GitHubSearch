package com.sashamprog.githubsearch.ui.fragment.search

import com.sashamprog.githubsearch.base.BasePresenter
import com.sashamprog.githubsearch.domain.user.GitHubInteractorI
import kotlinx.coroutines.Job
import ru.terrakok.cicerone.Router


class SearchPresenter(
    private val mInteractor: GitHubInteractorI,
    router: Router
) : BasePresenter<SearchI.View>(router),
    SearchI.Presenter {

    private var mSearchJob: Job? = null

    override fun search(query: String) {
        mSearchJob?.cancel()
        mvpView?.showProgress(true)
        mvpView?.showList(false)
        mSearchJob = launchAsync {
            val data = asyncAwait { mInteractor.searchRepositories(query) }
            mvpView?.showProgress(false)
            mvpView?.showList(true)
            mvpView?.showSearchResults(data?.items ?: listOf())
        }
    }
}