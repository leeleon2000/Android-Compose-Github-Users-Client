/*
 * *
 *  * Created by Leon on 09/03/2023, 10:58
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 10:54
 *
 */

package com.leonlee.githubclient

import android.app.Application
import com.leonlee.githubclient.AppModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/*
 * *
 *  * Created by Leon on 09/03/2023, 10:53
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 09/03/2023, 10:53
 *
 */

class MainApp: Application() {
    override fun onCreate() {
        startKoin {
            androidLogger()
            modules(AppModule)
        }
        super.onCreate()
    }
}