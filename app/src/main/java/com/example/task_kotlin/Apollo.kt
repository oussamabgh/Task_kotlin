package com.example.task_kotlin

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient

//val apolloClient = ApolloClient.Builder()
  //  .serverUrl("https://countries.trevorblades.com/graphql")
    //.build()

private var instance: ApolloClient? = null

fun apolloClient(context: Context): ApolloClient {
    if (instance != null) {
        return instance!!
    }

    val okHttpClient = OkHttpClient.Builder()
        .build()

    instance = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .okHttpClient(okHttpClient)
        .build()

    return instance!!
}

