/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // O MutableLiveData interno que armazena o status da solicitação mais recente
    private val _status = MutableLiveData<String>()

    // O LiveData externo imutável para o status da solicitação
    val status: LiveData<String> = _status
    /**
     * Chamando getMarsPhotos() no init para que possamos exibir o status imediatamente.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Obtém informações de fotos da Mars do serviço Mars API Retrofit e atualiza o
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {
        //_status.value = "Set the Mars API status response here!"
//        viewModelScope.launch {
//            val listResult = MarsApiService.MarsApi.retrofitService.getPhotos()
//            _status.value = listResult
//        }

        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}
