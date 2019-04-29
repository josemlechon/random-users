package com.jml.random.users.common.view.widget.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jml.random.users.common.domain.model.PaginationScroll


abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        getPagination()
            .takeIf { !it.loading && !it.noMorePages }
            ?.takeIf { (visibleItemCount + firstVisibleItemPosition) >= totalItemCount }
            ?.takeIf { firstVisibleItemPosition >= 0 }
            ?.takeIf { totalItemCount >= it.numItemsPerPage }
            ?.let { loadMoreItems() }
    }

    protected abstract fun loadMoreItems()

    abstract fun getPagination(): PaginationScroll
}