package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class DataCategoryDto(
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)