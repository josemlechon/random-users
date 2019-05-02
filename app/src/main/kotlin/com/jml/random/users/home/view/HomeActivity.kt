package com.jml.random.users.home.view

import android.os.Bundle
import android.text.InputType
import androidx.recyclerview.widget.LinearLayoutManager
import com.jml.random.users.R
import com.jml.random.users.common.domain.model.PaginationScroll
import com.jml.random.users.common.exceptions.ErrorType
import com.jml.random.users.common.extensions.*
import com.jml.random.users.common.view.BaseActivity
import com.jml.random.users.common.view.vm.state.EventState
import com.jml.random.users.common.view.widget.adapter.PaginationScrollListener
import com.jml.random.users.home.view.model.UserBriefUI
import com.jml.random.users.home.view.vm.HomeViewModel
import com.jml.random.users.home.view.vm.state.HomeState
import com.jml.random.users.home.view.widget.adapter.HomeUsersAdapter
import com.jml.random.users.users.view.UserActivity
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

        setSupportActionBar(home_user_toolbar)
        setToolbarTitle(R.string.activity_label_home)

        val linearLayout = LinearLayoutManager(this)

        val paginationListener = object : PaginationScrollListener(linearLayout) {
            override fun loadMoreItems() = viewModel.getMoreUsers()
            override fun getPagination(): PaginationScroll = viewModel.pagination
        }

        home_users_recycler.apply {
            adapter = HomeUsersAdapter().apply {
                doOnItemClick = (::onItemSelected)
                doDeleteUserClick = (::onItemDeleted)
            }
            setHasFixedSize(true)
            layoutManager = linearLayout
            addOnScrollListener(paginationListener)
        }

        home_users_no_data_messages.apply {
            setTitle(getString(R.string.home_user_no_result_title))
            setMessage(getString(R.string.home_user_no_result_message))
        }

        home_users_search_edit.onChangeDelayed(1000) { onFilterUsers(it) }
        home_users_search_edit.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
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
            is HomeState.FilteredData -> showFilteredUsers(state.search, state.users)
            is HomeState.Error -> showErrorGettingData(state.errorType)
            is HomeState.ErrorMoreData -> showErrorGettingMoreData(state.errorType)
        }
    }

    private fun onFilterUsers(search: String) {
        viewModel.filterUsers(search)
    }

    private fun showFilteredUsers(search: String, users: List<UserBriefUI>) {
        if (search.isNotEmpty() && home_users_search_edit.text.isNullOrEmpty()) {
            home_users_search_edit.setText(search)
        }

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
        val intent = UserActivity.createIntent(this, id)
        startActivity(intent)
    }

    private fun onItemDeleted(position: Int, id: String) {
        observeNonNull(viewModel.deleteUser(id)) {

            if (it == EventState.Success) {
                removeItemDeleted(position)
            } else {
                showErrorDialog(ErrorType.GENERIC, retry = { onItemDeleted(position, id) })
            }
        }
    }

    private fun removeItemDeleted(position: Int) {
        (home_users_recycler.adapter as HomeUsersAdapter)
            .apply {
                removeAt(position)
                showFilterEmptyMessage(itemCount == 0)
            }
    }

    private fun showErrorGettingData(errorType: ErrorType) {
        showErrorDialog(errorType, retry =  viewModel::requestUsers)
    }

    private fun showErrorGettingMoreData(errorType: ErrorType) {
        showErrorDialog(errorType)
    }
}
