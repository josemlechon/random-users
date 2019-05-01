package com.jml.random.users.common.domain.model


class PaginationScroll(
    var noMorePages: Boolean = false,
    var loading: Boolean = false,
    var filtering: Boolean = false
) {

    fun checkMorePages(morePages: Boolean) {
        noMorePages = !morePages
    }

    fun resetPager() {
        noMorePages = true
        loading = false
        filtering = false
    }


}
