package com.sashamprog.githubsearch.base

import kotlinx.coroutines.Job

abstract class BaseInteractor : InteractorI, CoroutinesComponentI {

    override val asyncJobs: MutableList<Job?> = mutableListOf()

    override fun release() {
        cancelAllAsync()
    }
}