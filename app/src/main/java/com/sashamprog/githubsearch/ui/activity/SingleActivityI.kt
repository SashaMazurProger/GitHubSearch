package com.sashamprog.githubsearch.ui.activity

import com.sashamprog.githubsearch.base.MvpPresenterI
import com.sashamprog.githubsearch.base.MvpViewI

interface SingleActivityI {

    interface Presenter : MvpPresenterI<View> {
        fun navigateScreens()
    }

    interface View : MvpViewI<Presenter>

}




