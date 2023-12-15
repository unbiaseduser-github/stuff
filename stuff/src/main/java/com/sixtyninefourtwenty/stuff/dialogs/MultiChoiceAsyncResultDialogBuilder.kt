@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff.dialogs

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.common.util.concurrent.ListenableFuture
import com.sixtyninefourtwenty.stuff.annotations.BuiltWithDependency
import java.util.concurrent.CompletableFuture

class MultiChoiceAsyncResultDialogBuilder(context: Context) : BaseAsyncResultDialogBuilder<MultiChoiceAsyncResultDialogBuilder, MultiChoiceDialogResult>(context) {

    override val self: MultiChoiceAsyncResultDialogBuilder = this

    private val itemIndices = mutableSetOf<Int>()

    fun setItems(
        items: Array<CharSequence>?,
        checkedItems: BooleanArray?
    ) = apply {

        fun addOrRemoveIndex(index: Int, b: Boolean) {
            if (b) {
                itemIndices.add(index)
            } else {
                itemIndices.remove(index)
            }
        }

        checkedItems?.forEachIndexed(::addOrRemoveIndex)
        delegate.setMultiChoiceItems(items, checkedItems) { _, which, isChecked ->
            addOrRemoveIndex(which, isChecked)
        }
    }

    @JvmSynthetic
    override suspend fun show(): MultiChoiceDialogResult = showInternal(::createResult)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun showAsCompletableFuture(): CompletableFuture<MultiChoiceDialogResult> = showAsCompletableFutureInternal(::createResult)

    @BuiltWithDependency(
        dependency = "com.google.guava:guava",
        version = "32.1.3-android"
    )
    override fun showAsGuavaFuture(): ListenableFuture<MultiChoiceDialogResult> = showAsGuavaFutureInternal(::createResult)

    @BuiltWithDependency(
        dependency = "androidx.concurrent:concurrent-futures",
        version = "1.1.0"
    )
    override fun showAsAndroidXFuture(): ListenableFuture<MultiChoiceDialogResult> = showAsAndroidXFutureInternal(::createResult)

    private fun createResult(dialogButton: Int) = MultiChoiceDialogResult(
        dialogButton = dialogButton,
        itemIndices = itemIndices
    )

}

class MultiChoiceDialogResult internal constructor(
    val dialogButton: Int,
    val itemIndices: Set<Int>
)
