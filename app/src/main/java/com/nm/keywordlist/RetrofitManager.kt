package com.nm.keywordlist

import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.*
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null


    val service: ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }


    private fun addLoggingInterceptor(): Interceptor {
        val loggingInterceptor = LoggingInterceptor.Builder()
        loggingInterceptor.loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("Request")
            .response("Response")
            .addHeader("version", BuildConfig.VERSION_NAME)
            .addQueryParam("query", "0")

        return loggingInterceptor.build()
    }


    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    client = OkHttpClient.Builder()
                        .addInterceptor(addLoggingInterceptor())
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build()

                    //val customConverterFactory = GsonConverterFactory.create()
                    // Fix Api return Gson wrong format - Ignore some field wrong return
                    val gson = GsonBuilder()
                        .create()
                    val converter = GsonConverterFactory.create(gson)
                    retrofit = Retrofit.Builder()
                        .baseUrl("https://raw.githubusercontent.com/")
                        .client(client!!)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(converter)
                        .build()

                }
            }
        }

        return retrofit!!
    }
}

