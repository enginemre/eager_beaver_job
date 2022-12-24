package com.engin.eagerbeaver.data.main.repository

import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.domain.model.User
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.common.presentation.util.UiText
import com.engin.eagerbeaver.data.auth.remote.dto.Return
import com.engin.eagerbeaver.data.main.mapper.*
import com.engin.eagerbeaver.data.main.remote.MainApi
import com.engin.eagerbeaver.data.main.remote.dto.*
import com.engin.eagerbeaver.domain.main.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.internal.notify
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainApi: MainApi
) : MainRepository {
    override fun getUserInfo(userId: Long): Flow<Resource<ApplicantUser>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result = mainApi.getUserInfo(userId)
                emit(Resource.Success(result.toApplicantUser()))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun getCategories(): Flow<Resource<List<Category>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result = mainApi.getCategories()
                emit(Resource.Success(result.data.map { it.toCategory() }))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun getJobsWithCategoryId(categoryId: Long): Flow<Resource<List<JobAdvert>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result = mainApi.getCategoryJobs(categoryId)
                emit(Resource.Success(result.toJobList()))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun getAppliedJobs(userId: Long): Flow<Resource<List<JobAdvert>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result = mainApi.getAppliedJobs(userId)
                emit(Resource.Success(result.toJobAdverts()))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun getJobDetail(jobId: Long): Flow<Resource<JobAdvert>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result = mainApi.getJobDetail(jobId)
                emit(Resource.Success(result.toJobAdvert()))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun applyJob(userId: Long, jobId: Long): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                val senderObject =
                    ApplyJobSenderDto(applicant = userId.toInt(), job = jobId.toInt())
                val result = mainApi.applyJob(senderObject)
                if(result.errorMessage != null )
                    emit(Resource.Error(UiText.DynamicString(result.errorMessage)))
                else
                    emit(Resource.Success(true))

            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun getMyAdverts(userId: Long): Flow<Resource<List<JobAdvert>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result  = mainApi.getMyAdvert(userId)
                emit(Resource.Success(result.data.map { it.toJobAdvert() }))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun deleteAdvert(jobId: Long): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result  = mainApi.deleteMyAdvert(jobId)
                emit(Resource.Success(result.isSuccessful))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun updateAdvert(jobId: Long,body:UpdateAdvertDto): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result  = mainApi.updateMyAdvert(jobId,body)
                emit(Resource.Success(result.isSuccessful))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun searchJob(body: SearchSenderDto): Flow<Resource<List<JobAdvert>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val map = HashMap<String,String>()
                body.categoryId?.let {
                    map.put("category_id",it.toString())
                }
                body.jobType?.let {
                    map.put("job_type",it)
                }
                body.title?.let {
                    map.put("title",it)
                }
                val result : SearchReturnDto
                if(map.isEmpty()){
                    map[""] = ""
                    result = mainApi.searchJob(map)
                }else{
                     result  = mainApi.searchJob(map)
                }
                emit(Resource.Success(result.data.map { it.toJobAdvert() }))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun createJob(body: CreateJobSenderDto): Flow<Resource<CreateJobReturnDto>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result  = mainApi.createJob(body)
                emit(Resource.Success(result))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun updateProfile(userId: Long,body: UpdateProfileSenderDto): Flow<Resource<UpdateProfileReturnDto>> {
        return flow {
            try {
                emit(Resource.Loading())
                val result  = mainApi.updateProfile(userId,body)
                emit(Resource.Success(result))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }


}