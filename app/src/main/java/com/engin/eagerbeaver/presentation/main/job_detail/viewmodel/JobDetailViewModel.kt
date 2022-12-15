package com.engin.eagerbeaver.presentation.main.job_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.common.presentation.util.UiText
import com.engin.eagerbeaver.domain.auth.model.EmployerUser
import com.engin.eagerbeaver.presentation.main.job_detail.JobDetailState
import com.engin.eagerbeaver.presentation.main.job_detail.component.JobDetailClicks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JobDetailViewModel @Inject constructor(
    private val preferences: Preferences,
   private val savedStateHandle: SavedStateHandle
) : ViewModel(),JobDetailClicks{

    private var _state:MutableStateFlow<JobDetailState> = MutableStateFlow(JobDetailState())
    var state:StateFlow<JobDetailState> = _state.asStateFlow()
    private val advertId:Long? = savedStateHandle["job_id"]

    init {
        getJobDetail(advertId)
    }

    private fun getJobDetail(advertId: Long?) {
        if(advertId !=null){
            val advert:JobAdvert
            if(advertId == 1L){
                advert = JobAdvert(
                    EmployerUser(name = "Apple","apple@gmail.com","apple_cmp",
                        UserRole.EMPLOYER, age = 23),
                    "iOS Developer",
                    "Senior",
                    type = "Full-Time",
                    Category("Bilgi Teknolojileri",1L,"https://cdn-icons-png.flaticon.com/512/6062/6062646.png"),
                    salary = 16400,
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
                _state.update {
                    it.copy(
                        jobDetail = advert,
                        isLoading = false
                    )
                }
            }
            else{
                advert = JobAdvert(
                    EmployerUser(name = "Goolge","goolge@gmail.com","goolge_cmp",UserRole.EMPLOYER, age = 43),
                    "iOS Developer",
                    "Junior",
                    type = "Intern",
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
                _state.update {
                    it.copy(
                        jobDetail = advert,
                        isLoading = false
                    )
                }

            }

        }else{
            _state.update {
                it.copy(
                    errorMessage = UiText.DynamicString("Bir Hata oluştu Yeğenim Tekrar dene"),
                    jobDetail = null,
                    isLoading = false
                )
            }
        }

    }
    fun messageShown(){
        _state.update {
            it.copy(
                warningMessage = null,
                errorMessage = null
            )
        }
    }

    private fun applyJob(job:JobAdvert){
        _state.update {
            it.copy(
                warningMessage = UiText.DynamicString("Başvurulan İş ilanı ${job.title}")
            )
        }
    }

    override fun onJobApply(job: JobAdvert) {
        applyJob(job)
    }

    override fun onRetryError() {
//        getJobDetail(advertId)
        _state.update {
            it.copy(
                warningMessage = UiText.DynamicString("Tekrar denenecek")
            )
        }
    }

}