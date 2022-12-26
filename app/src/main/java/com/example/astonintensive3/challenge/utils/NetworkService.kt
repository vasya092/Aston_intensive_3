package com.example.astonintensive3.challenge.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.net.URL

object NetworkService {

    const val TAG_DOWNLOAD = "TAG_DOWNLOAD"

    fun downloadBitmap(imageUrl: String): Bitmap? {
        return try {
            val connection = URL(imageUrl).openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        } catch (e: Exception) {
            Log.e(TAG_DOWNLOAD, e.message.toString())
            null
        }
    }
}