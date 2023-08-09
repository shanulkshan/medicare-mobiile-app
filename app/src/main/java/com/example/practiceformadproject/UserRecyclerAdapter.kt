package com.example.practiceformadproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserRecyclerAdapter(private val medicineList: ArrayList<MedicineModel>) :
    RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    private lateinit var myListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        myListener = clickListener
    }

    private val pictures:IntArray = intArrayOf(R.drawable.salbutamol,R.drawable.panadol,R.drawable.digene,R.drawable.croxil,R.drawable.losartan50mg,R.drawable.aspirin,R.drawable.metformin,R.drawable.amoxcillin,R.drawable.corex_d,R.drawable.gabapentin,R.drawable.penicilin)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card_view, parent, false)
        return ViewHolder(v, myListener)
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMedicine = medicineList[position]

        holder.medName.text = currentMedicine.name
        holder.medPrice.text = "Rs.${currentMedicine.pricePerUnit}/="
        holder.image.setImageResource(pictures[position])
    }

    inner class ViewHolder(itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.imageView)
        var medName: TextView = itemView.findViewById(R.id.tvMedName)
        var medPrice: TextView = itemView.findViewById(R.id.tvMedPrice)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}