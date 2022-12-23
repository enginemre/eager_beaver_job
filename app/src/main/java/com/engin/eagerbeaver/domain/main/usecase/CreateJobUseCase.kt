package com.engin.eagerbeaver.domain.main.usecase

import com.engin.eagerbeaver.common.domain.model.JobPosition
import com.engin.eagerbeaver.common.domain.model.JobType
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.main.remote.dto.CreateJobReturnDto
import com.engin.eagerbeaver.data.main.remote.dto.CreateJobSenderDto
import com.engin.eagerbeaver.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateJobUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val preferences: Preferences
)  {
    operator  fun invoke(category_id:Long,description:String,jobType: JobType,position: JobPosition,salary:Int,title:String): Flow<Resource<CreateJobReturnDto>> {
        val body = CreateJobSenderDto(
            category = category_id.toInt(),
            country = "Turkey",
            description = description,
            employer = preferences.getUserID().toInt(),
            jobType = jobType.fromType(),
            position = position.fromPosition(),
            salary = salary,
            title = title
        )
        return mainRepository.createJob(body)
    }
}