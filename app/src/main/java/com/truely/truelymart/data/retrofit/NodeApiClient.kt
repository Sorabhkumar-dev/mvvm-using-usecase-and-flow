package com.truely.truelymart.data.retrofit

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.truely.truelymart.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NodeApiClient {
    private var gson: Gson

    init {
        gson = GsonBuilder().create()
    }

    private var sNodeApiInterface: NodeApiInterface = client.create(
        NodeApiInterface::class.java
    )

    @JvmStatic
    val nodeApiInterface: NodeApiInterface
        get() {
            return sNodeApiInterface
        }

    private//Read Interceptor
// Buffer the entire body.
    val client: Retrofit
        get() {
            val okHttpBuilder = OkHttpClient.Builder()


            okHttpBuilder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )

            okHttpBuilder.connectTimeout(20, TimeUnit.SECONDS)
            okHttpBuilder.readTimeout(20, TimeUnit.SECONDS)
            okHttpBuilder.writeTimeout(20, TimeUnit.SECONDS)

            return Retrofit.Builder()
                .baseUrl(Constant.homeBaseUrl)
                .addCallAdapterFactory(FlowCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpBuilder.build())
                .build()
        }
}