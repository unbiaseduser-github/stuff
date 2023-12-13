@file:JvmName("Dialogs")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.google.common.util.concurrent.SettableFuture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.CompletableFuture

class AsyncResultDialogBuilder(context: Context) {

    private val delegate = MaterialAlertDialogBuilder(context)
    private var positiveButtonText: CharSequence? = null
    private var negativeButtonText: CharSequence? = null
    private var neutralButtonText: CharSequence? = null

    fun setTitle(title: CharSequence?) = apply { delegate.setTitle(title) }

    fun setMessage(message: CharSequence?) = apply { delegate.setMessage(message) }

    fun setView(view: View?) = apply { delegate.setView(view) }

    fun setPositiveButton(text: CharSequence?) = apply { this.positiveButtonText = text }

    fun setNegativeButton(text: CharSequence?) = apply { this.negativeButtonText = text }

    fun setNeutralButton(text: CharSequence?) = apply { this.neutralButtonText = text }

    private fun AlertDialog.setButtonsInternal(
        positiveButtonText: CharSequence? = null,
        negativeButtonText: CharSequence? = null,
        neutralButtonText: CharSequence? = null,
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

    @JvmSynthetic
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun show(): Int = suspendCancellableCoroutine { cont ->
        delegate.create().apply {
            val commonListener = DialogInterface.OnClickListener { _, which ->
                cont.resume(which) { dismiss() }
            }
            setButtonsInternal(positiveButtonText, negativeButtonText, neutralButtonText, commonListener)
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
            setButtonsInternal(positiveButtonText, negativeButtonText, neutralButtonText, commonListener)
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

    fun showAsListenableFuture(): ListenableFuture<Int> {
        val future = SettableFuture.create<Int>()
        val dialog = delegate.create().apply {
            val commonListener = DialogInterface.OnClickListener { _, which ->
                future.set(which)
            }
            setButtonsInternal(positiveButtonText, negativeButtonText, neutralButtonText, commonListener)
            setOnCancelListener { future.cancel(false) }
        }
        dialog.show()
        return future.apply {
            addListener({ dialog.dismiss() }, MoreExecutors.directExecutor())
        }
    }

}
