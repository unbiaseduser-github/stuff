package com.sixtyninefourtwenty.stuff.interfaces

import org.json.JSONArray
import org.json.JSONObject

@Suppress("unused")
interface JsonSerializer<T> {

    fun toJson(obj: T): JSONObject

    fun fromJson(obj: JSONObject): T

    fun listToJson(list: List<T>): JSONArray {
        val array = JSONArray()
        list.map { toJson(it) }.forEach { array.put(it) }
        return array
    }

    fun listFromJson(array: JSONArray): List<T> {
        val list = mutableListOf<T>()
        for (i in 0 until array.length()) {
            list.add(fromJson(array.getJSONObject(i)))
        }
        return list
    }

}