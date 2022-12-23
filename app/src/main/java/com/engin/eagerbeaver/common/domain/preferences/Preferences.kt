package com.engin.eagerbeaver.common.domain.preferences

import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.domain.model.EmployeeUser
import com.engin.eagerbeaver.common.domain.model.UserRole

interface Preferences {

    fun saveEmployeeUser(user: EmployeeUser)

    fun loadEmployerUser(): EmployeeUser

    fun saveLogin(isLogin: Boolean)

    fun isLogin(): Boolean

    fun saveUserType(userType: UserRole)

    fun userType(): UserRole

    fun saveApplicantUser(user: ApplicantUser)

    fun loadApplicantUser(): ApplicantUser

    fun saveUserID(id:Long)

    fun getUserID() : Long

    fun removeUser()


    companion object {
        const val KEY_IS_LOGIN = "isLogin"
        const val KEY_USER_TYPE = "userType"
        const val KEY_APPLICANT_USER = "applicantUser"
        const val KEY_EMPLOYER_USER = "employerUser"
        const val KEY_USER_ID = "userId"
    }
}