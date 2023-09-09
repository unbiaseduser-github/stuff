@file:JvmName("BottomSheetDialogs")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

val BottomSheetDialogFragment.bottomSheetDialog: BottomSheetDialog?
    get() = dialog as BottomSheetDialog?

fun BottomSheetDialogFragment.requireBottomSheetDialog() = requireDialog() as BottomSheetDialog

fun BottomSheetDialog.expand() {
    behavior.state = BottomSheetBehavior.STATE_EXPANDED
}

fun BottomSheetDialog.collapse() {
    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
}

fun BottomSheetDialog.hides() {
    behavior.state = BottomSheetBehavior.STATE_HIDDEN
}

fun BottomSheetDialog.halfExpand() {
    behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
}
