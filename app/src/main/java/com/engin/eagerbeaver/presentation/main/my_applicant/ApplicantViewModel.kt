package com.engin.eagerbeaver.presentation.main.my_applicant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.domain.main.usecase.GetAppliedJobsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class ApplicantViewModel @Inject constructor(
    private val getAppliedJobsUseCase: GetAppliedJobsUseCase,
    private val preferences: Preferences
) : ViewModel() {

    private var _state = MutableStateFlow(ApplicantState())
    var state = _state.asStateFlow()

    init {
        getMyApplicant()
    }

    private var applicantJob: Job? = null

    private fun getMyApplicant(){
        // passing guest
        if(preferences.getUserID() != 0L){
            applicantJob?.cancel()
            applicantJob = getAppliedJobsUseCase(preferences.getUserID()).onEach { resource->
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
                                    myApplicant = data
                                )
                            }
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

    fun messageShown(){
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }
}