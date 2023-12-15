package com.sixtyninefourtwenty.stuff.dialogs

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.common.util.concurrent.ListenableFuture
import com.sixtyninefourtwenty.stuff.annotations.BuiltWithDependency
import java.util.concurrent.CompletableFuture

sealed class BaseAsyncResultDialogBuilder<T : BaseAsyncResultDialogBuilder<T, R>, R>(context: Context) {
    protected val delegate = MaterialAlertDialogBuilder(context)
    protected var positiveButtonText: CharSequence? = null
    protected var negativeButtonText: CharSequence? = null
    protected var neutralButtonText: CharSequence? = null
    protected abstract val self: T

    fun setTitle(title: CharSequence?) = self.apply { delegate.setTitle(title) }
    fun setPositiveButton(text: CharSequence?) = self.apply { this.positiveButtonText = text }
    fun setNegativeButton(text: CharSequence?) = self.apply { this.negativeButtonText = text }
    fun setNeutralButton(text: CharSequence?) = self.apply { this.neutralButtonText = text }
    @JvmSynthetic
    abstract suspend fun show(): R
    @RequiresApi(Build.VERSION_CODES.N)
    abstract fun showAsCompletableFuture(): CompletableFuture<R>
    @BuiltWithDependency(
        dependency = "com.google.guava:guava",
        version = "32.1.3-android"
    )
    abstract fun showAsGuavaFuture(): ListenableFuture<R>
    @BuiltWithDependency(
        dependency = "androidx.concurrent:concurrent-futures",
        version = "1.1.0"
    )
    abstract fun showAsAndroidXFuture(): ListenableFuture<R>
    protected suspend fun <T> showInternal(
        resultFunction: (whichButton: Int) -> T
    ) = delegate.showSuspending(positiveButtonText, negativeButtonText, neutralButtonText, resultFunction)
    @RequiresApi(Build.VERSION_CODES.N)
    protected fun <T> showAsCompletableFutureInternal(
        resultFunction: (whichButton: Int) -> T
    ) = delegate.showAsCompletableFuture(positiveButtonText, negativeButtonText, neutralButtonText, resultFunction)
    protected fun <T> showAsGuavaFutureInternal(
        resultFunction: (whichButton: Int) -> T
    ) = delegate.showAsGuavaFuture(positiveButtonText, negativeButtonText, neutralButtonText, resultFunction)
    protected fun <T> showAsAndroidXFutureInternal(
        resultFunction: (whichButton: Int) -> T
    ) = delegate.showAsAndroidXFuture(positiveButtonText, negativeButtonText, neutralButtonText, resultFunction)
}