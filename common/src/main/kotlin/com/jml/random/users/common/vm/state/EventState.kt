package  com.jml.random.users.common.vm.state

import com.jml.random.users.common.exceptions.ErrorType

sealed class EventState {
    object Success : EventState()
    class Error(val error: ErrorType) : EventState()
}