package com.engin.eagerbeaver.common.domain.model

import com.engin.eagerbeaver.common.domain.model.UserRole

interface  User{
    var id:Int
    var name : String
    var userEmail:String
    var userName:String
    var userType: UserRole
}
