package cc.lynzie.kotlinfm.api.track

import cc.lynzie.kotlinfm.model.track.FMTrackSearch
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TrackSearchDeserializer : JsonDeserializer<FMTrackSearch> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): FMTrackSearch? {
        return json?.let {
            val jsonObj = json.asJsonObject

            println(jsonObj)
            // Move trackMatches.track up to tracks in the root obj.
            jsonObj.add("tracks", jsonObj.get("trackmatches").asJsonObject.get("track").asJsonArray)
            jsonObj.remove("trackMatches")

            return Gson().fromJson(jsonObj, FMTrackSearch::class.java)
        }
    }

}