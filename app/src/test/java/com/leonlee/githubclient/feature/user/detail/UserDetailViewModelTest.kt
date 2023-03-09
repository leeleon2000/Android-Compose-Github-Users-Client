/*
 * *
 *  * Created by Leon on 09/03/2023, 16:35
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 16:35
 *
 */

@file:OptIn(ExperimentalCoroutinesApi::class)

package com.leonlee.githubclient.feature.user.detail

import com.leonlee.githubclient.feature.user.UserRepository
import com.leonlee.githubclient.feature.user.detail.data.UserDetailModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserDetailViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val repo = mockk<UserRepository>()
    private val viewModel = UserDetailViewModel(repo)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun GetUserDetail_Success() = runTest {
        coEvery { repo.getUser("test") } returns Result.success(
            UserDetailModel(
                login = "test",
                avatarUrl = "testimage"
            )
        )
        viewModel.getUserDetail("test")
        advanceUntilIdle()
        assertTrue(viewModel.userDetail.value.isSuccess)
        assertEquals("test", viewModel.userDetail.value.getOrThrow().login)
        assertEquals("testimage", viewModel.userDetail.value.getOrThrow().avatarUrl)
    }

}