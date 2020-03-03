package com.example.companieslistinternshiptask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Job(
    @SerializedName("type")
    var type : String? = null ,
    @SerializedName("title")
    var title : String? = null,
    @SerializedName("company")
    var company: String? = null,
    @SerializedName("company_logo")
    var company_logo: String? = null
):Parcelable