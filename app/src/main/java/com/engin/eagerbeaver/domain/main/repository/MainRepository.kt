package com.engin.eagerbeaver.domain.main.repository

import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.domain.model.User
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.auth.remote.dto.Return
import com.engin.eagerbeaver.data.main.remote.dto.*
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getUserInfo(userId:Long): Flow<Resource<ApplicantUser>>

    fun getCategories() :Flow<Resource<List<Category>>>

    fun getJobsWithCategoryId(categoryId:Long) : Flow<Resource<List<JobAdvert>>>

    fun getAppliedJobs(userId:Long) :Flow<Resource<List<JobAdvert>>>

    fun getJobDetail(jobId:Long) : Flow<Resource<JobAdvert>>

    fun applyJob(userId: Long,jobId: Long) : Flow<Resource<Boolean>>

    fun getMyAdverts(userId: Long): Flow<Resource<List<JobAdvert>>>

    fun deleteAdvert(jobId: Long) : Flow<Resource<Boolean>>

    fun updateAdvert(jobId: Long,body:UpdateAdvertDto) : Flow<Resource<Boolean>>

    fun searchJob(body:SearchSenderDto) : Flow<Resource<List<JobAdvert>>>

    fun createJob(body: CreateJobSenderDto) :Flow<Resource<CreateJobReturnDto>>

    fun updateProfile(userId: Long,body:UpdateProfileSenderDto) : Flow<Resource<UpdateProfileReturnDto>>
}