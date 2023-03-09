/*
 * *
 *  * Created by Leon on 09/03/2023, 12:23
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 08/03/2023, 13:56
 *
 */

package com.leonlee.githubclient.feature.user

import com.leonlee.githubclient.feature.user.detail.data.UserDetailModel
import com.leonlee.githubclient.feature.user.list.data.UserListModelItem
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users")
    suspend fun listUser() : Result<List<UserListModelItem>>

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String) : Result<UserDetailModel>
}
