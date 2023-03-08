/*
 * *
 *  * Created by Leon on 07/03/2023, 13:57
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 07/03/2023, 13:57
 *
 */


package com.leonlee.githubclient.feature.users.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.leonlee.githubclient.R
import com.leonlee.githubclient.feature.users.list.model.UserListModelItem
import com.leonlee.githubclient.ui.theme.GithubUserClientTheme


@Composable
fun UserListItem(modelItem: UserListModelItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { },
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = modelItem.avatarUrl,
                contentDescription = stringResource(R.string.avatar_image_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = modelItem.login, fontSize = 28.sp)
        }
    }
}

@Composable
fun UserListScreen(viewModel: UserListViewModel) {
    val listResult by viewModel.userList.collectAsState()
    val lists = listResult.getOrNull() ?: return
    LazyColumn {
        items(items = lists, key = { it.id }) {
            UserListItem(it)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserListScreenPreview() {
    GithubUserClientTheme {
        UserListItem(UserListModelItem())
    }
}
