/*
 * *
 *  * Created by Leon on 09/03/2023, 12:24
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 12:21
 *
 */

package com.leonlee.githubclient.feature.user.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonlee.githubclient.feature.user.UserRepository
import com.leonlee.githubclient.feature.user.detail.data.UserDetailModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class UserDetailViewState {
    object Loading : UserDetailViewState()
    object Error : UserDetailViewState()
    class Success(val userDetail: UserDetailModel) : UserDetailViewState()
}

class UserDetailViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userDetailState =
        MutableStateFlow<UserDetailViewState>(UserDetailViewState.Loading)
    val userDetail = _userDetailState.asStateFlow()

    fun getUserDetail(name: String) = viewModelScope.launch {
        if((_userDetailState.value as? UserDetailViewState.Success)?.userDetail?.name == name){
            return@launch
        }
        _userDetailState.emit(UserDetailViewState.Loading)
        userRepository.getUser(name).fold(
            onSuccess = {
                _userDetailState.emit(UserDetailViewState.Success(it))
            }, onFailure = {
                _userDetailState.emit(UserDetailViewState.Error)
            })
    }
}
