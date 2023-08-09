package com.example.practiceformadproject


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceformadproject.SubAdapter
import com.example.practiceformadproject.ComplaintsModel
import com.example.practiceformadproject.R
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class DisplayComp : AppCompatActivity(), SearchView.OnQueryTextListener{


    private lateinit var subRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var searchView: SearchView
    private lateinit var subList: ArrayList<ComplaintsModel>
    private lateinit var dbCon: DatabaseReference

    @SuppressLint("MissingInflatedId")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_comp)

        subRecyclerView = findViewById(R.id.rvSub)
        subRecyclerView.layoutManager = LinearLayoutManager(this)
        subRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        searchView = findViewById(R.id.searchView)

        subList = arrayListOf<ComplaintsModel>()

        // set up the SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search for classes"



        getSubData()

        val sixthAct = findViewById<Button>(R.id.add)
        sixthAct.setOnClickListener{
            val intent = Intent(this,SugHome::class.java)
            startActivity(intent)
        }


    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!TextUtils.isEmpty(newText)) {
            search(newText!!)
        } else {
            getSubData()
        }
        return true
    }
    private fun search(query: String) {
        val searchResultList = arrayListOf<ComplaintsModel>()
        for (ComplaintsModel in subList) {
            if (ComplaintsModel.subject?.toLowerCase(Locale.ROOT)?.contains(query.toLowerCase()) == true) {
                searchResultList.add(ComplaintsModel)
            }
        }
        val mAdapter = SubAdapter(searchResultList)
        subRecyclerView.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : SubAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@DisplayComp, SubjectDetails::class.java)
                //put extras
                intent.putExtra("compId", searchResultList[position].compId)
                intent.putExtra("name", searchResultList[position].name)
                intent.putExtra("mail", searchResultList[position].mail)
                intent.putExtra("subject", searchResultList[position].subject)
                intent.putExtra("description", searchResultList[position].description)

                startActivity(intent)
            }
        })
    }
    private fun getSubData(){
        subRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

         dbCon = FirebaseDatabase.getInstance().getReference("Complaints")

        dbCon.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               subList.clear()
                if(snapshot.exists()){
                    for(subSnap in snapshot.children){
                        val subData = subSnap.getValue(ComplaintsModel::class.java)
                        subList.add(subData!!)
                    }
                    val mAdapter = SubAdapter(subList)
                    subRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : SubAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@DisplayComp, SubjectDetails::class.java)


                            intent.putExtra("compId", subList[position].compId)
                            intent.putExtra("name", subList[position].name)
                            intent.putExtra("mail", subList[position].mail)
                            intent.putExtra("subject", subList[position].subject)
                            intent.putExtra("description", subList[position].description)
                            startActivity(intent)
                        }

                    })

                    subRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}