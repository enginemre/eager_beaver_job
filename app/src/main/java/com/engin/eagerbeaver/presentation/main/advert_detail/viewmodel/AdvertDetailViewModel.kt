package com.engin.eagerbeaver.presentation.main.advert_detail.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.eagerbeaver.common.domain.model.*
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.common.presentation.util.UiText
import com.engin.eagerbeaver.domain.main.usecase.AdvertDetailUseCases
import com.engin.eagerbeaver.presentation.main.advert_detail.AdvertDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class AdvertDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val advertDetailUseCases: AdvertDetailUseCases,
    private val preferences: Preferences
) : ViewModel() {

    private var _state = MutableStateFlow(AdvertDetailState())
    val state = _state.asStateFlow()

    private var _channel = Channel<JobAdvert?>()
    val channel = _channel.receiveAsFlow()
    private val advertId: Long? = savedStateHandle["advert_id"]

    private var getCategoryJob: Job? = null
    private var getJobInfo: Job? = null
    private var deleteAdvertJob: Job? = null
    private var createAdvertJob: Job? = null
    private var updateAdvertJob: Job? = null
    private var getUserInfoJob: Job? = null

    init {
        getCategories()
        getAdvertDetail(advertId ?: 0L)
    }

    fun messageShown() {
        _state.update {
            it.copy(
                errorMessage = null,
                warningMessage = null
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



    private fun getCategories() {
        getCategoryJob?.cancel()
        getCategoryJob = advertDetailUseCases.getCategoriesUseCase().onEach { resource ->
            when (resource) {
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
                            isLoading = true,
                        )
                    }
                }
                is Resource.Success -> {
                    resource.data?.let { data ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                categoriesList = data
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)

    }

    @SuppressLint("SimpleDateFormat")
    fun updateAdvert(
        title: String,
        jobPosition: JobPosition,
        type: JobType,
        category: Category,
        salary: Int,
        description: String
    ) {
        var applicantUser: ApplicantUser = ApplicantUser(id = 0)
        getUserInfoJob?.cancel()
        getUserInfoJob = advertDetailUseCases.getUserInfoUseCase(
            preferences.getUserID()
        ).onEach { resource ->
            when (resource) {
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
                    resource.data?.let { data ->
                        applicantUser = data
                    }
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
        val pattern = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(Date())
        val advert = JobAdvert(
            company = applicantUser,
            title = title,
            position = jobPosition,
            type = type,
            category = category,
            salary = salary,
            description = description,
            cratedDate = date,
            companyImageUrl = applicantUser.imageUrl,
            id = 0L
        )
        updateAdvertJob?.cancel()
        updateAdvertJob = advertDetailUseCases.updateAdvertUseCase(
            categoryId = advert.category.id,
            description = advert.description,
            jobType = advert.type,
            position = advert.position,
            salary = advert.salary,
            title = advert.title,
            jobId = advertId!!,
            userId = preferences.getUserID()
        ).onEach { resource ->
            when (resource) {
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
                            isLoading = true,
                        )
                    }
                }
                is Resource.Success -> {
                    resource.data?.let { data ->
                        if (data) {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    route = Route.MyAdverts(isRefresh = true)
                                )
                            }
                        } else {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = UiText.DynamicString("İlan güncellenemedi.")
                                )
                            }
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)

    }


    @SuppressLint("SimpleDateFormat")
    fun createAdvert(
        title: String,
        jobPosition: JobPosition,
        type: JobType,
        category: Category,
        salary: Int,
        description: String
    ) {
        var applicantUser: ApplicantUser = ApplicantUser(id = 0)
        getUserInfoJob?.cancel()
        getUserInfoJob = advertDetailUseCases.getUserInfoUseCase(
            preferences.getUserID()
        ).onEach { resource ->
            when (resource) {
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
                    resource.data?.let { data ->
                        applicantUser = data
                    }
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
        val pattern = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(Date())
        val advert = JobAdvert(
            company = applicantUser,
            title = title,
            position = jobPosition,
            type = type,
            category = category,
            salary = salary,
            description = description,
            cratedDate = date,
            companyImageUrl = applicantUser.imageUrl,
            id = 0L
        )
        createAdvertJob?.cancel()
        createAdvertJob = advertDetailUseCases.createJobUseCase(
            category_id = advert.category.id,
            description = advert.description,
            jobType = advert.type,
            position = advert.position,
            salary = advert.salary,
            title = advert.title
        ).onEach { resource ->
            when (resource) {
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
                            isLoading = true,
                        )
                    }
                }
                is Resource.Success -> {
                    resource.data?.let { data ->
                        if (data.data.id > 0){
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    route = Route.MyAdverts(isRefresh = true)
                                )
                            }
                        }

                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getAdvertDetail(id: Long) {
        if (id != 0L) {
            getJobInfo?.cancel()
            getJobInfo = advertDetailUseCases.getJobInfoByIdUseCase(id).onEach { resource ->
                when (resource) {
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
                        resource.data?.let { data ->
                            _channel.send(data)
                        }
                        _state.update {
                            it.copy(
                                isLoading = false,
                            )
                        }

                    }
                }
            }.launchIn(viewModelScope)
        }

    }

    fun deleteAdvert() {
        if (advertId != null && advertId != 0L) {
            deleteAdvertJob?.cancel()
            deleteAdvertJob =
                advertDetailUseCases.deleteAdvertUseCase(advertId).onEach { resource ->
                    when (resource) {
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
                            if (resource.data != null && resource.data) {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        route = Route.MyAdverts(isRefresh = true)
                                    )
                                }
                            } else {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = UiText.DynamicString("İlan Silinemedi")
                                    )
                                }
                            }

                        }
                    }
                }.launchIn(viewModelScope)
        }


    }

}