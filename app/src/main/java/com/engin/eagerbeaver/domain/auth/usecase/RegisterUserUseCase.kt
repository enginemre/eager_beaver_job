package com.engin.eagerbeaver.domain.auth.usecase

import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.auth.remote.dto.Return
import com.engin.eagerbeaver.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
   private val authRepository: AuthRepository
) {
    operator fun invoke(name:String, userName:String, password:String, email:String, type: UserRole, interestId:List<Long>? = null, description:String? =null, title :String? =null): Flow<Resource<Return>> {
        return authRepository.registerUser(name, userName, password, email, type, interestId, description,title)
    }
}