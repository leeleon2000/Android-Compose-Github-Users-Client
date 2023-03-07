/*
 * *
 *  * Created by Leon on 06/03/2023, 09:44
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 06/03/2023, 09:44
 *
 */

package com.leonlee.githubclient.common.network

import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != Call::class.java) {
                return null
            }

            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            if (getRawType(callType) != Result::class.java) {
                return null
            }

            val resultType = getParameterUpperBound(0, callType as ParameterizedType)
            return ResultCallAdapter(resultType)
        }
}

class ResultCallAdapter(
    private val resultType: Type
) : CallAdapter<Type, Call<Result<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<Result<Type>> {
        return ResultCall(call)
    }
}

class ResultCall<T : Any>(
    private val proxy: Call<T>
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(Result.success(it))
                    )
                } ?: run {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(Result.failure(Exception("${response.code()} ${response.message()}")))
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(this@ResultCall, Response.success(Result.failure(t)))
            }
        })
    }

    override fun execute(): Response<Result<T>> = throw NotImplementedError()
    override fun clone(): Call<Result<T>> = ResultCall(proxy.clone())
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() {
        proxy.cancel()
    }
}
