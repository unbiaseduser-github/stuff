@file:JvmName("Contexts")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.RemoteViews
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import java.util.function.Consumer

fun Context.startActivity(intent: Intent, exceptionHandler: Consumer<in ActivityNotFoundException>) {
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        exceptionHandler.accept(e)
    }
}

fun Context.newRemoteViews(@LayoutRes layoutRes: Int) = RemoteViews(packageName, layoutRes)

fun <T : Application> Context.asApplication(clazz: Class<T>) =
    if (clazz.isInstance(this)) clazz.cast(this)!! else clazz.cast(applicationContext)!!

inline fun <reified T : Application> Context.asApplication() = asApplication(T::class.java)

fun Context.isAppInDarkMode(): Boolean {
    return when (AppCompatDelegate.getDefaultNightMode()) {
        AppCompatDelegate.MODE_NIGHT_YES -> true
        AppCompatDelegate.MODE_NIGHT_NO -> false
        else -> resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}
