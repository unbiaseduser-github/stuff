@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff.dialogs

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.common.util.concurrent.ListenableFuture
import com.sixtyninefourtwenty.stuff.annotations.BuiltWithDependency
import java.util.concurrent.CompletableFuture

/**
 * Builder to show a single-choice dialog.
 *
 * Result is a [SingleChoiceDialogResult].
 * @see AlertDialog.Builder.setSingleChoiceItems
 */
class SingleChoiceAsyncResultDialogBuilder(context: Context) : BaseAsyncResultDialogBuilder<SingleChoiceAsyncResultDialogBuilder, SingleChoiceDialogResult>(context) {

    override val self: SingleChoiceAsyncResultDialogBuilder = this

    private var itemIndex: Int = -1

    fun setItems(
        items: Array<CharSequence>?,
        checkedItem: Int
    ) = apply {
        itemIndex = checkedItem
        delegate.setSingleChoiceItems(items, checkedItem) { _, which -> itemIndex = which }
    }

    @JvmSynthetic
    override suspend fun show(): SingleChoiceDialogResult = showInternal(::createResult)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun showAsCompletableFuture(): CompletableFuture<SingleChoiceDialogResult> = showAsCompletableFutureInternal(::createResult)

    @BuiltWithDependency(
        dependency = "com.google.guava:guava",
        version = "32.1.3-android"
    )
    override fun showAsGuavaFuture(): ListenableFuture<SingleChoiceDialogResult> = showAsGuavaFutureInternal(::createResult)

    @BuiltWithDependency(
        dependency = "androidx.concurrent:concurrent-futures",
        version = "1.1.0"
    )
    override fun showAsAndroidXFuture(): ListenableFuture<SingleChoiceDialogResult> = showAsAndroidXFutureInternal(::createResult)

    private fun createResult(dialogButton: Int) = SingleChoiceDialogResult(
        dialogButton = dialogButton,
        itemIndex = itemIndex
    )

}

/**
 * Represents the result of a [SingleChoiceAsyncResultDialogBuilder].
 * @property dialogButton One of [DialogInterface.BUTTON_POSITIVE],
 * [DialogInterface.BUTTON_NEGATIVE] and [DialogInterface.BUTTON_NEUTRAL]
 * @property itemIndex Index of the currently checked item, or -1 if no item is checked
 */
class SingleChoiceDialogResult internal constructor(
    val dialogButton: Int,
    val itemIndex: Int
)
