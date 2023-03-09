/*
 * *
 *  * Created by Leon on 09/03/2023, 17:36
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 17:36
 *
 */

@file:OptIn(ExperimentalCoroutinesApi::class)

package com.leonlee.githubclient.feature.user.list

import com.leonlee.githubclient.feature.user.UserRepository
import com.leonlee.githubclient.feature.user.detail.UserDetailViewModel
import com.leonlee.githubclient.feature.user.detail.data.UserDetailModel
import com.leonlee.githubclient.feature.user.list.data.UserListModelItem
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.math.log

class UserListViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val repo = mockk<UserRepository>()
    private val viewModel = UserListViewModel(repo)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun GetUserList_Success() = runTest {
        coEvery { repo.listUser() } returns Result.success(
            listOf(UserListModelItem(avatarUrl = "testImage", login = "test"), UserListModelItem())
        )
        viewModel.start()
        advanceUntilIdle()
        TestCase.assertTrue(viewModel.userList.value.isSuccess)
        TestCase.assertEquals(2, viewModel.userList.value.getOrThrow().size)
        TestCase.assertEquals("testImage", viewModel.userList.value.getOrThrow()[0].avatarUrl)
    }

}