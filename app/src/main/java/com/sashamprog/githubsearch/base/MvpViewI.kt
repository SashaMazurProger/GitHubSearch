package com.sashamprog.githubsearch.base

import androidx.annotation.StringRes

/**
 * View
 */
interface MvpViewI<out T : MvpPresenterI<*>> {

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    val presenter: T
}