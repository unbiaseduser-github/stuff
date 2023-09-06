@file:JvmName("Locales")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

fun obtainAppLocale(): Locale = AppCompatDelegate.getApplicationLocales()[0] ?: Locale.getDefault()
