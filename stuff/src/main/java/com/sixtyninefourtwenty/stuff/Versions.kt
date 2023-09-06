@file:JvmName("Versions")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(parameter = 0)
fun isDeviceOnOrOverSdk(sdkInt: Int) = Build.VERSION.SDK_INT >= sdkInt

fun isDeviceUnderSdk(sdkInt: Int) = !isDeviceOnOrOverSdk(sdkInt)
