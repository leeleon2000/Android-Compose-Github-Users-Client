/*
 * *
 *  * Created by Leon on 07/03/2023, 12:14
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 07/03/2023, 12:14
 *
 */

package com.leonlee.githubclient.feature.users.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonlee.githubclient.feature.users.UserRepository
import com.leonlee.githubclient.feature.users.list.model.UserListModelItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _userList = MutableStateFlow<Result<List<UserListModelItem>>>(Result.success(listOf()))
    val userList = _userList.asStateFlow()

    init {
        getUserLists()
    }

    private fun getUserLists() {
        viewModelScope.launch {
            _userList.emit(userRepository.listUser())
        }
    }
}
