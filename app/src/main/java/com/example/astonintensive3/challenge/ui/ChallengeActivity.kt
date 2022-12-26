package com.example.astonintensive3.challenge.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.astonintensive3.R
import com.example.astonintensive3.challenge.data.ImageLoadStatus
import com.example.astonintensive3.databinding.ActivityChallengeBinding

class ChallengeActivity : AppCompatActivity() {

    private var _binding: ActivityChallengeBinding? = null
    private val binding: ActivityChallengeBinding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[ImageLoaderViewModel::class.java]
        _binding = ActivityChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadButton.setOnClickListener {
            viewModel.updateBitmap(binding.urlEditField.text.toString())
        }

        viewModel.status.observe(this) { status ->
            when (status) {
                ImageLoadStatus.DONE -> {
                    onImageLoadDone(viewModel.bitmapFile.value)
                }
                ImageLoadStatus.ERROR -> {
                    onImageLoadError()
                }
                ImageLoadStatus.LOADING -> {
                    buttonShowLoading()
                }
                else -> {}
            }
        }
    }

    private fun buttonShowLoading() {
        with(binding) {
            loadButton.text = getString(R.string.loading_string)
            loadButton.isEnabled = false
        }
    }

    private fun buttonHideLoading() {
        with(binding) {
            loadButton.text = getString(R.string.challenge_button_load_string)
            loadButton.isEnabled = true
        }
    }

    private fun onImageLoadDone(bitmap: Bitmap?) {
        binding.imageView.setImageBitmap(bitmap)
        buttonHideLoading()
    }

    private fun onImageLoadError() {
        binding.imageView.setImageResource(R.drawable.ic_error_s)
        Toast.makeText(this@ChallengeActivity,
            getString(R.string.image_load_error_string),
            Toast.LENGTH_SHORT).show()
        buttonHideLoading()
    }
}