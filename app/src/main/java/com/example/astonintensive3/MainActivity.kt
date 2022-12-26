package com.example.astonintensive3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.astonintensive3.challenge.ui.ChallengeActivity
import com.example.astonintensive3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            flagsButton.setOnClickListener {
                val intent = Intent(this@MainActivity, FlagsActivity::class.java)
                startActivity(intent)
            }
            imageLoadButton.setOnClickListener {
                val intent = Intent(this@MainActivity, ImageLoaderActivity::class.java)
                startActivity(intent)
            }

            challengeButton.setOnClickListener {
                val intent = Intent(this@MainActivity, ChallengeActivity::class.java)
                startActivity(intent)
            }

        }
    }
}