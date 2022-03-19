package com.example.covidtrackerapplication.util

import android.util.Log
import kotlinx.coroutines.flow.*
import java.lang.Exception


fun <ResultType, RequestType> networkBoundResource(
    query: () -> Flow<ResultType>,
    fetch : suspend() -> RequestType,
    saveFetchResult : suspend (RequestType) -> Unit,
    shouldFetch:(ResultType) -> Boolean ={ true }
    ) = flow{
        val data  =query().first()
     val flowData =    if(shouldFetch(data)){
           emit(Resources.Loading(data))
          try{
              saveFetchResult(fetch())
              query().map {
                  Resources.Success(it)
              }
          }
           catch (throwable : Throwable){
                     query().map { Resources.Error(throwable,it) }
           }
       }
    else{
        query().map { Resources.Success(it) }
       }
    emitAll(flowData)
}