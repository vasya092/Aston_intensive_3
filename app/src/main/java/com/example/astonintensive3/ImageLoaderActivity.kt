package com.example.astonintensive3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.webkit.URLUtil
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.astonintensive3.databinding.ActivityImageLoaderBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class ImageLoaderActivity : AppCompatActivity() {

    private var _binding: ActivityImageLoaderBinding? = null
    private val binding: ActivityImageLoaderBinding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.urlEditField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                urlEditFieldChanged(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun urlEditFieldChanged(url: String) {
        if (URLUtil.isValidUrl(url)) {
            Picasso.get().load(url)
                .placeholder(R.drawable.anim_circle)
                .error(R.drawable.ic_error_s)
                .resize(IMAGE_WIDTH, IMAGE_HEIGHT)
                .onlyScaleDown()
                .into(binding.imageView, object : Callback {
                    override fun onSuccess() {}

                    override fun onError(e: Exception?) {
                        Toast.makeText(this@ImageLoaderActivity,
                            getString(R.string.picasso_exception_message, e?.message.toString()),
                            Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    companion object {
        const val IMAGE_WIDTH = 800
        const val IMAGE_HEIGHT = 800
    }
}