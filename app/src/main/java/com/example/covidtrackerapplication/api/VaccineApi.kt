package com.example.covidtrackerapplication.api


import com.example.covidtrackerapplication.model.Vaccines
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface VaccineApi {
  @GET
  suspend fun getVaccine(@Url url : String) : Response<Vaccines>
}