@file:JvmName("Snackbars")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

@JvmOverloads
fun View.makeSnackbar(text: CharSequence, duration: Int = BaseTransientBottomBar.LENGTH_SHORT) =
    Snackbar.make(this, text, duration)

@JvmOverloads
fun View.makeSnackbar(@StringRes textRes: Int, duration: Int = BaseTransientBottomBar.LENGTH_SHORT) =
    Snackbar.make(this, textRes, duration)

@JvmOverloads
fun View.makeSnackbar(context: Context, text: CharSequence, duration: Int = BaseTransientBottomBar.LENGTH_SHORT) =
    Snackbar.make(context, this, text, duration)
