package com.sashamprog.githubsearch.ui.fragment.search

import com.sashamprog.githubsearch.base.MvpPresenterI
import com.sashamprog.githubsearch.base.MvpViewI
import com.sashamprog.githubsearch.data.provider.github.entity.SearchRepositoryResult

interface SearchI {

    interface Presenter : MvpPresenterI<View> {
        fun search(query: String)
    }

    interface View : MvpViewI<Presenter> {
        fun showSearchResults(items: List<SearchRepositoryResult.Item>)
        fun showProgress(visible: Boolean)
        fun showList(visible: Boolean)
    }
}





