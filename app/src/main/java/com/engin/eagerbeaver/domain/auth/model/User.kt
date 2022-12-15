package com.engin.eagerbeaver.domain.auth.model

import com.engin.eagerbeaver.common.domain.model.UserRole

open class User(
    open var name : String,
    open var email:String,
    open var userName:String,
    open var userType: UserRole,
)