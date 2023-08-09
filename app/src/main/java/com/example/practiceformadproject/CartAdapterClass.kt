package com.example.practiceformadproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapterClass(private val dataList: ArrayList<CartModel>,private val dataClassList:ArrayList<CartDataClass>) :
    RecyclerView.Adapter<CartAdapterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.medicine_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        val currentDataClassItem = dataClassList[position]

        holder.rvImage.setImageResource(currentDataClassItem.dataImage)
        holder.rvImage1.setImageResource(currentDataClassItem.dataImage1)
        holder.rvImage2.setImageResource(currentDataClassItem.dataImage2)

        holder.rvTitle.text = currentItem.name
        holder.rvCompany.text = currentItem.manufactureName
        holder.rvPrice.text = currentItem.price
//        holder.rvUnits.text = currentItem.unitAmount
        holder.rvQuantity.text = currentItem.quantity
        holder.rvDescription.text = currentItem.description
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.img1)
        val rvImage1: ImageView = itemView.findViewById(R.id.deleteImg)
        val rvImage2: ImageView = itemView.findViewById(R.id.editImg)

        val rvTitle: TextView = itemView.findViewById(R.id.Title)
        val rvPrice: TextView = itemView.findViewById(R.id.Price)
        val rvUnits: TextView = itemView.findViewById(R.id.Units)
        val rvQuantity: TextView = itemView.findViewById(R.id.Quantity)
        val rvCompany: TextView = itemView.findViewById(R.id.company)
        val rvDescription: TextView = itemView.findViewById(R.id.description)
    }
}

