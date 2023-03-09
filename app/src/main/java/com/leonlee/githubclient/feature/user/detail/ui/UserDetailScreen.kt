/*
 * *
 *  * Created by Leon on 09/03/2023, 12:46
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 12:46
 *
 */

package com.leonlee.githubclient.feature.user.detail.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.leonlee.githubclient.R
import com.leonlee.githubclient.feature.user.detail.UserDetailViewModel
import com.leonlee.githubclient.feature.user.detail.data.UserDetailModel
import com.leonlee.githubclient.ui.theme.GithubUserClientTheme

@Composable
fun UserDetailScreen(viewModel: UserDetailViewModel) {
    val userDetailResult by viewModel.userDetail.collectAsState()
    val userDetail = userDetailResult.getOrNull() ?: return
    Column(modifier = Modifier.padding(24.dp)) {
        UserPhotoView(userDetail)
        Spacer(modifier = Modifier.size(32.dp))
        UserDetailTextView(image = Icons.Rounded.Email, text = userDetail.email)
        UserDetailTextView(image = Icons.Rounded.Web, text = userDetail.blog)
        UserDetailTextView(image = Icons.Rounded.Place, text = userDetail.location)
        UserDetailTextView(image = Icons.Rounded.Business, text = userDetail.company)
        Spacer(modifier = Modifier.size(4.dp))
        if(userDetail.followers != null && userDetail.following != null) {
            Row {
                Text(text = userDetail.followers.toString() + " " + stringResource(R.string.followers))
                Text(text = ", " + userDetail.following.toString() + " " + stringResource(R.string.following))
            }
        }
        if(userDetail.publicRepos != null){
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = userDetail.publicRepos.toString() + " " + stringResource(R.string.repos))
        }
    }
}


@Composable
fun UserDetailTextView(image: ImageVector, text: String?) {
    if(!text.isNullOrEmpty()) {
        Spacer(modifier = Modifier.size(4.dp))
        Row {
            Icon(image, stringResource(R.string.icon) + text)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = text)
        }
    }
}

@Composable
fun UserPhotoView(userDetail: UserDetailModel) {
    Row() {
        AsyncImage(
            model = userDetail.avatarUrl,
            contentDescription = stringResource(R.string.avatar_image_desc),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(32.dp))
        )
        Spacer(modifier = Modifier.size(24.dp))
        Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
            Text(
                text = userDetail.login,
                fontSize = 28.sp,
            )
            if (!userDetail.bio.isNullOrEmpty()) {
                Text(
                    text = userDetail.bio ?: "",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UserDetailPreview() {
    GithubUserClientTheme {
        UserPhotoView(userDetail = UserDetailModel(login = "TestLogin", bio = "TestBio"))
    }
}