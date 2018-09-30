package io.melbybaldove.remote.movie.response

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import io.melbybaldove.data.movie.entity.Rated
import java.lang.reflect.Type

/**
 * @author Melby Baldove
 * melbourne.baldove@owtoph.com
 */
class RatedDeserializer : JsonDeserializer<Rated> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Rated {
        return if (json.isJsonPrimitive) {
            Rated(0f)
        } else {
            Gson().fromJson(json, typeOfT)
        }
    }
}