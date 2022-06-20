package com.picpay.desafio.android

import androidx.lifecycle.*
import com.picpay.desafio.android.data.local.database.entities.UserEntity
import com.picpay.desafio.android.data.utils.DataState
import com.picpay.desafio.android.domain.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userRepository: UserUseCases,
) : ViewModel() {

    private val _dataStateUsers: MutableLiveData<DataState<List<UserEntity>?>> =
        MutableLiveData()
    val dataStateUsers: LiveData<DataState<List<UserEntity>?>>
        get() = _dataStateUsers

    fun setStateEventUsers(userStateEvent: UserStateEvent) {
        viewModelScope.launch {
            when (userStateEvent) {
                is UserStateEvent.getUsersEvent -> {
                    userRepository.getUsers().onEach {
                        _dataStateUsers.value = it
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class UserStateEvent {
    object getUsersEvent : UserStateEvent()
}
