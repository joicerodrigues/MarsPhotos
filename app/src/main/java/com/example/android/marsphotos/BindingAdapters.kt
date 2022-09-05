package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter


    @BindingAdapter("imageUrl") // instrui a vinculação de dados a executar esse adaptador de vinculação quando um item da visualização tiver o atributo imageUrl
    fun bindImage(
        imgView: ImageView,
        imgUrl: String?
    ) { // recebendo imageview e string como paramentros
        imgUrl?.let { //usando operador de chamada segura
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri){
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
            }
        }
    }

    @BindingAdapter("listData")
    fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
        val adapter = recyclerView.adapter as PhotoGridAdapter
        adapter.submitList(data)
    }
    @BindingAdapter("marsApiStatus")
    fun bindStatus(statusImageView: ImageView,
                   status: MarsApiStatus?) {
        when (status) {
            MarsApiStatus.LOADING -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.loading_animation)
            }
            MarsApiStatus.ERROR -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.ic_connection_error)
            }
            MarsApiStatus.DONE -> {
                statusImageView.visibility = View.GONE
            }

        }
    }




