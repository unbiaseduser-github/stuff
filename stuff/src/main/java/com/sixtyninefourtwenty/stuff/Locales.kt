@file:JvmName("Locales")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

/**
 * Similar to [obtainAppLocaleNullable], but the [supplier] and this method's return type can't be null
 */
@JvmOverloads
inline fun obtainAppLocale(supplier: () -> Locale = { Locale.getDefault() }) = obtainAppLocaleNullable(supplier)!!

/**
 * @return first app locale previously set by [AppCompatDelegate.setApplicationLocales] if present,
 * else the locale returned by [supplier]
 */
inline fun obtainAppLocaleNullable(supplier: () -> Locale?) = AppCompatDelegate.getApplicationLocales()[0] ?: supplier()
