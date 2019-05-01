package com.jml.random.users.common.domain.model


class PaginationScroll(
    var noMorePages: Boolean = false,
    var loading: Boolean = false
) {

    fun checkMorePages(morePages: Boolean) {
        noMorePages = !morePages
    }

    fun resetPager() {
        noMorePages = true
        loading = false
    }


}
