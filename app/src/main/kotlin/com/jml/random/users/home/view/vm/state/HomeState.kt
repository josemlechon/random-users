package com.jml.random.users.home.view.vm.state

import com.jml.random.users.common.exceptions.ErrorType
import com.jml.random.users.home.view.model.UserBriefUI

sealed class HomeState {
    class RefreshUsers(val users: List<UserBriefUI>) : HomeState()
    class Data(val users: List<UserBriefUI>) : HomeState()
    class MoreData(val users  : List <UserBriefUI>) : HomeState()
    class Error(val errorType: ErrorType) : HomeState()
    class ErrorMoreData(val errorType: ErrorType) : HomeState()

}