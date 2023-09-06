@file:JvmName("Permissions")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

fun Context.doesAppHavePermission(permission: String) = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun Context.doesAppHaveNotificationPermission() = doesAppHavePermission(Manifest.permission.POST_NOTIFICATIONS)
