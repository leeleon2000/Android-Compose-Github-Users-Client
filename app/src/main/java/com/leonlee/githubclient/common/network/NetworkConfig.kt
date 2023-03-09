/*
 * *
 *  * Created by Leon on 07/03/2023, 07:55
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 07/03/2023, 07:55
 *
 */

package com.leonlee.githubclient.common.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.addHeaderLenient
import retrofit2.Retrofit


class NetworkConfig {
    companion object {
        const val HOST = "https://api.github.com"
        val JSON_MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()
        val JSON = Json {
            ignoreUnknownKeys = true
        }
        val httpClient = OkHttpClient.Builder().addNetworkInterceptor {chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.header("User-Agent", "request")
            return@addNetworkInterceptor chain.proceed(requestBuilder.build())
        }.build()
        fun createRetrofit(client: OkHttpClient = httpClient): Retrofit {
            return Retrofit.Builder().baseUrl(HOST)
                .addConverterFactory(NetworkConfig.JSON.asConverterFactory(JSON_MEDIA_TYPE))
                .addCallAdapterFactory(ResultCallAdapterFactory())
                .client(client)
                .build()
        }
    }
}
