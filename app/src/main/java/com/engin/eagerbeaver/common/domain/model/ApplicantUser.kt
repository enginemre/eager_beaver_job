package com.engin.eagerbeaver.common.domain.model

data class ApplicantUser(
    var name:String = "Guest",
    var surname:String= "GuestSurname",
    var email:String = "guest@gmail.com",
    var role : UserRole = UserRole.APPLICANT
)
