package com.example.practiceformadproject

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practiceformadproject.ComplaintsModel
import com.example.practiceformadproject.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SugHome : AppCompatActivity() {

    private lateinit var edtName : EditText
    private lateinit var edtmail : EditText
    private lateinit var edtsub : EditText
    private lateinit var edtdescrip : EditText
    private lateinit var edtButton : Button

    private lateinit var dbCon: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sug_and_com_home)

        edtName = findViewById(R.id.etName)
        edtmail = findViewById(R.id.etMail)
        edtsub = findViewById(R.id.etSubject)
        edtdescrip = findViewById(R.id.etDescrip)
        edtButton = findViewById(R.id.UpdBtn)

        dbCon = FirebaseDatabase.getInstance().getReference("Complaints")

        edtButton.setOnClickListener {
            saveComplianitsData()
        }

    }

    private fun saveComplianitsData(){

        val name = edtName.text.toString()
        val mail = edtmail.text.toString()
        val subject = edtsub.text.toString()
        val description = edtdescrip.text.toString()

        if (name.isEmpty()){
            edtName.error = "Name can't be empty!"
        }
        if (mail.isEmpty()) {
            edtmail.error = "Email Can't be empty !"

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(edtmail.text.toString()).matches()) {
            edtmail.setError("Invalid Email Address !")
            edtmail.requestFocus()

        }
        if (subject.isEmpty()) {
            edtsub.error = "Subject Can't be empty !"

        }
        if (description.isEmpty()) {
            edtdescrip.error = "Description Can't be empty !"

        }

        val compId = dbCon.push().key!!

        val complaints = ComplaintsModel(compId,name,mail,subject,description)

        dbCon.child(compId).setValue(complaints)
            .addOnCompleteListener{
                Toast.makeText(this,"Submitted successfully!",Toast.LENGTH_LONG).show()

                edtName.text.clear()
                edtmail.text.clear()
                 edtsub.text.clear()
                 edtdescrip.text.clear()

                val intent = Intent(this,DisplayComp::class.java)
                startActivity(intent)

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }


    }
}