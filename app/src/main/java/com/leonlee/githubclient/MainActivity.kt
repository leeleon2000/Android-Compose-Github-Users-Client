package com.leonlee.githubclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.leonlee.githubclient.common.network.NetworkConfig
import com.leonlee.githubclient.common.network.ResultCallAdapterFactory
import com.leonlee.githubclient.feature.users.UserRepository
import com.leonlee.githubclient.feature.users.UserService
import com.leonlee.githubclient.feature.users.list.UserListViewModel
import com.leonlee.githubclient.ui.theme.GithubUserClientTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

val contentType = "application/json".toMediaType()
private val json = Json {
    ignoreUnknownKeys = true
}


@Serializable
data class User(val name: String?)


class MainActivity : ComponentActivity() {

    lateinit var userListViewModel: UserListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com")
            .addConverterFactory(NetworkConfig.json.asConverterFactory("application/json; charset=utf-8".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
        val service = retrofit.create(UserService::class.java)
        userListViewModel = UserListViewModel(UserRepository(service))

        setContent {
            GithubUserClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    GithubUserClientApp(userListViewModel)
                }
            }
        }
    }
}