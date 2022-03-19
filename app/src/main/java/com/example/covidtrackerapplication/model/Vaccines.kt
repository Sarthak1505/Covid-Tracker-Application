package com.example.covidtrackerapplication.model

import com.google.gson.annotations.SerializedName

data class Vaccines(
    @SerializedName("sessions")
    val  vaccinationCenter: List<VaccineData> )
  {

}