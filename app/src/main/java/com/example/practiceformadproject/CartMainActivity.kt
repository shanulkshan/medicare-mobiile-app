package com.example.practiceformadproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class CartMainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<CartModel> //medicine List array
    private lateinit var dataClassList: ArrayList<CartDataClass>
    private lateinit var searchView: SearchView
    private lateinit var searchList:ArrayList<CartDataClass>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cartactivity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<CartModel>()
        dataClassList = arrayListOf<CartDataClass>()
        searchList = arrayListOf<CartDataClass>()

        getMedicineData()

        // ----------------------------------    (Unwanted) Search    -----------------------------------------------------

//        val imageList = arrayOf(R.drawable.img)
//        val image1 = arrayOf(R.drawable.baseline_edit_24)
//        val image2 = arrayOf(R.drawable.baseline_delete_forever_24)
//        val dataTitle = arrayOf("Paracetamol")
//        val dataCompany = arrayOf("hhhhhh")
//        val dataPrice = arrayOf(10)
//        val dataUnits = arrayOf("one")
//        val dataQuantity = arrayOf(1)
//
//        searchView.clearFocus()
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                searchView.clearFocus()
//                return true
//            }
//            override fun onQueryTextChange(newText: String?): Boolean {
//                searchList.clear()
//                val searchText = newText?.toLowerCase(Locale.getDefault())
//                if (!searchText.isNullOrEmpty()) {
//                    dataList.forEach {
//                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
//                            searchList.add(it)
//                        }
//                    }
//                    recyclerView.adapter?.notifyDataSetChanged()
//                } else {
//                    searchList.clear()
//                    searchList.addAll(dataClassList)
//                    recyclerView.adapter!!.notifyDataSetChanged()
//                }
//                return false
//            }
//        })
//
//
//        for ((index, _) in imageList.withIndex()) {
//            val dataClass = CartDataClass(
//                imageList[index],
//                image1[index],
//                image2[index],
//                dataTitle[index],
//                dataCompany[index],
//                dataPrice[index],
//                dataUnits[index],
//                dataQuantity[index]
//            )
//            dataClassList.add(dataClass)
//        }
//        searchList.addAll(dataClassList)
//        recyclerView.adapter = CartAdapterClass(searchList)
//        recyclerView.adapter = CartAdapterClass(dataList)
      }

    private fun getMedicineData() {
        recyclerView.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Cart")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                if (snapshot.exists()) {
                    for (dataSnap in snapshot.children) {
                        val medicineData = dataSnap.getValue(CartModel::class.java)
                        dataList.add(medicineData!!)
                    }
                    val myAdapter = CartAdapterClass(dataList,dataClassList)
                    recyclerView.adapter = myAdapter

                    recyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
