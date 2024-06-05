package com.sixtyninefourtwenty.stuff.dialogs

import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

/**
 * Wrapper for a [AlertDialog.Builder] that offers the ability to disable auto dismissal of created
 * [AlertDialog]s. You have to supply a pre-configured builder (don't call any `setXButton`
 * methods on it) and call the `setXButton` methods on this wrapper.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class NoAutoDismissAlertDialogWrapper(private val wrapped: AlertDialog.Builder) {

    private var autoDismissOnPositive: Boolean = true
    private var positiveClickListener: DialogInterface.OnClickListener? = null
    private var autoDismissOnNegative: Boolean = true
    private var negativeClickListener: DialogInterface.OnClickListener? = null
    private var autoDismissOnNeutral: Boolean = true
    private var neutralClickListener: DialogInterface.OnClickListener? = null

    fun setPositiveButton(
        @StringRes textId: Int,
        listener: DialogInterface.OnClickListener?,
        autoDismissOnPositive: Boolean
    ) = apply {
        wrapped.setPositiveButton(textId, null)
        this.positiveClickListener = listener
        this.autoDismissOnPositive = autoDismissOnPositive
    }

    fun setPositiveButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?,
        autoDismissOnPositive: Boolean
    ) = apply {
        wrapped.setPositiveButton(text, null)
        this.positiveClickListener = listener
        this.autoDismissOnPositive = autoDismissOnPositive
    }

    fun setNegativeButton(
        @StringRes textId: Int,
        listener: DialogInterface.OnClickListener?,
        autoDismissOnNegative: Boolean
    ) = apply {
        wrapped.setNegativeButton(textId, null)
        this.negativeClickListener = listener
        this.autoDismissOnNegative = autoDismissOnNegative
    }

    fun setNegativeButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?,
        autoDismissOnNegative: Boolean
    ) = apply {
        wrapped.setNegativeButton(text, null)
        this.negativeClickListener = listener
        this.autoDismissOnNegative = autoDismissOnNegative
    }

    fun setNeutralButton(
        @StringRes textId: Int,
        listener: DialogInterface.OnClickListener?,
        autoDismissOnNeutral: Boolean
    ) = apply {
        wrapped.setNeutralButton(textId, null)
        this.neutralClickListener = listener
        this.autoDismissOnNeutral = autoDismissOnNeutral
    }

    fun setNeutralButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?,
        autoDismissOnNeutral: Boolean
    ) = apply {
        wrapped.setNeutralButton(text, null)
        this.neutralClickListener = listener
        this.autoDismissOnNeutral = autoDismissOnNeutral
    }

    fun create() = wrapped.create().apply {
        setOnShowListener { di ->
            getButton(DialogInterface.BUTTON_POSITIVE)?.setOnClickListener { _ ->
                positiveClickListener?.onClick(di, DialogInterface.BUTTON_POSITIVE)
                if (autoDismissOnPositive) {
                    dismiss()
                }
            }
            getButton(DialogInterface.BUTTON_NEGATIVE)?.setOnClickListener { _ ->
                negativeClickListener?.onClick(di, DialogInterface.BUTTON_NEGATIVE)
                if (autoDismissOnNegative) {
                    dismiss()
                }
            }
            getButton(DialogInterface.BUTTON_NEUTRAL)?.setOnClickListener { _ ->
                neutralClickListener?.onClick(di, DialogInterface.BUTTON_NEUTRAL)
                if (autoDismissOnNeutral) {
                    dismiss()
                }
            }
        }
    }

    fun show() = create().also { it.show() }

}