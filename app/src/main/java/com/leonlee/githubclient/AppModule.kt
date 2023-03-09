/*
 * *
 *  * Created by Leon on 09/03/2023, 10:54
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 10:54
 *
 */

package com.leonlee.githubclient

import com.leonlee.githubclient.common.network.NetworkConfig
import com.leonlee.githubclient.feature.users.UserRepository
import com.leonlee.githubclient.feature.users.UserService
import com.leonlee.githubclient.feature.users.list.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val AppModule = module {
    single { NetworkConfig.createRetrofit() }
    single { get<Retrofit>().create(UserService::class.java) }
    single { UserRepository(get()) }
    viewModelOf(::UserListViewModel)
}
