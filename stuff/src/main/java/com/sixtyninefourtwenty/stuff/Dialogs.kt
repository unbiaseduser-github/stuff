@file:JvmName("Dialogs")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.concurrent.futures.CallbackToFutureAdapter
import androidx.concurrent.futures.DirectExecutor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.google.common.util.concurrent.SettableFuture
import com.sixtyninefourtwenty.stuff.annotations.BuiltWithDependency
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.CompletableFuture

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

class RegularAsyncResultDialogBuilder(context: Context) : BaseAsyncResultDialogBuilder<RegularAsyncResultDialogBuilder>(context) {

    override val self: RegularAsyncResultDialogBuilder = this

    fun setMessage(message: CharSequence?) = apply { delegate.setMessage(message) }

    @JvmSynthetic
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun show(): Int = suspendCancellableCoroutine { cont ->
        delegate.create().apply {
            val commonListener = DialogInterface.OnClickListener { _, which ->
                cont.resume(which) { dismiss() }
            }
            setButtonsInternal(commonListener)
            setOnCancelListener { cont.cancel() }
            cont.invokeOnCancellation { dismiss() }
        }.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showAsCompletableFuture(): CompletableFuture<Int> {
        val future = CompletableFuture<Int>()
        val dialog = delegate.create().apply {
            val commonListener = DialogInterface.OnClickListener { _, which ->
                future.complete(which)
            }
            setButtonsInternal(commonListener)
            setOnCancelListener { future.cancel(false) }
        }
        dialog.show()
        future.whenComplete { _: Int?, _: Throwable? -> dialog.dismiss() }
        /*
         * I can't return the second future created with CompletableFuture.whenComplete above -
         * when users cancel that future, the first one won't be canceled which breaks dialog
         * dismissal
         */
        return future
    }

    @BuiltWithDependency(
        dependency = "com.google.guava:guava",
        version = "32.1.3-android"
    )
    fun showAsGuavaFuture(): ListenableFuture<Int> {
        val future = SettableFuture.create<Int>()
        val dialog = delegate.create().apply {
            val commonListener = DialogInterface.OnClickListener { _, which ->
                future.set(which)
            }
            setButtonsInternal(commonListener)
            setOnCancelListener { future.cancel(false) }
        }
        dialog.show()
        return future.apply {
            addListener({ dialog.dismiss() }, MoreExecutors.directExecutor())
        }
    }

    @SuppressLint("RestrictedApi")
    @BuiltWithDependency(
        dependency = "androidx.concurrent:concurrent-futures",
        version = "1.1.0"
    )
    fun showAsAndroidXFuture(): ListenableFuture<Int> {
        return CallbackToFutureAdapter.getFuture { completer ->
            val dialog = delegate.create().apply {
                val commonListener = DialogInterface.OnClickListener { _, which ->
                    completer.set(which)
                }
                setButtonsInternal(commonListener)
                setOnCancelListener { completer.setCancelled() }
            }
            dialog.show()
            completer.addCancellationListener({ dialog.dismiss() }, DirectExecutor.INSTANCE)
        }
    }

}
