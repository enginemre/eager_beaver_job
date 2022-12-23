package com.engin.eagerbeaver.common.data.preferences

import android.content.SharedPreferences
import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.domain.model.EmployeeUser
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.google.gson.Gson
import javax.inject.Inject

class DefaultPreferences @Inject constructor(
    private val gson: Gson,
    private val sharedPref: SharedPreferences
):Preferences {
    override fun saveEmployeeUser(user: EmployeeUser) {
        sharedPref.edit().putString(Preferences.KEY_EMPLOYER_USER, Gson().toJson(user)).apply()
    }

    override fun loadEmployerUser(): EmployeeUser {
        val json = sharedPref.getString(Preferences.KEY_EMPLOYER_USER, "")
        return gson.fromJson(json, EmployeeUser::class.java)
    }

    override fun saveLogin(isLogin: Boolean) {
        sharedPref.edit().putBoolean(Preferences.KEY_IS_LOGIN, isLogin).apply()
    }

    override fun isLogin(): Boolean {
        return sharedPref.getBoolean(Preferences.KEY_IS_LOGIN, false)
    }

    override fun saveUserType(userType: UserRole) {
        sharedPref.edit().putString(Preferences.KEY_USER_TYPE, Gson().toJson(userType)).apply()
    }

    override fun userType(): UserRole {
        val json = sharedPref.getString(Preferences.KEY_USER_TYPE, "")
        return gson.fromJson(json, UserRole::class.java)
    }

    override fun saveApplicantUser(user: ApplicantUser) {
        sharedPref.edit().putString(Preferences.KEY_APPLICANT_USER, Gson().toJson(user)).apply()
    }

    override fun loadApplicantUser(): ApplicantUser {
        val json = sharedPref.getString(Preferences.KEY_APPLICANT_USER, "")
        return gson.fromJson(json, ApplicantUser::class.java)
    }

    override fun saveUserID(id: Long) {
        sharedPref.edit().putLong(Preferences.KEY_USER_ID,id).apply()
    }

    override fun getUserID(): Long {
        return sharedPref.getLong(Preferences.KEY_USER_ID,0L)
    }

    override fun removeUser() {
        sharedPref.edit().remove(Preferences.KEY_USER_TYPE).apply()
        sharedPref.edit().remove(Preferences.KEY_APPLICANT_USER).apply()
        sharedPref.edit().remove(Preferences.KEY_EMPLOYER_USER).apply()
        saveLogin(false)
    }


}