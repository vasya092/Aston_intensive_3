package com.example.astonintensive3.challenge.ui

import android.graphics.Bitmap
import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astonintensive3.challenge.data.ImageLoadStatus
import com.example.astonintensive3.challenge.utils.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageLoaderViewModel : ViewModel() {
    private val _bitmapFile = MutableLiveData<Bitmap>()
    val bitmapFile: LiveData<Bitmap> = _bitmapFile

    private val _status = MutableLiveData<ImageLoadStatus>()
    val status: LiveData<ImageLoadStatus> = _status

    fun updateBitmap(url: String) {
        if (URLUtil.isValidUrl(url)) {
            _status.value = ImageLoadStatus.LOADING
            CoroutineScope(Dispatchers.IO).launch {
                val bitmap = NetworkService.downloadBitmap(url)
                withContext(Dispatchers.Main) {
                    if (bitmap != null) {
                        _bitmapFile.value = bitmap
                        _status.value = ImageLoadStatus.DONE
                    } else {
                        _status.value = ImageLoadStatus.ERROR
                        _bitmapFile.value = null
                    }
                }
            }
        }
    }
}