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

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _userList = MutableStateFlow<Result<List<UserListModelItem>>>(Result.success(listOf()))
    val userList = _userList.asStateFlow()

    fun start(){
        getUserLists()
    }
    private fun getUserLists() {
        viewModelScope.launch {
            _userList.emit(userRepository.listUser())
        }
    }
}
