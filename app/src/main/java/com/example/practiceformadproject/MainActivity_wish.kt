package com.example.practiceformadproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practiceformadproject.databinding.ActivityMainWishBinding
import com.example.wishlist.UploadActivity


class MainActivity_wish : AppCompatActivity() {

    private lateinit var binding: ActivityMainWishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
    }
}