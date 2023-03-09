/*
 * *
 *  * Created by Leon on 09/03/2023, 13:27
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 12:24
 *
 */

package com.leonlee.githubclient

import com.leonlee.githubclient.common.network.NetworkConfig
import com.leonlee.githubclient.feature.user.UserRepository
import com.leonlee.githubclient.feature.user.UserService
import com.leonlee.githubclient.feature.user.list.UserListViewModel
import com.leonlee.githubclient.feature.user.detail.UserDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val AppModule = module {
    single { NetworkConfig.createRetrofit() }
    single { get<Retrofit>().create(UserService::class.java) }
    single { UserRepository(get()) }
    viewModelOf(::UserListViewModel)
    viewModelOf(::UserDetailViewModel)
}
