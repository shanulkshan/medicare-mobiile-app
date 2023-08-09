package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
//import com.example.myapplication1.MainActivity
//import com.example.myapplication1.R


class FirstPage : AppCompatActivity() {

    private lateinit var btnInsertData: Button
    private lateinit var btnFetchData: Button
    private lateinit var btnInsertDelivery : Button
    private lateinit var btnOrderSummary : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)

        btnInsertData = findViewById(R.id.btnInsertData)
        btnFetchData = findViewById(R.id.btnFetchData)
        btnInsertDelivery = findViewById(R.id.btnInsertDelivery)
        btnOrderSummary = findViewById(R.id.btnOrderSummary)

        btnInsertData.setOnClickListener {
            val intent = Intent(this, PaymentDetails::class.java)
            startActivity(intent)
        }

        /*btnFetchData.setOnClickListener {
            val intent = Intent(this, PaymentFetching::class.java)
            startActivity(intent)
        }

        btnInsertDelivery.setOnClickListener {
            val intent = Intent(this, DeliveryDetails::class.java)
            startActivity(intent)
        }

        btnOrderSummary.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/

    }
}
























