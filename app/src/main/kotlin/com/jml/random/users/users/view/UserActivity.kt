package com.jml.random.users.users.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jml.random.users.R
import com.jml.random.users.common.extensions.*
import com.jml.random.users.common.view.BaseActivity
import com.jml.random.users.users.domain.model.User
import com.jml.random.users.users.view.vm.UserViewModel
import com.jml.random.users.users.view.vm.state.UserState
import kotlinx.android.synthetic.main.activity_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : BaseActivity() {


    private val viewModel: UserViewModel by viewModel()

    companion object {

        private const val EXTRA_ID: String = "UserActivity#EXTRA_ID"

        fun createIntent(context: Context, id: String): Intent {
            return Intent(context, UserActivity::class.java)
                .apply {
                    putExtra(EXTRA_ID, id)
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        initViews()
        initObservers()
    }


    private fun initViews() {

        /*
          - User gender.
   - User name and surname.
   - User location: street, city and state.
   - Registered date.
   - User email.
   - User picture.
         */
    }

    private fun initObservers() {

        val id = intent.getStringExtra(EXTRA_ID)
        viewModel.setUserId(id)
        viewModel.getUser()

        observeNonNull(viewModel.getUserLiveData(), ::handleUserState)
        observeNonNull(viewModel.getLoadingLiveData(), ::showProgress)
    }

    private fun showProgress(show: Boolean = true) {
        user_progress.visibleGone(show)
    }

    private fun handleUserState(state: UserState) {
        when (state) {
            is UserState.Data -> showUserInfo(state.user)
            is UserState.Error -> {
            }
        }
    }

    private fun showUserInfo(user: User) {

        user_head_image.loadCircleFromUrl(user.pictures.large)
        user_gender_text.text = user.gender
        user_full_name_text.text = user.getFullName()
        user_email_text.text = user.email
        user_register_text.text = user.registerDate

        user.location.let {
            "${it.street} \n${it.city} \n (${it.state})"
        }.let(user_address_text::setText)

        user_register_text.text = user.registerDate.formattedDate(FormatDates.DATE_FULL, FormatDates.DATE_MONTH_YEAR)

    }

}