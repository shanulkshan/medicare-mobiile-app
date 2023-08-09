package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import com.example.myapplication1.R
//import com.example.myapplication1.models.PaymentModel
//import com.example.myapplication1.models.ReceiverModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PaymentDetails : AppCompatActivity() {

    private lateinit var etCardNumber: EditText
    private lateinit var etCardHolderName: EditText
    private lateinit var etMonthYear: EditText
    private lateinit var btnSave:Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

        etCardNumber = findViewById(R.id.cardNo)
        etCardHolderName = findViewById(R.id.holderName)
        etMonthYear = findViewById(R.id.expDate)
        btnSave = findViewById(R.id.button6)

        dbRef = FirebaseDatabase.getInstance().getReference("Payments")



        //val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnSave.setOnClickListener {
            savePaymentDetails()
        }


    }
    private fun savePaymentDetails(){
        //getting values
        val cardNo = etCardNumber.text.toString()
        val holderName = etCardHolderName.text.toString()
        val expDate = etMonthYear.text.toString()


        if(cardNo.isEmpty()){
            etCardNumber.error = "Please Card Number"
        }
        if(holderName.isEmpty()){
            etCardHolderName.error = "Please Enter Card Holder Name"
        }
        if(expDate.isEmpty()){
            etMonthYear.error = "Please Enter Expire date"
        }

        val payId = dbRef.push().key!!

        val payment = PaymentModel(payId, cardNo , holderName, expDate)

        dbRef.child(payId).setValue(payment)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()

                etCardNumber.text.clear()
                etCardHolderName.text.clear()
                etMonthYear.text.clear()


            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()

            }

        /*val secondAct = findViewById<Button>(R.id.button6)
        secondAct.setOnClickListener{
            val intent = Intent(this,PaymentFetching::class.java)
            startActivity(intent)
        }*/
    }
}