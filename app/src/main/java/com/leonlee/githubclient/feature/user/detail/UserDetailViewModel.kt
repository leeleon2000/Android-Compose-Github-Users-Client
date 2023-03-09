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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _userDetail = MutableStateFlow(Result.success(UserDetailModel()))
    val userDetail = _userDetail.asStateFlow()

    fun getUserDetail(name: String){
        viewModelScope.launch {
            _userDetail.emit(userRepository.getUser(name))
        }
    }
}
