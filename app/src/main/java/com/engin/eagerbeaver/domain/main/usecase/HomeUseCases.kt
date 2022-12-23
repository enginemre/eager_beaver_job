package com.engin.eagerbeaver.domain.main.usecase

import com.engin.eagerbeaver.domain.auth.repository.AuthRepository
import javax.inject.Inject

class HomeUseCases @Inject constructor(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getJobsWithCategoryId: GetJobsWithCategoryId
)