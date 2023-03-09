package com.leonlee.githubclient

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.leonlee.githubclient.common.network.NetworkConfig
import com.leonlee.githubclient.common.network.ResultCallAdapterFactory
import com.leonlee.githubclient.feature.users.UserRepository
import com.leonlee.githubclient.feature.users.UserService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalSerializationApi::class, ExperimentalCoroutinesApi::class)
class UserRepositoryTests {
    private lateinit var server: MockWebServer
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        server = MockWebServer()
        val retrofit = Retrofit.Builder().baseUrl(server.url("/"))
            .addConverterFactory(NetworkConfig.JSON.asConverterFactory("application/json; charset=utf-8".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
        val service = retrofit.create(UserService::class.java)
        userRepository = UserRepository(service)
    }

    @Test
    fun GetUser_Success() = runTest {
        val response =
            """{"login":"github","id":9919,"node_id":"MDEyOk9yZ2FuaXphdGlvbjk5MTk=","avatar_url":"https://avatars.githubusercontent.com/u/9919?v=4","gravatar_id":"","url":"https://api.github.com/users/github","html_url":"https://github.com/github","followers_url":"https://api.github.com/users/github/followers","following_url":"https://api.github.com/users/github/following{/other_user}","gists_url":"https://api.github.com/users/github/gists{/gist_id}","starred_url":"https://api.github.com/users/github/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/github/subscriptions","organizations_url":"https://api.github.com/users/github/orgs","repos_url":"https://api.github.com/users/github/repos","events_url":"https://api.github.com/users/github/events{/privacy}","received_events_url":"https://api.github.com/users/github/received_events","type":"Organization","site_admin":false,"name":"GitHub","company":null,"blog":"https://github.com/about","location":"San Francisco, CA","email":null,"hireable":null,"bio":"How people build software.","twitter_username":null,"public_repos":458,"public_gists":0,"followers":18256,"following":0,"created_at":"2008-05-11T04:37:31Z","updated_at":"2022-11-29T19:44:55Z"}"""
        server.enqueue(MockResponse().setBody(response))
        val user = userRepository.getUser("github")
        assertTrue(user.isSuccess)
        assertEquals(user.getOrThrow().id, 9919)
        assertEquals(user.getOrThrow().login, "github")
    }

    @Test
    fun ListUser_Success() = runTest {
        val response =
            """[{"login":"mojombo","id":1,"node_id":"MDQ6VXNlcjE=","avatar_url":"https://avatars.githubusercontent.com/u/1?v=4","gravatar_id":"","url":"https://api.github.com/users/mojombo","html_url":"https://github.com/mojombo","followers_url":"https://api.github.com/users/mojombo/followers","following_url":"https://api.github.com/users/mojombo/following{/other_user}","gists_url":"https://api.github.com/users/mojombo/gists{/gist_id}","starred_url":"https://api.github.com/users/mojombo/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/mojombo/subscriptions","organizations_url":"https://api.github.com/users/mojombo/orgs","repos_url":"https://api.github.com/users/mojombo/repos","events_url":"https://api.github.com/users/mojombo/events{/privacy}","received_events_url":"https://api.github.com/users/mojombo/received_events","type":"User","site_admin":false},{"login":"defunkt","id":2,"node_id":"MDQ6VXNlcjI=","avatar_url":"https://avatars.githubusercontent.com/u/2?v=4","gravatar_id":"","url":"https://api.github.com/users/defunkt","html_url":"https://github.com/defunkt","followers_url":"https://api.github.com/users/defunkt/followers","following_url":"https://api.github.com/users/defunkt/following{/other_user}","gists_url":"https://api.github.com/users/defunkt/gists{/gist_id}","starred_url":"https://api.github.com/users/defunkt/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/defunkt/subscriptions","organizations_url":"https://api.github.com/users/defunkt/orgs","repos_url":"https://api.github.com/users/defunkt/repos","events_url":"https://api.github.com/users/defunkt/events{/privacy}","received_events_url":"https://api.github.com/users/defunkt/received_events","type":"User","site_admin":false},{"login":"pjhyett","id":3,"node_id":"MDQ6VXNlcjM=","avatar_url":"https://avatars.githubusercontent.com/u/3?v=4","gravatar_id":"","url":"https://api.github.com/users/pjhyett","html_url":"https://github.com/pjhyett","followers_url":"https://api.github.com/users/pjhyett/followers","following_url":"https://api.github.com/users/pjhyett/following{/other_user}","gists_url":"https://api.github.com/users/pjhyett/gists{/gist_id}","starred_url":"https://api.github.com/users/pjhyett/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/pjhyett/subscriptions","organizations_url":"https://api.github.com/users/pjhyett/orgs","repos_url":"https://api.github.com/users/pjhyett/repos","events_url":"https://api.github.com/users/pjhyett/events{/privacy}","received_events_url":"https://api.github.com/users/pjhyett/received_events","type":"User","site_admin":false},{"login":"wycats","id":4,"node_id":"MDQ6VXNlcjQ=","avatar_url":"https://avatars.githubusercontent.com/u/4?v=4","gravatar_id":"","url":"https://api.github.com/users/wycats","html_url":"https://github.com/wycats","followers_url":"https://api.github.com/users/wycats/followers","following_url":"https://api.github.com/users/wycats/following{/other_user}","gists_url":"https://api.github.com/users/wycats/gists{/gist_id}","starred_url":"https://api.github.com/users/wycats/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/wycats/subscriptions","organizations_url":"https://api.github.com/users/wycats/orgs","repos_url":"https://api.github.com/users/wycats/repos","events_url":"https://api.github.com/users/wycats/events{/privacy}","received_events_url":"https://api.github.com/users/wycats/received_events","type":"User","site_admin":false},{"login":"ezmobius","id":5,"node_id":"MDQ6VXNlcjU=","avatar_url":"https://avatars.githubusercontent.com/u/5?v=4","gravatar_id":"","url":"https://api.github.com/users/ezmobius","html_url":"https://github.com/ezmobius","followers_url":"https://api.github.com/users/ezmobius/followers","following_url":"https://api.github.com/users/ezmobius/following{/other_user}","gists_url":"https://api.github.com/users/ezmobius/gists{/gist_id}","starred_url":"https://api.github.com/users/ezmobius/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/ezmobius/subscriptions","organizations_url":"https://api.github.com/users/ezmobius/orgs","repos_url":"https://api.github.com/users/ezmobius/repos","events_url":"https://api.github.com/users/ezmobius/events{/privacy}","received_events_url":"https://api.github.com/users/ezmobius/received_events","type":"User","site_admin":false}]"""
        server.enqueue(MockResponse().setBody(response))
        val users = userRepository.listUser()
        assertTrue(users.isSuccess)
        assertEquals(5, users.getOrThrow().size)
        assertEquals(1, users.getOrThrow()[0].id)
        assertEquals("ezmobius", users.getOrThrow()[4].login)
    }
}
