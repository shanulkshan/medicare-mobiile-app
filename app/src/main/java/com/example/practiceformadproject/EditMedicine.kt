package com.example.practiceformadproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class EditMedicine : AppCompatActivity() {
    private lateinit var backIcon: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_medicine)

        //Initialize Imageview
        backIcon = findViewById(R.id.backArrow)

        //Navigate to All medicine recycle view after clicking back arrow
        backIcon.setOnClickListener{
            val intent = Intent(this, ServiceProviderRecyclerView::class.java)
            startActivity(intent)
        }
    }
}