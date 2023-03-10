/*
 * *
 *  * Created by Leon on 09/03/2023, 12:23
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 08/03/2023, 13:56
 *
 */

package com.leonlee.githubclient.feature.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonlee.githubclient.feature.user.UserRepository
import com.leonlee.githubclient.feature.user.list.data.UserListModelItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UserListViewState {
    object Loading : UserListViewState()
    object Error : UserListViewState()
    class SuccessList(val list: List<UserListModelItem>) : UserListViewState()
}

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _userListState = MutableStateFlow<UserListViewState>(UserListViewState.Loading)
    val userListState = _userListState.asStateFlow()

    fun start() = viewModelScope.launch {
        if(_userListState.value is UserListViewState.SuccessList){
            return@launch
        }
        _userListState.emit(UserListViewState.Loading)
        getUserLists()
    }

    private fun getUserLists() = viewModelScope.launch {
        userRepository.listUser().fold(
            onSuccess = {
                        _userListState.emit(UserListViewState.SuccessList(it))
            },
            onFailure = {
                _userListState.emit(UserListViewState.Error)
            })
    }
}
