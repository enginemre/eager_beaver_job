package com.engin.eagerbeaver.domain.auth.repository

import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.auth.remote.dto.CheckMailReturnDto
import com.engin.eagerbeaver.data.auth.remote.dto.DataLoginDto
import com.engin.eagerbeaver.data.auth.remote.dto.Return
import com.engin.eagerbeaver.data.auth.remote.dto.ReturnLoginDto
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun loginUser(email:String,password: String) : Flow<Resource<ReturnLoginDto>>

    fun registerUser(name:String, userName:String, password:String, email:String, type: UserRole, interestId:List<Long>? = null, description:String? =null, title :String? =null) : Flow<Resource<Return>>

    fun checkMail(email: String) : Flow<Resource<CheckMailReturnDto>>

}