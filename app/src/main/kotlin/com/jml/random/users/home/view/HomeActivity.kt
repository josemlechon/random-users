package com.jml.random.users.home.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jml.random.users.R
import com.jml.random.users.common.domain.model.PaginationScroll
import com.jml.random.users.common.exceptions.ErrorType
import com.jml.random.users.common.extensions.observeNonNull
import com.jml.random.users.common.extensions.showErrorDialog
import com.jml.random.users.common.extensions.visibleGone
import com.jml.random.users.common.view.BaseActivity
import com.jml.random.users.common.view.widget.adapter.PaginationScrollListener

import com.jml.random.users.home.view.vm.HomeViewModel
import com.jml.random.users.home.view.vm.state.HomeState
import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.home.view.widget.adapter.HomeUsersAdapter
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        subscribeObservers()
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

        home_users_no_data_messages.apply {
            setTitle(getString(R.string.home_user_no_result_title))
            setMessage(getString(R.string.home_user_no_result_message))
        }
    }

    private fun subscribeObservers() {
        observeNonNull(viewModel.getLoadingLiveData(), ::showProgress)
        observeNonNull(viewModel.getHomeStateLiveData(), ::handleHomeState)
    }

    private fun showProgress(status: Boolean? = true) {
        home_progress.visibleGone(status)
    }

    private fun handleHomeState(state: HomeState) {
        when (state) {
            is HomeState.Data -> showUsersData(state.users)
            is HomeState.AddData -> showMoreUsersData(state.users)
            is HomeState.RefreshUsers -> showNewUsersRefreshed(state.users)
            is HomeState.Error -> showErrorGettingData(state.errorType)
            is HomeState.ErrorMoreData -> showErrorGettingMoreData(state.errorType)
        }
    }

    private fun onRefreshListener() {
        viewModel.refreshUsers()
    }

    private fun showNewUsersRefreshed(users: List<UserBriefUI>) {
        home_users_refresh.isRefreshing = false
        showUsersData(users)
    }

    private fun showUsersData(users: List<UserBriefUI>) {
        showFilterEmptyMessage(users.isEmpty())
        (home_users_recycler.adapter as HomeUsersAdapter).setData(users)
    }

    private fun showMoreUsersData(users: List<UserBriefUI>) {
        (home_users_recycler.adapter as HomeUsersAdapter).addData(users)
    }

    private fun showFilterEmptyMessage(show: Boolean) {
        home_users_no_data_messages.visibleGone(show)
    }

    private fun onItemSelected(position: Int, id: String) {

    }

    private fun showErrorGettingData(errorType: ErrorType) {
        showErrorDialog(errorType)
    }

    private fun showErrorGettingMoreData(errorType: ErrorType) {
        showErrorDialog(errorType)
    }
}
