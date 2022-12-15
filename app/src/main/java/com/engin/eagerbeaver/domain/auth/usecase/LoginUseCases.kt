package com.engin.eagerbeaver.domain.auth.usecase

import javax.inject.Inject

class LoginUseCases @Inject constructor (
    val validateUser: ValidateUser,
)