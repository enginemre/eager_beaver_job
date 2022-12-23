package com.engin.eagerbeaver.domain.main.usecase

import javax.inject.Inject

class JobDetailUseCases @Inject constructor(
    val getJobInfoByIdUseCase: GetJobInfoByIdUseCase,
    val applyJobUseCase: ApplyJobUseCase
)