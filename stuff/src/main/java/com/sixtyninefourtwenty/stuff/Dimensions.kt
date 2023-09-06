@file:JvmName("Dimensions")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.Context
import android.os.Build
import android.util.TypedValue
import androidx.annotation.Dimension
import androidx.annotation.Px
import kotlin.math.roundToInt

@Px
fun Context.dpToPx(@Dimension(unit = Dimension.DP) dp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

@Px
fun Context.dpToPx(@Dimension(unit = Dimension.DP) dp: Int) = dpToPx(dp.toFloat())

@Px
fun Context.spToPx(@Dimension(unit = Dimension.SP) sp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)

@Px
fun Context.spToPx(@Dimension(unit = Dimension.SP) sp: Int) = spToPx(sp.toFloat())

@Dimension(unit = Dimension.DP)
fun Context.pxToDp(@Px px: Float) =
    if (isDeviceOnOrOverSdk(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)) {
        TypedValue.deriveDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
    } else {
        px / resources.displayMetrics.density
    }

@Dimension(unit = Dimension.DP)
fun Context.pxToDp(@Px px: Int) = pxToDp(px.toFloat())

@Dimension(unit = Dimension.SP)
fun Context.pxToSp(@Px px: Float) =
    if (isDeviceOnOrOverSdk(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)) {
        TypedValue.deriveDimension(TypedValue.COMPLEX_UNIT_SP, px, resources.displayMetrics)
    } else {
        @Suppress("DEPRECATION")
        px / resources.displayMetrics.scaledDensity
    }

@Dimension(unit = Dimension.SP)
fun Context.pxToSp(@Px px: Int) = pxToSp(px.toFloat())

enum class DimensionFloatToIntOption(internal val transform: (Float) -> Int) {
    TRUNCATE({ it.toInt() }), ROUND({ it.roundToInt() })
}

@JvmOverloads
@Px
fun Context.dpToPxAsInt(@Dimension(unit = Dimension.DP) dp: Float, option: DimensionFloatToIntOption = DimensionFloatToIntOption.TRUNCATE) =
    option.transform(dpToPx(dp))

@JvmOverloads
@Px
fun Context.dpToPxAsInt(@Dimension(unit = Dimension.DP) dp: Int, option: DimensionFloatToIntOption = DimensionFloatToIntOption.TRUNCATE) =
    dpToPxAsInt(dp.toFloat(), option)

@JvmOverloads
@Px
fun Context.spToPxAsInt(@Dimension(unit = Dimension.SP) dp: Float, option: DimensionFloatToIntOption = DimensionFloatToIntOption.TRUNCATE) =
    option.transform(spToPx(dp))

@JvmOverloads
@Px
fun Context.spToPxAsInt(@Dimension(unit = Dimension.SP) dp: Int, option: DimensionFloatToIntOption = DimensionFloatToIntOption.TRUNCATE) =
    spToPxAsInt(dp.toFloat(), option)

@JvmOverloads
@Dimension(unit = Dimension.DP)
fun Context.pxToDpAsInt(@Px px: Float, option: DimensionFloatToIntOption = DimensionFloatToIntOption.TRUNCATE) =
    option.transform(pxToDp(px))

@JvmOverloads
@Dimension(unit = Dimension.DP)
fun Context.pxToDpAsInt(@Px px: Int, option: DimensionFloatToIntOption = DimensionFloatToIntOption.TRUNCATE) =
    pxToDpAsInt(px.toFloat(), option)

@JvmOverloads
@Dimension(unit = Dimension.SP)
fun Context.pxToSpAsInt(@Px px: Float, option: DimensionFloatToIntOption = DimensionFloatToIntOption.TRUNCATE) =
    option.transform(pxToSp(px))

@JvmOverloads
@Dimension(unit = Dimension.SP)
fun Context.pxToSpAsInt(@Px px: Int, option: DimensionFloatToIntOption = DimensionFloatToIntOption.TRUNCATE) =
    pxToSpAsInt(px.toFloat(), option)
