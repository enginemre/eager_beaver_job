package com.engin.eagerbeaver.data.main.mapper

import com.engin.eagerbeaver.common.domain.model.*
import com.engin.eagerbeaver.data.main.remote.dto.*
import java.util.*

fun ProfileBodyDto.toApplicantUser() : ApplicantUser{
    return ApplicantUser(
        name = this.data.fullName,
        userEmail = this.email,
        userType = if(this.userType.startsWith("A")) UserRole.APPLICANT else UserRole.EMPLOYEE,
        userName = this.data.user,
        imageUrl =  this.data.image ?: "https://cdn.pixabay.com/photo/2012/04/26/19/43/profile-42914__340.png",
        birthDate = Date(),
        description = this.data.description ?: "",
        category = this.data.interestedWith?.map { it.id } ?: listOf(1),
        title = this.data.title ?:"",
        id = this.data.id
    )
}

fun CategoryReturnDto.toCategoryList() : List<Category>{
    return  this.data.map { Category(
        categoryName = it.categoryName,
        id = it.id.toLong(),
        categoryImageUrl = it.image
    ) }

}

fun JobsWithCategoryIdDto.toJobList() :List<JobAdvert>{
    return this.data.map {
        JobAdvert(
            title = it.title,
            position = JobPosition.toPosition(it.position),
            salary = it.salary,
            description = it.description,
            type = JobType.toType(it.jobType),
            cratedDate = it.datePublished,
            companyImageUrl = it.employer.image,
            id = it.id.toLong(),
            category = it.category.toCategory(),
            company = it.employer.toUser(),
        )
    }
}


fun DataCategoryDto.toCategory() : Category{
    return Category(categoryName = categoryName, id = id.toLong(), categoryImageUrl = image)
}

fun EmployerDto.toUser() : EmployeeUser {
    return  EmployeeUser(
        name = fullName,
        userEmail = user.email,
        userName = user.username,
        userType = UserRole.EMPLOYEE,
        imageUrl = image,
        id = id
    )
}

fun AppliedJobReturnDto.toJobAdverts() : List<JobAdvert>{
    return this.data.map {
        JobAdvert(
            title = it.job.title,
            position = JobPosition.toPosition(it.job.position),
            type =  JobType.toType(it.job.jobType),
            salary = it.job.salary,
            cratedDate = it.job.datePublished,
            description = it.job.description,
            companyImageUrl = it.job_image,
            company = it.job.employer.toUser(),
            status = it.status,
            category = it.job.category.toCategory(),
            id = it.id.toLong()
        )
    }
}

fun DataJobDetailDto.toJobAdvert(image:String) : JobAdvert {
    return  JobAdvert(
        id = this.id.toLong(),
        title = title,
        position = JobPosition.toPosition(position),
        company = employer.toUser(),
        type = JobType.toType(jobType),
        category = category.toCategory(),
        salary = this.salary,
        description = description,
        cratedDate = this.datePublished,
        companyImageUrl = image,
    )
}
fun JobDetailsReturnDto.toJobAdvert() : JobAdvert{
    return  this.data.toJobAdvert(this.companyImage)
}

fun DataMyAdvertsDto.toJobAdvert() : JobAdvert{
    return JobAdvert(
        id = id.toLong(),
        title = title,
        position = JobPosition.toPosition(position),
        type = JobType.toType(jobType),
        category = category.toCategory(),
        salary = salary,
        description = description,
        cratedDate = datePublished,
        company = employer.toUser(),
        companyImageUrl = employer.image
    )
}


fun DataSearchDto.toJobAdvert() : JobAdvert {
    return JobAdvert(
        id = id.toLong(),
        title = title,
        position = JobPosition.toPosition(position),
        type = JobType.toType(jobType),
        companyImageUrl = employer.image,
        category = category.toCategory(),
        company = employer.toUser(),
        salary = salary,
        description = description,
        cratedDate = datePublished,
    )
}