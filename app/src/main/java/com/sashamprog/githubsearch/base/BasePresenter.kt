package com.sashamprog.githubsearch.base

import kotlinx.coroutines.Job
import org.koin.core.KoinComponent
import ru.terrakok.cicerone.Router


abstract class BasePresenter<V>(protected val router: Router) : MvpPresenterI<V>, KoinComponent,
    CoroutinesComponentI {

    override val asyncJobs: MutableList<Job?> = mutableListOf()

    var mvpView: V? = null
        private set

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun closeApp() = router.finishChain()

    override fun onDetach() {
        cancelAllAsync()
        mvpView = null
    }
}