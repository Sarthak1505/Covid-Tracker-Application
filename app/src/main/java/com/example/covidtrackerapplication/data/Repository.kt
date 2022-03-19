package com.example.covidtrackerapplication.data

import android.util.Log
import androidx.room.withTransaction
import com.example.covidtrackerapplication.api.CovidApi
import com.example.covidtrackerapplication.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class Repository @Inject constructor(
   private val api : CovidApi,
    private val db : CasesDatabase
) {
  private val dao = db.casesDao()


    fun getData() = networkBoundResource(

        query = {
            Log.d("Repo", "query: ${dao.getAllData().toString()} ")
              dao.getAllData()
        },
        fetch  = {
        //delay(2000)

            Log.d("Repo", "fetch: ${api.getCovidData().toString()} ")
            api.getCovidData()
        },
        saveFetchResult = { cases ->
            Log.d("Repo", "save: ${cases.toString()} ")
            db.withTransaction {
                dao.deleteAll()

                dao.insert(cases.cases)

            }


        }

    )
}