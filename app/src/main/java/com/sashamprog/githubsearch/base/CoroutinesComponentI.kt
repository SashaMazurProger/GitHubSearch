package com.sashamprog.githubsearch.base

import kotlinx.coroutines.*

interface CoroutinesComponentI {

    val asyncJobs: MutableList<Job?>

    fun launchAsync(block: suspend CoroutineScope.() -> Unit): Job {
        val job: Job = GlobalScope.launch(Dispatchers.Main) { block() }
        asyncJobs.add(job)
        job.invokeOnCompletion { asyncJobs.remove(job) }
        return job
    }

    suspend fun <T> async(block: suspend CoroutineScope.() -> T): T =
        withContext(Dispatchers.Default) { block() }

    suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T): T = async(block)

    fun cancelAllAsync() {
        val iterator = asyncJobs.iterator()
        while (iterator.hasNext()) {
            iterator.next()?.cancel()
            iterator.remove()
        }
    }

}