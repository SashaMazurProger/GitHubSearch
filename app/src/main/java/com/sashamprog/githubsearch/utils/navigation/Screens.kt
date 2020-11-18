package com.sashamprog.githubsearch.utils.navigation

import com.sashamprog.githubsearch.ui.fragment.search.FragmentSearch
import ru.terrakok.cicerone.android.support.SupportAppScreen


object Screens {

    object ScreenSearch : SupportAppScreen() {
        override fun getFragment() = FragmentSearch.getInstance()
    }
}