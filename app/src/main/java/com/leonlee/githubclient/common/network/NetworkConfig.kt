/*
 * *
 *  * Created by Leon on 07/03/2023, 07:55
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 07/03/2023, 07:55
 *
 */

package com.leonlee.githubclient.common.network

import kotlinx.serialization.json.Json

class NetworkConfig {
    companion object{
        val json = Json {
            ignoreUnknownKeys = true
        }
    }
}
