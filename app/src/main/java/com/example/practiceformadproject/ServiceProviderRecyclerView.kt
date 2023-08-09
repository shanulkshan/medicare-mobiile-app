package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ServiceProviderRecyclerView : AppCompatActivity() {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var medicineList: ArrayList <MedicineModel>
    private lateinit var firebase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_provider_recycler_view)

        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        medicineList = arrayListOf<MedicineModel>()

        getMedicines()
    }

    private fun getMedicines() {
        recyclerView.visibility = View.GONE

        firebase = FirebaseDatabase.getInstance().getReference("medicines")

        firebase.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                medicineList.clear()
                if(snapshot.exists()){
                    for(medicineSnap in snapshot.children) {
                        val medicineData = medicineSnap.getValue(MedicineModel::class.java)
                        medicineList.add(medicineData!!)
                    }
                    val mAdapter = RecyclerAdapter(medicineList)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : RecyclerAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@ServiceProviderRecyclerView, ServiceProviderViewMedicine::class.java)

                            //put extras
                            intent.putExtra("medicineId", medicineList[position].medId )
                            intent.putExtra("medicineName", medicineList[position].name)
                            intent.putExtra("medicineDescription",medicineList[position].description)
                            intent.putExtra("medicineManufactureName",medicineList[position].manufactureName)
                            intent.putExtra("medicineManufactureDate",medicineList[position].manufactureDate)
                            intent.putExtra("medicineExpiryDate",medicineList[position].expiryDate)
                            intent.putExtra("medicineQuantity",medicineList[position].quantity)
                            intent.putExtra("medicinePrice",medicineList[position].pricePerUnit)
                            intent.putExtra("medicineCategory",medicineList[position].category)
                            startActivity(intent)

//                            "Medicine Image and Pharmacy Name to be add"
//                            intent.putExtra("pharmacyName",pharmacyList[position].pharmacyName)
//                            intent.putExtra("medicineImage",medicineList[position].image)
                        }

                    })
                    recyclerView.visibility = View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}