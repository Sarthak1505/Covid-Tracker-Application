package com.example.covidtrackerapplication.data

import androidx.lifecycle.*

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CovidViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {


    val covidData =repository.getData().asLiveData()


}