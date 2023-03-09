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
import retrofit2.Retrofit

class NetworkConfig {
    companion object {
        const val HOST = "https://api.github.com"
        val JSON_MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()
        val JSON = Json {
            ignoreUnknownKeys = true
        }
        fun createRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl(HOST)
                .addConverterFactory(NetworkConfig.JSON.asConverterFactory(JSON_MEDIA_TYPE))
                .addCallAdapterFactory(ResultCallAdapterFactory())
                .build()
        }
    }
}
