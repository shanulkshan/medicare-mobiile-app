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

class ServiceProviderViewMedicine : AppCompatActivity() {
    private lateinit var tvDrugId: TextView
    private lateinit var tvDrugName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvManufactureName: TextView
    private lateinit var tvExpiryDate: TextView
    private lateinit var tvManufactureDate: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvQuantity: TextView
    private lateinit var backIcon:ImageView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_provider_view_medicine)

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
        btnEdit = findViewById<Button>(R.id.btnEdit)
        btnDelete = findViewById<Button>(R.id.btnDelete)

        initView()
        setValuesToViews()

        //Navigate to All medicine recycle view after clicking back arrow
        backIcon.setOnClickListener{
            val intent = Intent(this, ServiceProviderRecyclerView::class.java)
            startActivity(intent)
        }

        btnEdit.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("medicineId").toString(),
                intent.getStringExtra("medicineName").toString(),
                intent.getStringExtra("medicineDescription").toString(),
                intent.getStringExtra("medicineManufactureName").toString(),
                intent.getStringExtra("medicineManufactureDate").toString(),
                intent.getStringExtra("medicineExpiryDate").toString(),
                intent.getStringExtra("medicineCategory").toString(),
                intent.getStringExtra("medicinePrice").toString(),
                intent.getStringExtra("medicineQuantity").toString(),
            )
//            val intent = Intent(this, EditMedicine::class.java)
//            startActivity(intent)
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("medicineId").toString()
            )
        }

    }

    private fun deleteRecord(medicineId: String) {
        val dbReference = FirebaseDatabase.getInstance().getReference("medicines").child(medicineId)

        val mTask = dbReference.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Medicine data deleted",Toast.LENGTH_LONG).show()
            val intent = Intent(this,ViewAllMedicines::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

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

    private fun openUpdateDialog(
        medicineId: String,
        medicineName: String,
        medicineDescription: String,
        medicineManufactureName: String,
        medicineManufactureDate: String,
        medicineExpiryDate: String,
        medicineCategory: String,
        medicinePrice: String,
        medicineQuantity: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflator = layoutInflater
        val mDialogView = inflator.inflate(R.layout.activity_edit_medicine,null)

        mDialog.setView(mDialogView)

        val etMedicineName = mDialogView.findViewById<EditText>(R.id.idDrugName)
        val etMedicineDescription = mDialogView.findViewById<EditText>(R.id.idDescription)
        val etMedicineManufactureName = mDialogView.findViewById<EditText>(R.id.idManufactureName)
        val etMedicineManufactureDate = mDialogView.findViewById<EditText>(R.id.idManufactureDate)
        val etMedicineExpireDate = mDialogView.findViewById<EditText>(R.id.idExpireDate)
        val etMedicineCategory = mDialogView.findViewById<EditText>(R.id.idCategory)
        val etMedicinePrice = mDialogView.findViewById<EditText>(R.id.idPrice)
        val etMedicineQuantity = mDialogView.findViewById<EditText>(R.id.idQuantity)
        val btnUpdate = mDialogView.findViewById<Button>(R.id.btnUpdateData)

//        etMedicineName.setText(intent.getStringExtra("medicineName").toString())
        etMedicineName.setText(medicineName)
        etMedicineDescription.setText(medicineDescription)
        etMedicineManufactureName.setText(medicineManufactureName)
        etMedicineManufactureDate.setText(medicineManufactureDate)
        etMedicineExpireDate.setText(medicineExpiryDate)
        etMedicineCategory.setText(medicineCategory)
        etMedicinePrice.setText(medicinePrice)
        etMedicineQuantity.setText(medicineQuantity)

        mDialog.setTitle("Updating $medicineName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdate.setOnClickListener {
            updateMedicineData(
                medicineId,
                etMedicineName.text.toString(),
                etMedicineDescription.text.toString(),
                etMedicineManufactureName.text.toString(),
                etMedicineManufactureDate.text.toString(),
                etMedicineExpireDate.text.toString(),
                etMedicineCategory.text.toString(),
                etMedicinePrice.text.toString(),
                etMedicineQuantity.text.toString(),
            )

            Toast.makeText(applicationContext,"Medicine Data Updated",Toast.LENGTH_LONG).show()

            // Setting updated data to viewMedicine Page textViews
            tvDrugId.text = "Drug id : ${medicineId}"
            tvDrugName.text = etMedicineName.text.toString()
            tvDescription.text =etMedicineDescription.text.toString()
            tvManufactureName.text = "Manufactured By : ${etMedicineManufactureName.text.toString()}"
            tvManufactureDate.text = "Manufactured Date : ${etMedicineManufactureDate.text.toString()}"
            tvExpiryDate.text = "Expire Date : ${etMedicineExpireDate.text.toString()}"
            //tvCategory.text = etMedicineCategory.text.toString()
            tvPrice.text = "Rs ${etMedicinePrice.text.toString()} /="
            tvQuantity.text = "Quantity in Stock ${etMedicineQuantity.text.toString()}"

            alertDialog.dismiss()
        }

    }

    private fun updateMedicineData(
        medicineId: String,
        medicineName: String,
        medicineDescription: String,
        medicineManufactureName: String,
        medicineManufactureDate: String,
        medicineExpiryDate: String,
        medicineCategory: String,
        medicinePrice: String,
        medicineQuantity: String
    ) {
        val dbReference = FirebaseDatabase.getInstance().getReference("medicines").child(medicineId)
        val medicineInfo = MedicineModel(
            medicineId,
            medicineName,
            medicineDescription,
            medicineManufactureName,
            medicineManufactureDate,
            medicineExpiryDate,
            medicineCategory,
            medicinePrice,
            medicineQuantity)
        dbReference.setValue(medicineInfo)
    }
}