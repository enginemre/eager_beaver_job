package com.engin.eagerbeaver.common.domain.model

data class EmployeeUser(
    override var name: String,
    override var userEmail: String,
    override var userName: String,
    override var userType: UserRole= UserRole.EMPLOYEE,
    var imageUrl:String = "",
    var adverts:List<JobAdvert> = emptyList(),
    override var id: Int,
): User{

}
