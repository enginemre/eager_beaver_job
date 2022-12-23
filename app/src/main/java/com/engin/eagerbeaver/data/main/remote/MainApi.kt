package com.engin.eagerbeaver.data.main.remote

import com.engin.eagerbeaver.data.main.remote.dto.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface MainApi {

    @GET("/accounts/profile/{id}")
    suspend fun getUserInfo(@Path( "id") id : Long) : ProfileBodyDto

    @GET("/api/get_categories/")
    suspend fun getCategories():CategoryReturnDto

    @GET("/api/get_category_jobs/{id}/")
    suspend fun getCategoryJobs(@Path("id") id:Long): JobsWithCategoryIdDto

    @GET("/api/get_applied_jobs/{id}/")
    suspend fun getAppliedJobs(@Path("id") id:Long): AppiedJobsDto

    @POST("/api/create_job/")
    suspend fun createJob(@Body body:CreateJobSenderDto) : CreateJobReturnDto

    @POST("/api/apply_job/")
    suspend fun applyJob(@Body body: ApplyJobSenderDto) : ApplyJobReturnDto

    @GET("/api/job_detail/{id}/")
    suspend fun getJobDetail(@Path("id") id:Long) :JobDetailsReturnDto

    @GET("/api/my_jobs/{id}/")
    suspend fun getMyAdvert(@Path("id") userId:Long):GetMyAdvertsReturnDto

    @PUT("/api/update_job/{id}/")
    suspend fun updateMyAdvert(@Path("id") jobId:Long,@Body body:UpdateAdvertDto): Response<ResponseBody>

    @DELETE("/api/update_job/{id}/")
    suspend fun deleteMyAdvert(@Path("id") jobId:Long) : Response<ResponseBody>

    @POST("/api/search_jobs/")
    suspend fun searchJob(@Body body:SearchSenderDto) :SearchReturnDto



}