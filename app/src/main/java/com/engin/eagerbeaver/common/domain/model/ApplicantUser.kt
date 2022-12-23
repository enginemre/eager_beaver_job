package com.engin.eagerbeaver.common.domain.model

import java.util.Date

data class ApplicantUser(
    override var name:String = "Guest",
    override var userEmail: String = "guest@gmail.com",
    override var userName: String = "",
    var imageUrl:String = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
    var birthDate:Date = Date(),
    var description:String = "",
    var category: List<Int> = emptyList(),
    var title:String = "Guest",
    override var userType: UserRole= UserRole.APPLICANT,
    override var id: Int,
): User
