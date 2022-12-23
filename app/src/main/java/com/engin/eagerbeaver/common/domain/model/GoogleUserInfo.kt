package com.engin.eagerbeaver.common.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class GoogleUserInfo(
    var email:String,
    var name : String
) : Parcelable
