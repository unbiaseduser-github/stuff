@file:JvmName("Assets")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.Options
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.Reader
import java.nio.charset.Charset
import java.util.stream.Stream
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.Throws

@JvmOverloads
@Throws(IOException::class)
@WorkerThread
inline fun <R> AssetManager.readLinesSync(
    path: String,
    charset: Charset = Charsets.UTF_8,
    block: (Stream<String>) -> R
) = open(path).bufferedReader(charset).use { block(it.lines()) }

suspend fun <R> AssetManager.readLines(
    path: String,
    charset: Charset = Charsets.UTF_8,
    context: CoroutineContext = Dispatchers.IO,
    block: (Stream<String>) -> R
) = withContext(context) {
    readLinesSync(path, charset, block)
}

/**
 * Reads a text file from assets with [Reader.readText].
 */
@JvmOverloads
@Throws(IOException::class)
@WorkerThread
fun AssetManager.readTextSync(path: String, charset: Charset = Charsets.UTF_8) =
    open(path).reader(charset).use { it.readText() }

/**
 * Suspending version of [readTextSync].
 */
suspend fun AssetManager.readText(
    path: String,
    charset: Charset = Charsets.UTF_8,
    context: CoroutineContext = Dispatchers.IO
) = withContext(context) {
    readTextSync(path, charset)
}

/**
 * Reads a drawable from assets with [Drawable.createFromStream].
 */
@Throws(IOException::class)
@WorkerThread
fun AssetManager.readDrawableSync(path: String) =
    open(path).use { Drawable.createFromStream(it, /* I have no idea what this param does, but this works anyway. */ path) }

/**
 * Suspending version of [readDrawableSync].
 */
suspend fun AssetManager.readDrawable(
    path: String,
    context: CoroutineContext = Dispatchers.IO
) = withContext(context) {
    readDrawableSync(path)
}

/**
 * Reads a bitmap from assets with [BitmapFactory.decodeStream].
 */
@JvmOverloads
@Throws(IOException::class)
@WorkerThread
fun AssetManager.readBitmapSync(
    path: String,
    outPadding: Rect? = null,
    opts: Options? = null
): Bitmap? = open(path).use { BitmapFactory.decodeStream(it, outPadding, opts) }

/**
 * Suspending version of [readBitmapSync].
 */
suspend fun AssetManager.readBitmap(
    path: String,
    outPadding: Rect? = null,
    opts: Options? = null,
    context: CoroutineContext = Dispatchers.IO
) = withContext(context) {
    readBitmapSync(path, outPadding, opts)
}
