package com.example.companieslistinternshiptask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.companieslistinternshiptask.model.JobRepository

class ViewModelFactory(private val repository: JobRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JobViewModel(repository) as T
    }
}