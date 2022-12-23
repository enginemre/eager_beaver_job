package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class GetMyAdvertsReturnDto(
    @SerializedName("data")
    val `data`: List<DataMyAdvertsDto>
)