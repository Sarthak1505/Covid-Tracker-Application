package com.example.covidtrackerapplication.model

import com.google.gson.annotations.SerializedName

data class VaccineData(

    @SerializedName("name")
    val  centerName: String,

    @SerializedName("fee_type")
    val vaccinationCharges : String ,

    @SerializedName("min_age_limit")
    val vaccinationAge : String ,

    @SerializedName("slots")
    val vaccinationTimimgs : List<String> ,

    @SerializedName("vaccine")
    val  vaccinationName: String ,

    @SerializedName("address")
    val vaccinationCenterAddress : String ,

    @SerializedName("available_capacity")
    val vaccinationAvailable : String
) {
}