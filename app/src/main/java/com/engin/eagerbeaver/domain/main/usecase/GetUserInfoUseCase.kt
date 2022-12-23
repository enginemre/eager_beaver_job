package com.engin.eagerbeaver.domain.main.usecase

import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val mainRepository: MainRepository
){
    operator fun invoke(userId:Long): Flow<Resource<ApplicantUser>> {
        return mainRepository.getUserInfo(userId)
    }
}