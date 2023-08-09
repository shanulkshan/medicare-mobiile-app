package com.example.practiceformadproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val medicineList: ArrayList<MedicineModel>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var  myListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        myListener = clickListener
    }
    private val pictures:IntArray = intArrayOf(R.drawable.salbutamol,R.drawable.panadol,R.drawable.digene,R.drawable.corex_d,R.drawable.losartan50mg,R.drawable.aspirin,R.drawable.metformin,R.drawable.amoxcillin,R.drawable.croxil,R.drawable.gabapentin,R.drawable.penicilin)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.service_provider_card_view,parent,false)
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

    inner class ViewHolder(itemView:View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var medName: TextView
        var medPrice: TextView

        init{
            image = itemView.findViewById(R.id.imageView)
            medName = itemView.findViewById(R.id.tvMedName)
            medPrice = itemView.findViewById(R.id.tvMedPrice)

            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}