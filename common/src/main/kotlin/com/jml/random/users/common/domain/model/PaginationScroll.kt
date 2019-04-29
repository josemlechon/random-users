package com.jml.random.users.common.domain.model


class PaginationScroll(var currentPage: Int = INITIAL_PAGE,
                         var noMorePages: Boolean = false,
                         var loading: Boolean = false,
                         var numItemsPerPage : Int = NUM_ITEMS_PER_PAGE) {

    companion object {
        const val NUM_ITEMS_PER_PAGE: Int = 10
        const val INITIAL_PAGE : Int = 1
    }

    /** returns the next page whoever it does not confirm it */
    fun getPossibleNextPage() = currentPage + 1

    /** Confirm the current page, it is called after the data has successfully been downloaded */
    fun confirmCurrentPage() {
        currentPage += 1
    }

    fun checkMorePages(activities: List<*>) {
        noMorePages = activities.size != numItemsPerPage
    }

    fun resetPager() {
        currentPage = INITIAL_PAGE
        loading = false
    }

}
