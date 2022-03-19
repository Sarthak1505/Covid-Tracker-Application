package com.example.covidtrackerapplication.data

import com.google.gson.annotations.SerializedName


data class Cases(

    @SerializedName("statewise")
    val cases : MutableList<CovidData>
) {


}