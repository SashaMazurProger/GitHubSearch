package com.sashamprog.githubsearch.base

/**
 * Presenter
 */
interface MvpPresenterI<V> {

    fun onAttach(mvpView: V)
    fun onDetach()
    fun closeApp()

}