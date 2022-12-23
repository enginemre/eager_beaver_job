package com.engin.eagerbeaver.domain.main.usecase

import javax.inject.Inject

class MyAdvertsUseCases @Inject constructor(
    val getMyAdvertsById: GetMyAdvertsById,
)