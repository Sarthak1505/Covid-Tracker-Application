package com.example.covidtrackerapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackerapplication.R
import com.example.covidtrackerapplication.adapter.CovidCaseAdapter.*
import com.example.covidtrackerapplication.data.CovidData
import com.google.android.material.tabs.TabLayout

class CovidCaseAdapter(dataList : ArrayList<CovidData>, context : Context, tabLayout: TabLayout) : RecyclerView.Adapter<viewHolder>() {
 val data = dataList
  val context = context
    val tabLayout = tabLayout


class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val stateName = itemView.findViewById<TextView>(R.id.stateName)
    val cases = itemView.findViewById<TextView>(R.id.stateCase)
   val view = itemView.findViewById<CardView>(R.id.covidCardView)


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.covid_case_item,parent,false))
        return view
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currItem = data.get(position)
        val view = holder.view
        holder.stateName.text = currItem.state
        setDetails(holder,"active",currItem)
        when(position%5){
     1-> view.setCardBackgroundColor(context.getColor(R.color.recyclerView5))
     2->  view.setCardBackgroundColor(context.getColor(R.color.recyclerView2))
     3->  view.setCardBackgroundColor(context.getColor(R.color.recyclerView3))
     4->  view.setCardBackgroundColor(context.getColor(R.color.recyclerView4))
     else -> {
         view.setCardBackgroundColor(context.getColor(R.color.recyclerView1))
     }

     }
   tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
       override fun onTabSelected(tab: TabLayout.Tab?) {
           when(tab?.position){
               0 -> setDetails(holder,"active",currItem)
               1-> setDetails(holder , "deaths",currItem)
               2-> setDetails(holder,"recovered",currItem)
           }
       }

       override fun onTabUnselected(tab: TabLayout.Tab?) {
          return
       }

       override fun onTabReselected(tab: TabLayout.Tab?) {
          return
       }

   })





    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun setDetails(holder : viewHolder , description: String , currItem : CovidData){
               if(description.equals("active")){
                   holder.cases.text = currItem.active
               }
               else if(description.equals("deaths")){
                   holder.cases.text = currItem.deaths
               }
               else{
                   holder.cases.text = currItem.recovered
               }
    }


}