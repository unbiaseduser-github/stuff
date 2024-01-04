package com.sixtyninefourtwenty.stuff.interfaces

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@Suppress("unused")
interface JsonSerializer<T> {

    @Throws(JSONException::class)
    fun toJson(obj: T): JSONObject

    @Throws(JSONException::class)
    fun fromJson(obj: JSONObject): T

    /**
     * Convenience for `toJson(obj).toString()`.
     */
    fun toJsonString(obj: T) = toJson(obj).toString()

    /**
     * Convenience for `fromJson(JSONObject(jsonString))`.
     */
    fun fromJsonString(jsonString: String) = fromJson(JSONObject(jsonString))

    fun listToJson(list: List<T>): JSONArray {
        val array = JSONArray()
        list.forEach { array.put(toJson(it)) }
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