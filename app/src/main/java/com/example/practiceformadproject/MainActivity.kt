package com.example.practiceformadproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstAct = findViewById<Button>(R.id.btnAddMedicines)
        firstAct.setOnClickListener{
            val intent = Intent(this,AddMedicine::class.java)
            startActivity(intent)
        }

        val secondAct = findViewById<Button>(R.id.btnViewMedicines)
        secondAct.setOnClickListener{
            val intent = Intent(this,ViewAllMedicines::class.java)
            startActivity(intent)
        }

        val thirdAct = findViewById<Button>(R.id.btnViewCart)
        thirdAct.setOnClickListener{
            val intent = Intent(this,CartMainActivity::class.java)
            startActivity(intent)
        }

        val forthAct = findViewById<Button>(R.id.btnPharmacyView)
        forthAct.setOnClickListener{
            val intent = Intent(this,ServiceProviderRecyclerView::class.java)
            startActivity(intent)
        }

        val fifthAct = findViewById<Button>(R.id.btntoLogin)
        fifthAct.setOnClickListener{
            val intent = Intent(this,ServiceProviderRecyclerView::class.java)
            startActivity(intent)
        }

        val sixthAct = findViewById<Button>(R.id.toComplaints)
        sixthAct.setOnClickListener{
            val intent = Intent(this,DisplayComp::class.java)
            startActivity(intent)
        }


    }


}