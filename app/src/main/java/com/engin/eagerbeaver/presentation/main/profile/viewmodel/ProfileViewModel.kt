package com.engin.eagerbeaver.presentation.main.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.domain.main.usecase.GetUserInfoUseCase
import com.engin.eagerbeaver.domain.main.usecase.UpdateProfileUseCase
import com.engin.eagerbeaver.presentation.main.profile.ProfileState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferences: Preferences,
   val getUserInfoUseCase: GetUserInfoUseCase,
    val updateProfileUseCase: UpdateProfileUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private var userInfoJob : Job? = null
    private var editUserJob:Job?= null

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        userInfoJob?.cancel()
        userInfoJob = getUserInfoUseCase(preferences.getUserID()).onEach { resource ->
            when(resource){
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = resource.message
                        )
                    }
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Success -> {
                    resource.data?.let {data->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                user =data
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    fun editProfile(description:String,email:String,fullName:String,title:String,username:String){
        editUserJob?.cancel()
        editUserJob = updateProfileUseCase(userId = preferences.getUserID(),
            description = description,
            email = email,
            fullName = fullName,
            title = title,
            username = username ).onEach { resource->
                when(resource){
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = resource.message
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is Resource.Success -> {
                        resource.data?.let { data->
                            if (data.data != null && data.data == "Ok"){
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                    )
                                }
                                getUserInfo()
                            }else{
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = resource.message
                                    )
                                }
                            }
                        }
                    }
                }
        }.launchIn(viewModelScope)
    }

    fun messageShown(){
        _state.update {
            it.copy(
                errorMessage = null,
                warningMessage = null,
            )
        }
    }

    fun logout() {
        preferences.saveLogin(false)
        preferences.removeUser()
        _state.update {
            it.copy(
                route = Route.Login()
            )
        }
    }

    fun navigated() {
        _state.update {
            it.copy(
                route = null
            )
        }
    }


}