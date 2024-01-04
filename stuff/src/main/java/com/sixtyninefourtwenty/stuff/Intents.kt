@file:JvmName("Intents")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.Intent
import com.sixtyninefourtwenty.stuff.interfaces.JsonSerializable
import com.sixtyninefourtwenty.stuff.interfaces.JsonSerializer

fun <T : JsonSerializable<T>> Intent.putExtra(name: String, obj: T?) =
    putExtra(name, obj?.jsonSerializer?.toJsonString(obj))
fun <T> Intent.getJsonSerializableExtra(name: String, jsonSerializer: JsonSerializer<T>) =
    getStringExtra(name)?.let { jsonSerializer.fromJsonString(it) }
