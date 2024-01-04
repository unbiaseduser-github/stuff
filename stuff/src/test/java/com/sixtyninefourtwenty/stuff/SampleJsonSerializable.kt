package com.sixtyninefourtwenty.stuff

import com.sixtyninefourtwenty.stuff.interfaces.JsonSerializable
import com.sixtyninefourtwenty.stuff.interfaces.JsonSerializer
import org.json.JSONObject

data class SampleJsonSerializable(
    val someInt: Int,
    val someString: String
) : JsonSerializable<SampleJsonSerializable> {

    override val jsonSerializer: JsonSerializer<SampleJsonSerializable>
        get() = SERIALIZER

    companion object {
        val SERIALIZER = object : JsonSerializer<SampleJsonSerializable> {
            override fun toJson(obj: SampleJsonSerializable): JSONObject {
                return JSONObject()
                    .put("some_int", obj.someInt)
                    .put("some_string", obj.someString)
            }

            override fun fromJson(obj: JSONObject): SampleJsonSerializable {
                return SampleJsonSerializable(
                    obj.getInt("some_int"),
                    obj.getString("some_string")
                )
            }
        }
    }

}
