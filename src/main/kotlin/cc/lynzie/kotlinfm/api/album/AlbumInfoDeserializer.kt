package cc.lynzie.kotlinfm.api.album

import cc.lynzie.kotlinfm.model.album.FMAlbumInfo
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class AlbumInfoDeserializer : JsonDeserializer<FMAlbumInfo> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): FMAlbumInfo? {
        return json?.let {
            val jsonObj = json.asJsonObject

            // Move tracks.track up to tracks and tags.tag up to tags
            jsonObj.add("tracks", jsonObj.getAsJsonObject("tracks").getAsJsonArray("track"))
            jsonObj.add("tags", jsonObj.getAsJsonObject("tags").getAsJsonArray("tag"))

            return Gson().fromJson(jsonObj, FMAlbumInfo::class.java)
        }
    }

}