package com.engin.eagerbeaver.domain.auth.model

import com.engin.eagerbeaver.common.domain.model.UserRole

data class ApplicantUser(
    override var name : String,
    override var email:String,
    override var userName:String,
    override var userType: UserRole,
    val job:String
): User(name,email,userName, userType)
