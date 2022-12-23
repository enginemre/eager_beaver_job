package com.engin.eagerbeaver.presentation.main.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.eagerbeaver.common.domain.model.*
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.domain.main.usecase.HomeUseCases
import com.engin.eagerbeaver.presentation.main.home.HomeState
import com.engin.eagerbeaver.presentation.main.home.components.CategoryCardListener
import com.engin.eagerbeaver.presentation.main.home.components.JobCardListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel(){

    private var _state:MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state:StateFlow<HomeState> = _state.asStateFlow()

    private var getCategoryJob: Job? =null
    private var getJobs:Job?= null

    init {
        getCategories()
        getLastJobs()
    }

    private fun getCategories(){
        getCategoryJob?.cancel()
        getCategoryJob = useCases.getCategoriesUseCase().onEach {resource ->
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
                                categories =data
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)

      /*val listOfCategories = listOf<Category>(
            Category("Bilgi Teknolojileri",1L,"https://cdn-icons-png.flaticon.com/512/6062/6062646.png"),
            Category("UI/UX Tasarım",2L,"https://cdn-icons-png.flaticon.com/512/1055/1055666.png"),
            Category("Temilik",3L,"https://cdn-icons-png.flaticon.com/512/2954/2954884.png"),
            Category("İnsan Kaynakları",4L,"https://cdn-icons-png.flaticon.com/512/4490/4490380.png"),
            Category("Pazarlama",4L,"https://cdn-icons-png.flaticon.com/512/4490/4490380.png"),
            Category("Nakliyat",4L,"https://cdn-icons-png.flaticon.com/512/4196/4196451.png"),
        )*/
    }

    private fun getLastJobs(){
        getJobs?.cancel()
        getJobs = useCases.getJobsWithCategoryId(2).onEach { resource ->
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
                                lastAdverts =data
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
       /* val users = listOf<EmployeeUser>(
            EmployeeUser(name = "Apple","apple@gmail.com","apple_cmp",UserRole.EMPLOYEE),
            EmployeeUser(name = "Goolge","goolge@gmail.com","goolge_cmp",UserRole.EMPLOYEE),
        )
        val listOfJobs = listOf<JobAdvert>(
            JobAdvert(
                users[0],
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
            ),
            JobAdvert(
                users[1],
                "iOS Developer",
                JobPosition.Junior,
                type = JobType.Intern,
                Category("Bilgi Teknolojileri",1L,"https://cdn-icons-png.flaticon.com/512/6062/6062646.png"),
                salary = 12600,
                description = "We are looking for Senior iOS and/or Android Developer working on our mobile\n" +
                        "\n" +
                        "applications.\n" +
                        "\n" +
                        "\n" +
                        "Senior iOS Developer\n" +
                        "\n" +
                        "\n" +
                        "• Bachelor's degree in Computer Science/Engineering or relevant areas.\n" +
                        "\n" +
                        "• 3-5+ years experience with iOS in Swift.\n" +
                        "\n" +
                        "• Focusing on UI design and a user-oriented programming.\n" +
                        "\n" +
                        "• Familiarity with OOP and design patterns.\n" +
                        "\n" +
                        "• Experience on Git and CI.\n" +
                        "\n" +
                        "• Experience with REST, SOAP and JSON.\n" +
                        "\n" +
                        "• Experience with deploying applications on App Store.\n" +
                        "\n" +
                        "• Experience with Xcode.\n" +
                        "\n" +
                        "• Jenkins and/or fastlane knowledge is a big plus.\n" +
                        "\n" +
                        "• Experience in Unit testing.\n" +
                        "\n" +
                        "• Understanding of iOS design principles and interface guidelines.\n" +
                        "\n" +
                        "• Knowledge of SwiftUI.\n" +
                        "\n" +
                        "• Experience in Firebase features.\n" +
                        "\n" +
                        "• Understanding of development methodologies Agile and Waterfall.\n" +
                        "\n" +
                        "\n" +
                        "Senior Android Developer\n" +
                        "\n" +
                        "\n" +
                        "• Bachelor's degree in Computer Science/Engineering or relevant areas.\n" +
                        "\n" +
                        "• 3-5+ years experience with Android in Kotlin and Java.\n" +
                        "\n" +
                        "• Focusing on UI design and a user-oriented programming.\n" +
                        "\n" +
                        "• Familiarity with OOP and design patterns.\n" +
                        "\n" +
                        "• Experience on Git and CI.\n" +
                        "\n" +
                        "• Experience with REST, SOAP and JSON.\n" +
                        "\n" +
                        "• Experience in deploying applications on Google Play Store and Huawei App\n" +
                        "\n" +
                        "Gallery.\n" +
                        "\n" +
                        "• Experience with Android SDK and Huawei SDK.\n" +
                        "\n" +
                        "• Jenkins and/or fastlane knowledge.\n" +
                        "\n" +
                        "• Experience in Unit testing.\n" +
                        "\n" +
                        "• Understanding of Android design principles and interface guidelines.\n" +
                        "\n" +
                        "• Knowledge of Jetpack Components.\n" +
                        "\n" +
                        "• Experience with Firebase features.\n" +
                        "\n" +
                        "• Understanding of development methodologies Agile and Waterfall.\n" +
                        "\n" +
                        "\n" +
                        "SKILLS\n" +
                        "\n" +
                        "\n" +
                        "• Good at analytical thinking, result-oriented, team-oriented, and openminded.\n" +
                        "\n" +
                        "• Passionate about new technologies.\n" +
                        "\n" +
                        "\n" +
                        "(Hybrid Working Model)\n" +
                        "\n" +
                        "\n" +
                        "ABOUT US\n" +
                        "\n" +
                        "Established in 1991, Gedik Investment is one of the leading investment banking\n" +
                        "\n" +
                        "advisory and brokerage firms in Turkey, providing research and brokerage services\n" +
                        "\n" +
                        "to domestic and foreign clients. The company is a member of Borsa Istanbul\n" +
                        "\n" +
                        "(BIST) and is authorized by the Capital Markets Board (CMB). Today, it has the\n" +
                        "\n" +
                        "largest customer base of any non-bank local brokerage house, with around 58,000\n" +
                        "\n" +
                        "clients and counting. Gedik Investment boasts a widespread distribution network,\n" +
                        "\n" +
                        "with 40 main branches spread across Turkey’s primary cities.\n" +
                        "\n" +
                        "Gedik Investment remains a pioneer in the Turkish market, achieving a number of\n" +
                        "\n" +
                        "firsts. To name just a few, Gedik Investment was first to: use remote access API,\n" +
                        "\n" +
                        "access the Derivatives Exchange of Turkey (VOB), claim an option execution on\n" +
                        "\n" +
                        "VIOP, FIX access to the BIST’s equities and futures/options market (2014), and\n" +
                        "\n" +
                        "access the BIST CoLocation Facility (2014).\n" +
                        "\n" +
                        "Gedik Investment is a listed entity on BIST, trading under the ticker symbol\n" +
                        "\n" +
                        "“GEDIK.” Gedik Investment and its subsidiaries have a Long-Term National\n" +
                        "\n" +
                        "Rating of ‘AA- (Trk)’ with a positive outlook.”",
                cratedDate ="Oluşturma Tarihi :" +  Date().toString(),
                companyImageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_\"G\"_Logo.svg/2048px-Google_\"G\"_Logo.svg.png",
                id = 2L
            )
        )*/
    }

    fun messageShown() {
        _state.update {
            it.copy(
                errorMessage = null,
                warningMessage = null
            )
        }
    }

    fun navigated(){
        _state.update {
            it.copy(
                shouldNavigate = null,
            )
        }
    }

    fun navigateJobs(jobId:Long =0L){
        _state.update {
            it.copy(
                shouldNavigate = if(jobId != 0L)  Route.JobDetail(jobId = jobId) else Route.Jobs()
            )
        }
    }

    private fun navigateCategory(categoryId:Long){
        _state.update {
            it.copy(
                shouldNavigate = Route.Jobs(category_id = categoryId)
            )
        }
    }



    val jobCardListener=  object :JobCardListener{
        override fun onCardClick(id: Long) {
            navigateJobs(jobId = id)
        }
    }

    val categoryCardListener = object : CategoryCardListener {
        override fun onCategoryClicked(id: Long) {
           navigateCategory(categoryId = id)
        }
    }
}