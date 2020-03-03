package com.example.companieslistinternshiptask.model

import com.example.companieslistinternshiptask.api.OperationCallback

interface JobDataSource {
    fun retrieveJob(callback: OperationCallback)
}