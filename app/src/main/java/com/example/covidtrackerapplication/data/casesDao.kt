package com.example.covidtrackerapplication.data

import androidx.room.*

import kotlinx.coroutines.flow.Flow


@Dao

interface casesDao {


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(cases : MutableList<CovidData>)
  @Query("DELETE FROM cases" )
  suspend fun deleteAll()

  @Query("SELECT * FROM cases")
  fun getAllData() : Flow<MutableList<CovidData>>

}