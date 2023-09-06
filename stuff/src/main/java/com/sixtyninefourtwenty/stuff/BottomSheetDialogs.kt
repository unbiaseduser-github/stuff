@file:JvmName("BottomSheetDialogs")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

val BottomSheetDialogFragment.bottomSheetDialog: BottomSheetDialog?
    get() = dialog as BottomSheetDialog?

fun BottomSheetDialogFragment.requireBottomSheetDialog() = requireDialog() as BottomSheetDialog
