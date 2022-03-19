package com.example.covidtrackerapplication.api


import com.example.covidtrackerapplication.data.Cases
import com.example.covidtrackerapplication.data.CovidData
import retrofit2.Response
import retrofit2.http.GET

 interface CovidApi {
    @GET("state_wise")
     suspend fun getCovidData() : Cases
}