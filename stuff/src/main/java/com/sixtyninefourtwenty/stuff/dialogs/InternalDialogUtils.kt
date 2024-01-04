@file:JvmSynthetic

package com.sixtyninefourtwenty.stuff.dialogs

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.concurrent.futures.CallbackToFutureAdapter
import androidx.concurrent.futures.DirectExecutor
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.google.common.util.concurrent.SettableFuture
import com.sixtyninefourtwenty.stuff.annotations.BuiltWithDependency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture

internal fun AlertDialog.setButtons(
    positiveButtonText: CharSequence?,
    negativeButtonText: CharSequence?,
    neutralButtonText: CharSequence?,
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

@OptIn(ExperimentalCoroutinesApi::class)
internal suspend fun <T> AlertDialog.Builder.showSuspending(
    positiveButtonText: CharSequence?,
    negativeButtonText: CharSequence?,
    neutralButtonText: CharSequence?,
    resultFunction: (whichButton: Int) -> T
): T = withContext(Dispatchers.Main.immediate) {
    suspendCancellableCoroutine { cont ->
        create().apply {
            val commonListener = DialogInterface.OnClickListener { _, which ->
                cont.resume(resultFunction(which)) { dismiss() }
            }
            setButtons(positiveButtonText, negativeButtonText, neutralButtonText, commonListener)
            setOnCancelListener { cont.cancel() }
            cont.invokeOnCancellation { dismiss() }
        }.show()
    }
}

@RequiresApi(Build.VERSION_CODES.N)
internal fun <T> AlertDialog.Builder.showAsCompletableFuture(
    positiveButtonText: CharSequence?,
    negativeButtonText: CharSequence?,
    neutralButtonText: CharSequence?,
    resultFunction: (whichButton: Int) -> T
): CompletableFuture<T> {
    val future = CompletableFuture<T>()
    val dialog = create().apply {
        val commonListener = DialogInterface.OnClickListener { _, which ->
            future.complete(resultFunction(which))
        }
        setButtons(positiveButtonText, negativeButtonText, neutralButtonText, commonListener)
        setOnCancelListener { future.cancel(false) }
    }
    dialog.show()
    future.whenComplete { _: T?, _: Throwable? -> dialog.dismiss() }
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
internal fun <T> AlertDialog.Builder.showAsGuavaFuture(
    positiveButtonText: CharSequence?,
    negativeButtonText: CharSequence?,
    neutralButtonText: CharSequence?,
    resultFunction: (whichButton: Int) -> T
): ListenableFuture<T> {
    val future = SettableFuture.create<T>()
    val dialog = create().apply {
        val commonListener = DialogInterface.OnClickListener { _, which ->
            future.set(resultFunction(which))
        }
        setButtons(positiveButtonText, negativeButtonText, neutralButtonText, commonListener)
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
internal fun <T> AlertDialog.Builder.showAsAndroidXFuture(
    positiveButtonText: CharSequence?,
    negativeButtonText: CharSequence?,
    neutralButtonText: CharSequence?,
    resultFunction: (whichButton: Int) -> T
): ListenableFuture<T> {
    return CallbackToFutureAdapter.getFuture { completer ->
        val dialog = create().apply {
            val commonListener = DialogInterface.OnClickListener { _, which ->
                completer.set(resultFunction(which))
            }
            setButtons(positiveButtonText, negativeButtonText, neutralButtonText, commonListener)
            setOnCancelListener { completer.setCancelled() }
        }
        dialog.show()
        completer.addCancellationListener({ dialog.dismiss() }, DirectExecutor.INSTANCE)
    }
}
