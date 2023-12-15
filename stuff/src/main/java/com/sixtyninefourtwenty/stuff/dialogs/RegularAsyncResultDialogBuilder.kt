package com.sixtyninefourtwenty.stuff.dialogs

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.common.util.concurrent.ListenableFuture
import com.sixtyninefourtwenty.stuff.annotations.BuiltWithDependency
import java.util.concurrent.CompletableFuture

/**
 * Builder to show a dialog with a simple message.
 *
 * Result is one of [DialogInterface.BUTTON_POSITIVE],
 * [DialogInterface.BUTTON_NEGATIVE] and [DialogInterface.BUTTON_NEUTRAL].
 */
@Suppress("unused")
class RegularAsyncResultDialogBuilder(context: Context) : BaseAsyncResultDialogBuilder<RegularAsyncResultDialogBuilder, Int>(context) {

    override val self: RegularAsyncResultDialogBuilder = this

    fun setMessage(message: CharSequence?) = apply { delegate.setMessage(message) }

    @JvmSynthetic
    override suspend fun show(): Int = showInternal { it }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun showAsCompletableFuture(): CompletableFuture<Int> = showAsCompletableFutureInternal { it }

    @BuiltWithDependency(
        dependency = "com.google.guava:guava",
        version = "32.1.3-android"
    )
    override fun showAsGuavaFuture(): ListenableFuture<Int> = showAsGuavaFutureInternal { it }

    @BuiltWithDependency(
        dependency = "androidx.concurrent:concurrent-futures",
        version = "1.1.0"
    )
    override fun showAsAndroidXFuture(): ListenableFuture<Int> = showAsAndroidXFutureInternal { it }

}
