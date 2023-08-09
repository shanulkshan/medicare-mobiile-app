package com.example.practiceformadproject

import android.annotation.SuppressLint
import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddMedicine : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var edDrugName: EditText
    private lateinit var edDescription: EditText
    private lateinit var edManufactureName: EditText
    private lateinit var edExpiryDate: EditText
    private lateinit var edManufactureDate: EditText
    private lateinit var edCategory: EditText
    private lateinit var edPricePerUnit: EditText
    private lateinit var edQuantity: EditText
    private lateinit var backIcon: ImageView
    private lateinit var btnAdd: Button

    private lateinit var firebase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)

        edDrugName = findViewById(R.id.idDrugName)
        edDescription = findViewById(R.id.idDescription)
        edManufactureName = findViewById(R.id.idManufactureName)
        edManufactureDate = findViewById(R.id.idManufactureDate)
        edExpiryDate = findViewById(R.id.idExpiryDare)
        edCategory = findViewById(R.id.idCategory)
        edPricePerUnit = findViewById(R.id.idUnitPrice)
        edQuantity = findViewById(R.id.idQnt)
        backIcon = findViewById(R.id.backArrowIcon)
        btnAdd = findViewById(R.id.btnAdd)

        firebase = FirebaseDatabase.getInstance().getReference("medicines")

        btnAdd.setOnClickListener {
            saveMedicineData()
        }

        backIcon.setOnClickListener{
            val intent = Intent(this,ServiceProviderRecyclerView::class.java)
            startActivity(intent)
        }





//        Intent(this,MainActivity2::class.java)

    }

    private fun saveMedicineData() {
        //Getting values
        val drugName = edDrugName.text.toString()
        val description = edDescription.text.toString()
        val manufactureName = edManufactureName.text.toString()
        val manufactureDate = edManufactureDate.text.toString()
        val expiryDate = edExpiryDate.text.toString()
        val category = edCategory.text.toString()
        val pricePerUnit = edPricePerUnit.text.toString()
        val quantity = edQuantity.text.toString()

        //FrontEnd Validations?
        if(drugName.isEmpty() || manufactureName.isEmpty() || description.isEmpty() || manufactureDate.isEmpty() || expiryDate.isEmpty() || category.isEmpty() || pricePerUnit.isEmpty() || quantity.isEmpty()){
            if(drugName.isEmpty()){
                edDrugName.error = "Please enter drug name"
            }
            if(description.isEmpty()){
                edDescription.error = "Please enter drug description"
            }
            if(manufactureName.isEmpty()){
                edManufactureName.error = "Please enter manufacturer name"
            }
            if(manufactureDate.isEmpty()){
                edManufactureDate.error = "Please enter manufacture date"
            }
            if(expiryDate.isEmpty()){
                edExpiryDate.error = "Please enter expire date"
            }
            if(category.isEmpty()){
                edCategory.error = "Please enter category"
            }
            if(pricePerUnit.isEmpty()){
                edPricePerUnit.error = "Please enter unit price"
            }
            if(quantity.isEmpty()){
                edQuantity.error = "Please enter quantity"
            }
        }
        else{
            val medId = firebase.push().key!!

            var medicine = MedicineModel(medId,drugName, description, manufactureName,manufactureDate,expiryDate,category,pricePerUnit,quantity)

            firebase.child(medId).setValue(medicine)
                .addOnCompleteListener {
                    Toast.makeText(this,"Data Added Successfully", Toast.LENGTH_LONG).show()

                    edDrugName.text.clear()
                    edDescription.text.clear()
                    edManufactureName.text.clear()
                    edManufactureDate.text.clear()
                    edExpiryDate.text.clear()
                    edCategory.text.clear()
                    edPricePerUnit.text.clear()
                    edQuantity.text.clear()

                }.addOnFailureListener { err ->
                    Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
                }

            val intent = Intent(this,ServiceProviderRecyclerView::class.java)
            startActivity(intent)
        }



    }
}