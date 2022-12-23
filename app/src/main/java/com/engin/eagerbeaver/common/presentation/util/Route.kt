package com.engin.eagerbeaver.common.presentation.util

sealed class Route {
    data class Register(val route:String = "/register"):Route()
    data class Login(val route:String = "/login"):Route()
    data class Home (val route:String ="/home"):Route()
    data class JobDetail (val route:String ="/job_detail",val jobId:Long= 0L ):Route()
    data class Jobs (val route:String = "/jobs",val category_id:Long = 0L):Route()
    data class Profile (val route:String = "/profile"):Route()
    data class MyApplicant (val route:String ="/my_applicants"):Route()
    data class JobEdit (val route:String = "/job_edit"):Route()
    data class MyAdverts(val route:String = "/adverts",val isRefresh:Boolean):Route()
    data class AdvertEdit(val route: String = "/advert_edit",val advert_id:Long = 0L):Route()

}