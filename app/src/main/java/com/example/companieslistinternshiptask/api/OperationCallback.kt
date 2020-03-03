package com.example.companieslistinternshiptask.api

interface OperationCallback {
    fun onSuccess(obj:Any?)
    fun onError(obj:Any?)
}