package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

class AddToCart : AppCompatActivity() {
    private lateinit var tvDrugId: TextView
    private lateinit var tvDrugName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvManufactureName: TextView
    private lateinit var tvExpiryDate: TextView
    private lateinit var tvManufactureDate: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvQuantity: TextView
    private lateinit var backIcon:ImageView
    private lateinit var btnAddToCart: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_cart)

        //Initializing elements
        tvDrugId = findViewById(R.id.idMedDrug)
        tvDrugName = findViewById(R.id.idMedName)
        tvDescription = findViewById(R.id.idDescription)
        tvManufactureName = findViewById(R.id.idManfactureName)
        tvManufactureDate = findViewById(R.id.idManufactureDate)
        tvExpiryDate = findViewById(R.id.expiryDate)
        tvQuantity = findViewById(R.id.idQuantity)
        tvPrice = findViewById(R.id.idPrice)
        backIcon = findViewById(R.id.idBackArrow)
        btnAddToCart = findViewById(R.id.btnAddToCart)

        initView()
        setValuesToViews()

        //Navigate to All medicine recycle view after clicking back arrow
        backIcon.setOnClickListener{
            val intent = Intent(this, ViewAllMedicines::class.java)
            startActivity(intent)
        }

        btnAddToCart.setOnClickListener {
            addToCart(
                intent.getStringExtra("medicineId").toString(),
                intent.getStringExtra("medicineName").toString(),
                intent.getStringExtra("medicineDescription").toString(),
                intent.getStringExtra("medicineManufactureName").toString(),
                intent.getStringExtra("medicineManufactureDate").toString(),
                intent.getStringExtra("medicineExpiryDate").toString(),
                intent.getStringExtra("medicinePrice").toString(),
                intent.getStringExtra("medicineQuantity").toString(),
            )
        }

//        btnDelete.setOnClickListener{
//            deleteRecord(
//                intent.getStringExtra("medicineId").toString()
//            )
//        }

    }

