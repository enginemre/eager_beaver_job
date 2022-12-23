package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class CategoryReturnDto(
    @SerializedName("data")
    val `data`: List<DataCategoryDto>
)