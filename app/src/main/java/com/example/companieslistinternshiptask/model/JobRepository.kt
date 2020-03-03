package com.example.companieslistinternshiptask.model

import com.example.companieslistinternshiptask.api.ApiClient
import com.example.companieslistinternshiptask.api.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JobRepository:JobDataSource {
    override fun retrieveJob(callback: OperationCallback) {
        val call = ApiClient.build().getJobs()
        call.enqueue(object: Callback<List<Job>>{
            override fun onFailure(call: Call<List<Job>>, t: Throwable) {
                callback.onError(t.message)
            }
            override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                    response.body().let {
                        callback.onSuccess(it)
                    }
            }
        })
    }
}