package com.engin.eagerbeaver.domain.auth.usecase

import javax.inject.Inject

class RegisterUseCases @Inject constructor(
    val validateRegisterUser: ValidateRegisterUser
) {
}