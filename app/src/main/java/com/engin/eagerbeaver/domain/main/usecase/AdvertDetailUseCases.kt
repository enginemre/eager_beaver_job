package com.engin.eagerbeaver.domain.main.usecase

import javax.inject.Inject

class AdvertDetailUseCases @Inject constructor(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getJobInfoByIdUseCase: GetJobInfoByIdUseCase,
    val deleteAdvertUseCase: DeleteAdvertUseCase,
    val updateAdvertUseCase: UpdateAdvertUseCase,
    val createJobUseCase: CreateJobUseCase,
    val getUserInfoUseCase: GetUserInfoUseCase
)