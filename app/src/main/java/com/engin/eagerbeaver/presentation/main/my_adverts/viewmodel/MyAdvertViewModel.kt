package com.engin.eagerbeaver.presentation.main.my_adverts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.eagerbeaver.common.domain.model.*
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.domain.main.usecase.MyAdvertsUseCases
import com.engin.eagerbeaver.presentation.main.home.components.JobCardListener
import com.engin.eagerbeaver.presentation.main.my_adverts.MyAdvertState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MyAdvertViewModel @Inject constructor(
    private val preferences: Preferences,
    private val myAdvertsUseCases: MyAdvertsUseCases,
) : ViewModel(),JobCardListener {

    private var _state = MutableStateFlow(MyAdvertState())
    val state = _state.asStateFlow()

    private var getMyAdvertJob: Job? = null

    init {
        getMyAdverts()
    }

    fun messageShown(){
        _state.update {
            it.copy(
                warningMessage = null,
                errorMessage = null
            )
        }
    }

    fun navigated(){
        _state.update {
            it.copy(
                route = null
            )
        }
    }


   private fun getMyAdverts(){
       getMyAdvertJob?.cancel()
       getMyAdvertJob = myAdvertsUseCases.getMyAdvertsById(userId = preferences.getUserID()).onEach {resource->
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
                               myAdvertLists = data
                           )
                       }
                   }
               }
           }
       }.launchIn(viewModelScope)
        // TODO Get adverts
        /*_state.update {
            it.copy(
                isLoading = false,
                myAdvertLists = listOf(
                    JobAdvert(
                        EmployeeUser(name = "Apple","apple@gmail.com","apple_cmp",
                            UserRole.EMPLOYEE),
                        "iOS Developer",
                        JobPosition.Senior,
                        type = JobType.FullTime,
                        Category("Bilgi Teknolojileri",1L,"https://cdn-icons-png.flaticon.com/512/6062/6062646.png"),
                        salary = 12600,
                        description = "A technology client is looking for a remote Android developer responsible for the development and maintenance of applications aimed at a vast number of diverse Android devices.\n" +
                                "\n" +
                                "Responsibility\n" +
                                "\n" +
                                "Design and build advanced applications for the Android platform.\n" +
                                "Collaborate with cross-functional teams to define, design, and ship new features.\n" +
                                "Unit-test code for robustness, including edge cases, usability, and general reliability.\n" +
                                "Work on bug fixing and improving application performance.\n" +
                                "Continuously discover, evaluate, and implement new technologies to maximize development efficiency.\n" +
                                "\n" +
                                "Requirements\n" +
                                "\n" +
                                "Strong knowledge of Android SDK, different versions of Android, and how to deal with different screen sizes.\n" +
                                "Familiarity with RESTful APIs to connect Android applications to back-end services.\n" +
                                "Strong knowledge of Android UI design principles, patterns, and best practices.\n" +
                                "Knowledge of the open-source Android ecosystem and the libraries available for common tasks.\n" +
                                "Familiarity with cloud message APIs and push notifications.\n" +
                                "Understanding of Google’s Android design principles and interface guidelines.\n" +
                                "\n" +
                                "About Hire Digital\n" +
                                "\n" +
                                "Hire Digital helps enterprises and growth companies build and enhance their digital capabilities with a world-class network of digital marketers, developers, and designers. We have enabled companies like Philips, 3M, Roche, AXA, Unilever, and many more to reduce overheads and boost efficiency.",
                        cratedDate ="Oluşturma Tarihi :" +  Date().toString(),
                        companyImageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/150px-Apple_logo_black.svg.png",
                        id = 1L
                    )
                )
            )
        }*/
    }

    override fun onCardClick(id: Long) {
        _state.update {
            it.copy(
                route = Route.AdvertEdit(advert_id = id)
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
}