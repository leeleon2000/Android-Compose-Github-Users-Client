/*
 * *
 *  * Created by Leon on 09/03/2023, 12:23
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 12:23
 *
 */

package com.leonlee.githubclient.feature.user

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val userService: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getUser(name: String) = withContext(dispatcher) {
        return@withContext userService.getUser(name)
    }

    suspend fun listUser() = withContext(dispatcher) {
        return@withContext userService.listUser()
    }
}