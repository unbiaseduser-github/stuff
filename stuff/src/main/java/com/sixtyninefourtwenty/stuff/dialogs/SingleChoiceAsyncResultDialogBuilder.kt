@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff.dialogs

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.common.util.concurrent.ListenableFuture
import com.sixtyninefourtwenty.stuff.annotations.BuiltWithDependency
import java.util.concurrent.CompletableFuture

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

class SingleChoiceDialogResult internal constructor(
    val dialogButton: Int,
    val itemIndex: Int
)
