package com.jml.random.users.home.vm

import com.jml.random.users.common.vm.BaseViewModel
import com.jml.random.users.users.domain.usecases.GetUser

class HomeViewModel(
    private val getUsers : GetUser
)  : BaseViewModel(){


}