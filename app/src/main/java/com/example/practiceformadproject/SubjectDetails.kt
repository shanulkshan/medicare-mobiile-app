package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.practiceformadproject.ComplaintsModel
import com.example.practiceformadproject.R
import com.google.firebase.database.FirebaseDatabase

class SubjectDetails : AppCompatActivity() {

    private lateinit var tvcompId: TextView
    private lateinit var tvName: TextView
    private lateinit var tvMail: TextView
    private lateinit var tvSubName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateComp(
                intent.getStringExtra("compId").toString(),
                intent.getStringExtra("subject").toString()
            )
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("compId").toString()
            )
        }
    }

    private fun deleteRecord(
        compId: String
    ){
        val dbCon = FirebaseDatabase.getInstance().getReference("Complaints").child(compId)
        val mTask = dbCon.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Complaint deleted",Toast.LENGTH_LONG).show()
            val intent = Intent(this,DisplayComp::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this,"Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvcompId = findViewById(R.id.tvcompId)
        tvName = findViewById(R.id.tvName)
        tvMail = findViewById(R.id.tvMail)
        tvSubName = findViewById(R.id.tvSubName)
        tvDescription = findViewById(R.id.tvDescription)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }

    private fun setValuesToViews(){
        tvcompId.text = intent.getStringExtra("compId")
        tvName.text = intent.getStringExtra("name")
        tvMail.text = intent.getStringExtra("mail")
        tvSubName.text = intent.getStringExtra("subject")
        tvDescription.text = intent.getStringExtra("description")

    }

    private fun openUpdateComp(
        compId: String,
        subject: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etName = mDialogView.findViewById<EditText>(R.id.etName)
        val etMail = mDialogView.findViewById<EditText>(R.id.etMail)
        val etSubject = mDialogView.findViewById<EditText>(R.id.etSubject)
        val etDescrip = mDialogView.findViewById<EditText>(R.id.etDescrip)
        val btnUpdate = mDialogView.findViewById<Button>(R.id.UpdBtn)

        etName.setText(intent.getStringExtra("name").toString())
        etMail.setText(intent.getStringExtra("mail").toString())
        etSubject.setText(intent.getStringExtra("subject").toString())
        etDescrip.setText(intent.getStringExtra("description").toString())

        mDialog.setTitle("Updating $subject Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener{
            updateCompData(
                compId,
                etName.text.toString(),
                etMail.text.toString(),
                etSubject.text.toString(),
                etDescrip.text.toString()
            )

            Toast.makeText(applicationContext,"Updated!", Toast.LENGTH_LONG).show()
            tvName.text = etName.text.toString()
            tvMail.text = etMail.text.toString()
            tvSubName.text = etSubject.text.toString()
            tvDescription.text = etDescrip.text.toString()

            alertDialog.dismiss()
        }


    }
    private fun updateCompData(
        compId: String,
        name:String,
        email:String,
        subject:String,
        description:String

    ){
        val dbCon = FirebaseDatabase.getInstance().getReference("Complaints").child(compId)
        val compInfo = ComplaintsModel(compId, name, email, subject, description)
        dbCon.setValue(compInfo)
    }
}