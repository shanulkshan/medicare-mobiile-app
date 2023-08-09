package com.example.practiceformadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ViewAllMedicines : AppCompatActivity() {
    private lateinit var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var medicineList: ArrayList <MedicineModel>
    private lateinit var firebase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_medicines)

        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.userRecycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        recyclerView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY;
        medicineList = arrayListOf<MedicineModel>()

        getMedicines()

//        adapter = RecyclerAdapter()
//        recyclerView.adapter = adapter
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

                            val intent = Intent(this@ViewAllMedicines, AddToCart::class.java)

                            //put extras
                            intent.putExtra("medicineId", medicineList[position].medId )
                            intent.putExtra("medicineName", medicineList[position].name)
                            intent.putExtra("medicineDescription",medicineList[position].description)
                            intent.putExtra("medicineManufactureName",medicineList[position].manufactureName)
                            intent.putExtra("medicineManufactureDate",medicineList[position].manufactureDate)
                            intent.putExtra("medicineExpiryDate",medicineList[position].expiryDate)
                            intent.putExtra("medicineQuantity",medicineList[position].quantity)
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