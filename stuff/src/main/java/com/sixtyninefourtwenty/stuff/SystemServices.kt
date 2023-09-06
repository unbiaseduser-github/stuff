@file:JvmName("SystemServices")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStatsManager
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalTime
import java.time.ZonedDateTime

val Context.clipboardManager: ClipboardManager
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

@get:RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
val Context.usageStatsManager: UsageStatsManager
    get() = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

@get:RequiresApi(Build.VERSION_CODES.O)
val Context.storageStatsManager: StorageStatsManager
    get() = getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager

val Context.inputMethodManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun InputMethodManager.hideKeyboard(view: View) =
    hideSoftInputFromWindow(view.windowToken, 0)

val Context.alarmManager: AlarmManager
    get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

@JvmOverloads
fun AlarmManager.setRepeatingDaily(
    type: Int = AlarmManager.RTC_WAKEUP,
    triggerAt: LocalTime,
    operation: PendingIntent
) = setRepeating(
    type,
    ZonedDateTime.now().withHour(triggerAt.hour).withMinute(triggerAt.minute).toInstant().toEpochMilli(),
    Duration.ofDays(1).toMillis(),
    operation
)

val Context.notificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
