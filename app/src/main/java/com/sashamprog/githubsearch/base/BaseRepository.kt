package com.sashamprog.githubsearch.base

import kotlinx.coroutines.Job

abstract class BaseRepository : RepositoryI, CoroutinesComponentI {

    override val asyncJobs: MutableList<Job?> = mutableListOf()

    override fun release() {
        cancelAllAsync()
    }
}