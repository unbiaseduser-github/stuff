package com.sixtyninefourtwenty.stuffplayground

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.MoreExecutors
import com.sixtyninefourtwenty.stuff.AsyncResultDialogBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialogResult = AsyncResultDialogBuilder(this@MainActivity)
            .setTitle("title")
            .setMessage("message")
            .showAsCompletableFuture(
                positiveButtonText = "Positive",
                negativeButtonText = "Negative",
                neutralButtonText = "Neutral"
            )

        lifecycleScope.launch {
            delay(2000)
            if (!dialogResult.isDone) {
                Toast.makeText(this@MainActivity, "dialogResult canceled", Toast.LENGTH_SHORT).show()
                dialogResult.cancel(false)
            }
        }

        dialogResult.whenComplete { t, u ->
            if (t != null) {
                when (t) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        Toast.makeText(this@MainActivity, "positive e", Toast.LENGTH_SHORT).show()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        Toast.makeText(this@MainActivity, "negative e", Toast.LENGTH_SHORT).show()
                    }
                    DialogInterface.BUTTON_NEUTRAL -> {
                        Toast.makeText(this@MainActivity, "neutral e", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (u != null) {
                Toast.makeText(this@MainActivity, "exception", Toast.LENGTH_SHORT).show()
            }
        }

        /*Futures.addCallback(
            dialogResult,
            object : FutureCallback<Int> {
                override fun onSuccess(result: Int) {
                    when (result) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            Toast.makeText(this@MainActivity, "positive", Toast.LENGTH_SHORT).show()
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            Toast.makeText(this@MainActivity, "negative", Toast.LENGTH_SHORT).show()
                        }
                        DialogInterface.BUTTON_NEUTRAL -> {
                            Toast.makeText(this@MainActivity, "neutral", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(t: Throwable) = Unit
            },
            MoreExecutors.directExecutor()
        )*/

        /*lifecycleScope.launch {
            val dialogResult = AsyncResultDialogBuilder(this@MainActivity)
                .setTitle("title")
                .setMessage("message")
                .show(
                    positiveButtonText = "OK",
                    negativeButtonText = "Negative",
                    neutralButtonText = "Neutral"
                )
            when (dialogResult) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Toast.makeText(this@MainActivity, "positive", Toast.LENGTH_SHORT).show()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    Toast.makeText(this@MainActivity, "negative", Toast.LENGTH_SHORT).show()
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                    Toast.makeText(this@MainActivity, "neutral", Toast.LENGTH_SHORT).show()
                }
            }
        }*/


    }
}