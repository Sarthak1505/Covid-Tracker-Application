package com.example.covidtrackerapplication.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.covidtrackerapplication.annotation.RetrofitOne
import com.example.covidtrackerapplication.annotation.RetrofitTwo
import com.example.covidtrackerapplication.api.CovidApi

import com.example.covidtrackerapplication.Constants.Constants
import com.example.covidtrackerapplication.data.CasesDatabase
import com.example.covidtrackerapplication.data.Repository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {





    @Singleton
    @Provides
    fun provideCache(@ApplicationContext application : Context) : Cache{
        val cacheSize : Long = 10*1024*1024
     val cache = Cache(application.cacheDir , cacheSize)
        return cache
    }


    @Singleton
    @Provides
    fun provideGson() : Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache : Cache) : OkHttpClient{
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }

    @Singleton
    @Provides
    @RetrofitOne
    fun provideRetrofit(gson : Gson , okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()


    }

    @Singleton
    @Provides
    @RetrofitTwo
    fun provideRetrofitCovid(gson: Gson,okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.COVID_BASE_URL)
            .client(okHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun provideRetrofitApi(@RetrofitTwo retrofit: Retrofit) : CovidApi {
          return retrofit.create(CovidApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(app : Application) : CasesDatabase {
        val database = Room.databaseBuilder(app, CasesDatabase::class.java,"cases-database").build()
       return database
    }




}