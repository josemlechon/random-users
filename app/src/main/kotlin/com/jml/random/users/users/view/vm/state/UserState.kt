package com.jml.random.users.users.view.vm.state

import com.jml.random.users.common.exceptions.ErrorType
import com.jml.random.users.users.domain.model.User

sealed class UserState {
    class Data(val user: User) : UserState()
    class Error(val error: ErrorType) : UserState()
}