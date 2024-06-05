package com.sixtyninefourtwenty.stuff.dialogs

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sixtyninefourtwenty.stuff.setToFullWidth

/**
 * [MaterialAlertDialogBuilder] that automatically calls [Dialog.setToFullWidth] on [create].
 */
@Suppress("unused")
open class FullWidthAlertDialogBuilder : MaterialAlertDialogBuilder {

    constructor(context: Context) : super(context)

    constructor(context: Context, overrideThemeResId: Int) : super(context, overrideThemeResId)

    override fun create(): AlertDialog {
        return super.create().apply {
            setToFullWidth()
        }
    }

}