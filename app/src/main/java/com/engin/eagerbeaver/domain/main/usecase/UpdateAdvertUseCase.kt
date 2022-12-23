package com.engin.eagerbeaver.domain.main.usecase

import com.engin.eagerbeaver.common.domain.model.JobPosition
import com.engin.eagerbeaver.common.domain.model.JobType
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.main.remote.dto.UpdateAdvertDto
import com.engin.eagerbeaver.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateAdvertUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke(jobId:Long,categoryId:Long,description:String,userId:Long,jobType: JobType,position: JobPosition,title:String,salary:Int): Flow<Resource<Boolean>> {
        val updateAdvertDto = UpdateAdvertDto(
            category = categoryId.toInt(),
            country = "TURKEY",
            description = description,
            employer = userId.toInt(),
            jobType = jobType.fromType(),
            position = position.fromPosition(),
            title = title,
            salary = salary
        )
        return mainRepository.updateAdvert(jobId =jobId ,updateAdvertDto)
    }
}