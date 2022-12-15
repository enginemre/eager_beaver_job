package com.engin.eagerbeaver.common

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.engin.eagerbeaver.R
import com.google.android.material.snackbar.Snackbar

object CustomSnackBar {

    /**
     *
     *
     * @param text message of snack bar
     * @param duration duration of snack bar
     * @param type type of Snack bar [SnackType]
     *
     *
     * @return [Snackbar]
     *
     */
    fun make(
        context: Context,
        view: View,
        text: String,
        type: SnackType,
        duration: Int = Snackbar.LENGTH_LONG
    ): Snackbar {
        return when (type) {
            SnackType.SUCCESS -> {
                Snackbar.make(
                    view,
                    text,
                    duration
                ).setIcon(
                    ContextCompat.getDrawable(context, R.drawable.ic_check)!!,
                    ContextCompat.getColor(context, R.color.white)
                )
                    .setBackgroundTint(
                        ContextCompat.getColor(
                            context,
                            android.R.color.holo_green_light
                        )
                    )
                    .setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            SnackType.ERROR -> {
                Snackbar.make(
                    view,
                    text,
                    duration
                ).setIcon(
                    ContextCompat.getDrawable(context, R.drawable.ic_error)!!,
                    ContextCompat.getColor(context, R.color.white)
                ).setBackgroundTint(ContextCompat.getColor(context, android.R.color.holo_red_dark))
                    .setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            SnackType.WARNING -> {
                Snackbar.make(
                    view,
                    text,
                    duration
                ).setIcon(
                    ContextCompat.getDrawable(context, R.drawable.ic_warning)!!,
                    ContextCompat.getColor(context, R.color.white)
                ).setBackgroundTint(
                    ContextCompat.getColor(
                        context,
                        android.R.color.holo_orange_light
                    )
                )
                    .setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }


    }
}

enum class SnackType {
    SUCCESS,
    ERROR,
    WARNING
}