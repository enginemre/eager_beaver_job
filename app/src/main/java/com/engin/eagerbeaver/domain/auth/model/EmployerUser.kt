package com.engin.eagerbeaver.domain.auth.model

import com.engin.eagerbeaver.common.domain.model.UserRole

data class EmployerUser(
    override var name : String,
    override var email:String,
    override var userName:String,
    override var userType: UserRole,
    var age : Int
):User(name,email,userName, userType)
