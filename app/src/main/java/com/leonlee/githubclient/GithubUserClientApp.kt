/*
 * *
 *  * Created by Leon on 07/03/2023, 13:19
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 07/03/2023, 13:19
 *
 */

@file:OptIn(ExperimentalMaterial3Api::class)

package com.leonlee.githubclient

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leonlee.githubclient.feature.users.list.UserListScreen
import com.leonlee.githubclient.feature.users.list.UserListViewModel

enum class GitHubUserClientScreen(@StringRes val title: Int) {
    List(R.string.user_list),
    Detail(R.string.user_detail)
}

private val startScreen = GitHubUserClientScreen.List

@Composable
fun CupcakeAppBar(
    currentScreen: GitHubUserClientScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.app_name)
                    )
                }
            }
        }
    )
}

@Composable
fun GithubUserClientApp(
    userListViewModel: UserListViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GitHubUserClientScreen.valueOf(
        backStackEntry?.destination?.route ?: startScreen.name
    )

    Scaffold(
        topBar = {
            CupcakeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startScreen.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = GitHubUserClientScreen.List.name) {
                UserListScreen(userListViewModel)
            }
        }
    }
}
