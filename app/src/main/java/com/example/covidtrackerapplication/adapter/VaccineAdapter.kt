package com.example.covidtrackerapplication.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackerapplication.R
import com.example.covidtrackerapplication.model.VaccineData
import com.example.covidtrackerapplication.model.Vaccines

class VaccineAdapter(vaccineData  :List<VaccineData>) : RecyclerView.Adapter<VaccineAdapter.VaccineHolder>() {
    var displayList = vaccineData
    private  val TAG = "adapter"
  class VaccineHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val name = itemView.findViewById<TextView>(R.id.hospitalName)
      val address = itemView.findViewById<TextView>(R.id.address)
      val vaccine = itemView.findViewById<TextView>(R.id.vaccineType)
      val price = itemView.findViewById<TextView>(R.id.price)
      val time = itemView.findViewById<TextView>(R.id.time)
      val available = itemView.findViewById<TextView>(R.id.availability)
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineHolder {
        val view = VaccineHolder(LayoutInflater.from(parent.context).inflate(R.layout.vaccine_list_item,parent,false))
        Log.d(TAG, "onCreateViewHolder: executed")
        return view
    }

    override fun onBindViewHolder(holder: VaccineHolder, position: Int) {
        val vaccinationCenter = displayList.get(position)
        Log.d(TAG, "onBindViewHolder: $vaccinationCenter")
       holder.name.text = vaccinationCenter.centerName
        holder.address.text = vaccinationCenter.vaccinationCenterAddress
        holder.vaccine.text = vaccinationCenter.vaccinationName
        holder.price.text = vaccinationCenter.vaccinationCharges
        holder.time.text = vaccinationCenter.vaccinationTimimgs.get(0)
        holder.available.text = "Available:- " +vaccinationCenter.vaccinationAvailable


    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ")
        return displayList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyData(newList : List<VaccineData>){
        Log.d(TAG, "notifyData: called")

        displayList = newList
        Log.d(TAG, "notifyData: $displayList")
       notifyDataSetChanged()
    }

 

}
