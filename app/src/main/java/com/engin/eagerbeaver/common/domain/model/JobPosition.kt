package com.engin.eagerbeaver.common.domain.model

sealed class JobPosition {
    object Senior:JobPosition()
    object Junior:JobPosition()
    object Middle:JobPosition()
    object Experienced:JobPosition()
    object Graduated:JobPosition()

    fun fromPosition() : String{
        return when(this){
            Experienced -> "Tecrübeli"
            Graduated -> "Mezun"
            Junior -> "Çaylak"
            Middle -> "Middle"
            Senior -> "Kıdemli"
        }
    }

    companion object{
        fun toPosition(position:String) : JobPosition {
            return when(position){
                "Tecrübeli"-> Experienced
                "Mezun" -> Graduated
                "Çaylak" ->Junior
                "Middle" -> Middle
                "Kıdemli"-> Senior
                else -> {Experienced}
            }
        }
    }


}