package com.engin.eagerbeaver.domain.main.usecase

import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.auth.remote.dto.Return
import com.engin.eagerbeaver.data.main.remote.dto.UpdateProfileReturnDto
import com.engin.eagerbeaver.data.main.remote.dto.UpdateProfileSenderDto
import com.engin.eagerbeaver.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke(userId:Long,description:String,email:String,fullName:String,title:String,username:String): Flow<Resource<UpdateProfileReturnDto>> {
        val body  = UpdateProfileSenderDto(
            description = description,
            email = email,
            full_name = fullName,
            title = title,
            username = username
        )
        return mainRepository.updateProfile(userId = userId, body = body)
    }
}