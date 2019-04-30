package com.jml.random.users.home.view

import android.os.Bundle
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.jml.random.users.R
import com.jml.random.users.common.exceptions.ErrorType
import com.jml.random.users.common.extensions.observeNonNull
import com.jml.random.users.common.extensions.showErrorDialog
import com.jml.random.users.common.extensions.visibleGone
import com.jml.random.users.common.view.BaseActivity

import com.jml.random.users.home.view.vm.HomeViewModel
import com.jml.random.users.home.view.vm.state.HomeState
import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.home.view.widget.adapter.HomePagedListAdapter
import com.jml.random.users.users.domain.model.User
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

        home_users_recycler.apply {
            adapter = HomePagedListAdapter().apply {
                doOnItemClick = (::onItemSelected)
            }
            setHasFixedSize(true)
            layoutManager = linearLayout
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
            is HomeState.PaggedData -> showPagedData(state.paged)
            is HomeState.Data -> showUsersData(state.users)
            is HomeState.MoreData -> showMoreUsersData(state.users)
            is HomeState.RefreshUsers -> showNewUsersRefreshed(state.users)
            is HomeState.Error -> showErrorGettingData(state.errorType)
            is HomeState.ErrorMoreData -> {
            }
        }
    }

    private fun onRefreshListener() {
        viewModel.refreshUsers()
    }

    private fun showNewUsersRefreshed(users: List<UserBriefUI>) {
        home_users_refresh.isRefreshing = false
        showUsersData(users)
    }

    private fun showPagedData(paged: PagedList<User>) {


        (home_users_recycler.adapter as HomePagedListAdapter).submitList(paged)

    }

    private fun showUsersData(users: List<UserBriefUI>) {
//        showFilterEmptyMessage(users.isNotEmpty())

        //       (home_users_recycler.adapter as HomeUsersAdapter).setData(users)
    }

    private fun showMoreUsersData(users: List<UserBriefUI>) {
        //     (home_users_recycler.adapter as HomeUsersAdapter).addData(users)
    }

    private fun showFilterEmptyMessage(show: Boolean) {
        home_users_no_data_messages.visibleGone(show)
    }

    private fun onItemSelected(position: Int, id: String) {

    }

    private fun showErrorGettingData(errorType: ErrorType) {
        showErrorDialog(errorType)
    }
}
