package com.engin.eagerbeaver.common.domain.model

sealed class JobType {
    object FullTime:JobType()
    object PartTime:JobType()
    object Remote:JobType()
    object Intern:JobType()
    object Hybrid:JobType()

    fun fromType() : String{
        return when(this){
            FullTime -> "Tam Zamanlı"
            Intern -> "Stajer"
            PartTime -> "Yarı Zamanlı"
            Remote -> "Uzaktan"
            Hybrid -> "Hibrit"
        }
    }
    companion object{
        fun toType(type:String) : JobType {
            return when(type){
                "Tam Zamanlı" -> FullTime
                "Stajer" ->Intern
                "Yarı Zamanlı"-> PartTime
                "Uzaktan"->Remote
                "Hibrit"-> Hybrid
                else -> {FullTime}
            }
        }
    }

}