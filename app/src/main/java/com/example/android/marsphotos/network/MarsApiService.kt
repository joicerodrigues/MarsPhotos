package com.example.android.marsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = //constante recebendo URL
    "https:///android-kotlin-fun-mars-server.appspot.com" // URL base do serviço da web

private val retrofit = Retrofit.Builder() // add o builder da retrofit para criar um obj da retrofit
    .addConverterFactory(ScalarsConverterFactory.create()) // ScalarsConverterFactory aceita strings e outros tipos primitivos,
    .baseUrl(BASE_URL)
    .build() // criando obj da retrofit

interface MarsApiService {
    @GET("photos") // informando que é uma solicitação get
    suspend fun getPhotos(): String// recebe a string de resposta do serviço da Web.
}

object MarsApi {
    val retrofitService: MarsApiService by lazy { //inicialização lenta
        retrofit.create(MarsApiService::class.java)
    }
}
