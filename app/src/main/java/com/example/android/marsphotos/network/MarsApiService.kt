package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = //constante recebendo URL
    "https:///android-kotlin-fun-mars-server.appspot.com" // URL base do serviço da web

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder() // add o builder da retrofit para criar um obj da retrofit
    .addConverterFactory(MoshiConverterFactory.create(moshi)) // ScalarsConverterFactory aceita strings e outros tipos primitivos,
    .baseUrl(BASE_URL)
    .build() // criando obj da retrofit

interface MarsApiService {
    @GET("photos") // informando que é uma solicitação get
    suspend fun getPhotos(): List<MarsPhoto>// recebe a string de resposta do serviço da Web.
}

object MarsApi {
    val retrofitService: MarsApiService by lazy { //inicialização lenta
        retrofit.create(MarsApiService::class.java)
    }
}
