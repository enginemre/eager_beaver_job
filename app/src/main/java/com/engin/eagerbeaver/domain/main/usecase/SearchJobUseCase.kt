package com.engin.eagerbeaver.domain.main.usecase

import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.domain.model.JobType
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.main.remote.dto.SearchSenderDto
import com.engin.eagerbeaver.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchJobUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke(categoryId:Long?,jobType: JobType?,title:String?): Flow<Resource<List<JobAdvert>>> {
        val searchSenderDto = SearchSenderDto(
            categoryId = categoryId?.toInt(),
            jobType = jobType?.fromType(),
            title = title
        )
        return mainRepository.searchJob(searchSenderDto)
    }
}