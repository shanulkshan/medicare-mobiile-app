package com.example.practiceformadproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceformadproject.ComplaintsModel
import com.example.practiceformadproject.R

class SubAdapter(private val subList: ArrayList<ComplaintsModel>) : RecyclerView.Adapter<SubAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_complaint, parent,false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentSub = subList[position]
        holder.tvSubName.text = currentSub.subject
    }




    override fun getItemCount(): Int {
        return subList.size
    }

    class ViewHolder(itemView: View,clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvSubName : TextView = itemView.findViewById(R.id.tvSubName)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }




}