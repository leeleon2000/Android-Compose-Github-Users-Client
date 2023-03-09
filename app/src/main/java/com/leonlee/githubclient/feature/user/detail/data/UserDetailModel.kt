/*
 * *
 *  * Created by Leon on 09/03/2023, 12:24
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 08/03/2023, 13:56
 *
 */

package com.leonlee.githubclient.feature.user.detail.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailModel(
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("blog")
    val blog: String? = null,
    @SerialName("company")
    val company: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("events_url")
    val eventsUrl: String? = null,
    @SerialName("followers")
    val followers: Int? = null,
    @SerialName("followers_url")
    val followersUrl: String? = null,
    @SerialName("following")
    val following: Int? = null,
    @SerialName("following_url")
    val followingUrl: String? = null,
    @SerialName("gists_url")
    val gistsUrl: String? = null,
    @SerialName("gravatar_id")
    val gravatarId: String? = null,
    @SerialName("hireable")
    val hireable: String? = null,
    @SerialName("html_url")
    val htmlUrl: String? = null,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("location")
    val location: String? = null,
    @SerialName("login")
    val login: String = "",
    @SerialName("name")
    val name: String? = null,
    @SerialName("node_id")
    val nodeId: String? = null,
    @SerialName("organizations_url")
    val organizationsUrl: String? = null,
    @SerialName("public_gists")
    val publicGists: Int? = null,
    @SerialName("public_repos")
    val publicRepos: Int? = null,
    @SerialName("received_events_url")
    val receivedEventsUrl: String? = null,
    @SerialName("repos_url")
    val reposUrl: String? = null,
    @SerialName("site_admin")
    val siteAdmin: Boolean? = null,
    @SerialName("starred_url")
    val starredUrl: String? = null,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String? = null,
    @SerialName("twitter_username")
    val twitterUsername: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    @SerialName("url")
    val url: String? = null
)