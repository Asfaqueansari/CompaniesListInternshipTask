package com.example.companieslistinternshiptask.api

import com.example.companieslistinternshiptask.model.Job
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object ApiClient {
    private const val BASE_URL  = "https://jobs.github.com/"
    private var serviceApiInterface:ServiceApiInterface? = null

    fun build():ServiceApiInterface{
        val builder: Retrofit.Builder= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit: Retrofit = builder.build()
        serviceApiInterface = retrofit.create(ServiceApiInterface::class.java)
        return serviceApiInterface as ServiceApiInterface
    }
    interface ServiceApiInterface {
        @GET("positions.json?description=java&full_time=true")
        fun getJobs(): Call<List<Job>>
    }
}