//    private fun deleteRecord(medicineId: String) {
//        val dbReference = FirebaseDatabase.getInstance().getReference("medicines").child(medicineId)
//
//        val mTask = dbReference.removeValue()
//
//        mTask.addOnSuccessListener {
//            Toast.makeText(this,"Medicine data deleted",Toast.LENGTH_LONG).show()
//            val intent = Intent(this,ViewAllMedicines::class.java)
//            finish()
//            startActivity(intent)
//        }.addOnFailureListener { error ->
//            Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()
//        }
//    }

    private fun initView(){}
    private fun setValuesToViews(){
        tvDrugId.text = "Drug Id : [${intent.getStringExtra("medicineId")}]"
        tvDrugName.text = intent.getStringExtra("medicineName")
        tvDescription.text = intent.getStringExtra("medicineDescription")
        tvManufactureName.text = "Manufacture By : ${intent.getStringExtra("medicineManufactureName")}"
        tvManufactureDate.text = "Manufacture Date : ${intent.getStringExtra("medicineManufactureDate")}"
        tvExpiryDate.text = "Expire Date : ${intent.getStringExtra("medicineExpiryDate")}"
        tvPrice.text = "Rs.${intent.getStringExtra("medicinePrice")}/="
        tvQuantity.text = "Quantity in Stock : ${intent.getStringExtra("medicineQuantity")}"
    }

    private fun addToCart(
        medicineId: String,
        medicineName: String,
        medicineDescription: String,
        medicineManufactureName: String,
        medicineManufactureDate: String,
        medicineExpiryDate: String,
        medicinePrice: String,
        medicineQuantity: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflator = layoutInflater
        val mDialogView = inflator.inflate(R.layout.activity_continue_add_to_cart,null)

        mDialog.setView(mDialogView)

        val etMedicineName = mDialogView.findViewById<TextView>(R.id.idDrugName)
        val etMedicineDescription = mDialogView.findViewById<TextView>(R.id.idDescription)
        val etMedicineManufactureName = mDialogView.findViewById<TextView>(R.id.idManfactureName)
        val etMedicineManufactureDate = mDialogView.findViewById<TextView>(R.id.idManufactureDate)
        val etMedicineExpireDate = mDialogView.findViewById<TextView>(R.id.expiryDate)
        val etMedicinePrice = mDialogView.findViewById<TextView>(R.id.idPrice)
        val etMedicineQuantity = mDialogView.findViewById<TextView>(R.id.idQuantity)
        val btnContinueAddToCart = mDialogView.findViewById<Button>(R.id.btnContinueAddToCart)

        // Setting data to Dialog View textViews
        etMedicineName.text = medicineName
        etMedicineDescription.text = medicineDescription
        etMedicineManufactureName.text = medicineManufactureName
        etMedicineManufactureDate.text = medicineManufactureDate
        etMedicineExpireDate.text = medicineExpiryDate
        //tvCategory.text = etMedicineCategory.text.toString()
        etMedicinePrice.text = medicinePrice
        etMedicineQuantity.text = medicineQuantity

        // ------ comment -------------------------
        //set text to Dialog View Box textViews
//        etMedicineName.setText(medicineName)
//        etMedicineDescription.setText(medicineDescription)
//        etMedicineManufactureName.setText(medicineManufactureName)
//        etMedicineManufactureDate.setText(medicineManufactureDate)
//        etMedicineExpireDate.setText(medicineExpiryDate)
//        etMedicinePrice.setText(medicinePrice)
//        etMedicineQuantity.setText(medicineQuantity)

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnContinueAddToCart.setOnClickListener {
            continueAddToCart(
                medicineId,
                etMedicineName.text.toString(),
                etMedicineDescription.text.toString(),
                etMedicineManufactureName.text.toString(),
                etMedicineManufactureDate.text.toString(),
                etMedicineExpireDate.text.toString(),
                etMedicinePrice.text.toString(),
                etMedicineQuantity.text.toString(),
            )

            Toast.makeText(applicationContext,"Medicine Successfully Added To Cart",Toast.LENGTH_LONG).show()

//            // Setting updated data to viewMedicine Page textViews
//            tvDrugId.text = "Drug id : ${medicineId}"
//            tvDrugName.text = etMedicineName.text.toString()
//            tvDescription.text =etMedicineDescription.text.toString()
//            tvManufactureName.text = "Manufactured By : ${etMedicineManufactureName.text.toString()}"
//            tvManufactureDate.text = "Manufactured Date : ${etMedicineManufactureDate.text.toString()}"
//            tvExpiryDate.text = "Expire Date : ${etMedicineExpireDate.text.toString()}"
//            //tvCategory.text = etMedicineCategory.text.toString()
//            tvPrice.text = "Rs ${etMedicinePrice.text.toString()} /="
//            tvQuantity.text = "Quantity in Stock ${etMedicineQuantity.text.toString()}"

            alertDialog.dismiss()
        }

    }

    private fun continueAddToCart(
        medicineId: String,
        medicineName: String,
        medicineDescription: String,
        medicineManufactureName: String,
        medicineManufactureDate: String,
        medicineExpiryDate: String,
        medicinePrice: String,
        medicineQuantity: String
    ) {
        val dbReference = FirebaseDatabase.getInstance().getReference("Cart")

        var cartId = dbReference.push().key!!
        val cartItem = CartModel(cartId,medicineName,medicineDescription,medicineManufactureName,medicineQuantity,medicinePrice)
        dbReference.child(cartId).setValue(cartItem)
            .addOnCompleteListener {
                Toast.makeText(this,"Item Added to cart Successfully",Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
            }
        val intent = Intent(this, ViewAllMedicines::class.java)
        startActivity(intent)

//        val medicineInfo = MedicineModel(
//            medicineId,
//            medicineName,
//            medicineDescription,
//            medicineManufactureName,
//            medicineManufactureDate,
//            medicineExpiryDate,
//            medicinePrice,
//            medicineQuantity)
//        dbReference.setValue(medicineInfo)
    }
}