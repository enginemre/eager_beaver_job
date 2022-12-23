package com.engin.eagerbeaver.domain.main.usecase

import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJobInfoByIdUseCase @Inject constructor(
    private val mainRepository: MainRepository
){
    operator fun invoke(jobId:Long): Flow<Resource<JobAdvert>> {
        return mainRepository.getJobDetail(jobId)
    }
}