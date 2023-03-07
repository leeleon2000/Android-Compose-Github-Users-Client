/*
 * *
 *  * Created by Leon on 06/03/2023, 09:31
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 06/03/2023, 09:31
 *
 */

package com.leonlee.githubclient.feature.users

import com.leonlee.githubclient.feature.users.detail.model.UserDetailModel
import com.leonlee.githubclient.feature.users.list.model.UserListModelItem
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users")
    suspend fun listUser() : Result<List<UserListModelItem>>

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String) : Result<UserDetailModel>
}
