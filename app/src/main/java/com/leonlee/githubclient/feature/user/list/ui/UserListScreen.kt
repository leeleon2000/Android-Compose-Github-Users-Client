/*
 * *
 *  * Created by Leon on 09/03/2023, 12:46
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 12:24
 *
 */


package com.leonlee.githubclient.feature.user.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.leonlee.githubclient.R
import com.leonlee.githubclient.feature.user.list.UserListViewModel
import com.leonlee.githubclient.feature.user.list.data.UserListModelItem
import com.leonlee.githubclient.ui.theme.GithubUserClientTheme


@Composable
fun UserListItem(modelItem: UserListModelItem, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(onClick = { onClick(modelItem.login) }),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = modelItem.avatarUrl,
                contentDescription = stringResource(R.string.avatar_image_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = modelItem.login,
                fontSize = 24.sp,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun UserListScreen(viewModel: UserListViewModel, onUserClicked: (String) -> (Unit)) {
    val listResult by viewModel.userList.collectAsState()
    val lists = listResult.getOrNull() ?: return
    LazyColumn {
        items(items = lists, key = { it.id }) {
            UserListItem(it) { name -> onUserClicked(name) }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserListScreenPreview() {
    GithubUserClientTheme {
        UserListItem(UserListModelItem(login = "test")) {}
    }
}
