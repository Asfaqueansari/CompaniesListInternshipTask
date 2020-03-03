package com.example.companieslistinternshiptask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.companieslistinternshiptask.api.OperationCallback
import com.example.companieslistinternshiptask.model.Job
import com.example.companieslistinternshiptask.model.JobRepository

class JobViewModel(private val repository: JobRepository):ViewModel(){
    private val jobsLoading = MutableLiveData<List<Job>>().apply { value = emptyList()}
     val jobs:LiveData<List<Job>> = jobsLoading

    private val viewLoading = MutableLiveData<Boolean>()
     val isViewLoading:LiveData<Boolean> = viewLoading

    private val messageError = MutableLiveData<Any>()
     val onMessageError:LiveData<Any> = messageError

    fun loadData(){
        viewLoading.postValue(true)
        repository.retrieveJob(object :OperationCallback{
            override fun onSuccess(obj: Any?) {
                viewLoading.postValue(false)
                if(obj !=null && obj is List<*>){
                    jobsLoading.value = obj as List<Job>
                }
            }
            override fun onError(obj: Any?) {
               viewLoading.postValue(false)
                messageError.postValue(obj)
            }
        })
    }
}