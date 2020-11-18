package com.sashamprog.githubsearch.data.provider.github.entity


data class SearchRepositoryResult(
    val items: List<Item>,
) {
    data class Item(val fullName: String)
}