@file:JvmName("Toasts")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

@JvmOverloads
fun Context.makeToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT): Toast =
    Toast.makeText(this, text, duration)

@JvmOverloads
fun Context.makeToast(@StringRes textRes: Int, duration: Int = Toast.LENGTH_SHORT): Toast =
    Toast.makeText(this, textRes, duration)
