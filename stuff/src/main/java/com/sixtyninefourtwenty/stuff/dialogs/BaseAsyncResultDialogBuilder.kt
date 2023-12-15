package com.sixtyninefourtwenty.stuff.dialogs

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

sealed class BaseAsyncResultDialogBuilder<T : BaseAsyncResultDialogBuilder<T>>(context: Context) {
    protected val delegate = MaterialAlertDialogBuilder(context)
    protected var positiveButtonText: CharSequence? = null
    protected var negativeButtonText: CharSequence? = null
    protected var neutralButtonText: CharSequence? = null
    protected abstract val self: T

    fun setTitle(title: CharSequence?) = self.apply { delegate.setTitle(title) }
    fun setPositiveButton(text: CharSequence?) = self.apply { this.positiveButtonText = text }
    fun setNegativeButton(text: CharSequence?) = self.apply { this.negativeButtonText = text }
    fun setNeutralButton(text: CharSequence?) = self.apply { this.neutralButtonText = text }
    protected fun AlertDialog.setButtonsInternal(
        commonListener: DialogInterface.OnClickListener
    ) {
        if (positiveButtonText != null) {
            setButton(AlertDialog.BUTTON_POSITIVE, positiveButtonText, commonListener)
        }

        if (negativeButtonText != null) {
            setButton(AlertDialog.BUTTON_NEGATIVE, negativeButtonText, commonListener)
        }

        if (neutralButtonText != null) {
            setButton(AlertDialog.BUTTON_NEUTRAL, neutralButtonText, commonListener)
        }
    }
}