package com.jml.random.users.home.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jml.random.users.R
import com.jml.random.users.common.domain.model.PaginationScroll
import com.jml.random.users.common.view.widget.adapter.PaginationScrollListener
import com.jml.random.users.home.view.widget.adapter.HomeUsersAdapter
import com.jml.random.users.home.vm.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        iniViews()
    }

    private fun iniViews() {

        val linearLayout = LinearLayoutManager(this)

        val paginationListener = object : PaginationScrollListener(linearLayout) {
            override fun loadMoreItems() = viewModel.getMoreUsers()
            override fun getPagination(): PaginationScroll = viewModel.pagination
        }

        home_users_recycler.apply {
            adapter = HomeUsersAdapter().apply {
                doOnItemClick = (::onItemSelected)
            }
            setHasFixedSize(true)
            layoutManager = linearLayout
            addOnScrollListener(paginationListener)
        }

        home_users_refresh.setOnRefreshListener(::onRefreshListener)
    }

    private fun onRefreshListener() {

    }

    private fun onItemSelected(position: Int, id: String) {

    }
}
