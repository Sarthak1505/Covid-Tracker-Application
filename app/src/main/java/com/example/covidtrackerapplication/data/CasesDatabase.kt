package com.example.covidtrackerapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CovidData::class], version = 1, exportSchema = false )

abstract class CasesDatabase  : RoomDatabase(){

    abstract fun casesDao() : casesDao
}