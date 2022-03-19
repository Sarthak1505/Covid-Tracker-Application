package com.example.covidtrackerapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cases")
data class CovidData(
    @SerializedName("active")
    val active :String,
    @SerializedName("recovered")
    val recovered : String,
    @SerializedName("deaths")
    val deaths : String,
    @SerializedName("confirmed")
    val confirmed : String,
    @SerializedName("state")
   @PrimaryKey val state : String
) {

}