package com.engin.eagerbeaver.common.domain.preferences

import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.domain.auth.model.ApplicantUser
import com.engin.eagerbeaver.domain.auth.model.EmployerUser

interface Preferences {

    fun saveEmployerUser(user: EmployerUser)

    fun loadEmployerUser(): EmployerUser

    fun saveLogin(isLogin: Boolean)

    fun isLogin(): Boolean

    fun saveUserType(userType: UserRole)

    fun userType(): UserRole

    fun saveApplicantUser(user: ApplicantUser)

    fun loadApplicantUser(): ApplicantUser


    fun removeUser()


    companion object {
        const val KEY_IS_LOGIN = "isLogin"
        const val KEY_USER_TYPE = "userType"
        const val KEY_APPLICANT_USER = "applicantUser"
        const val KEY_EMPLOYER_USER = "employerUser"
    }
}