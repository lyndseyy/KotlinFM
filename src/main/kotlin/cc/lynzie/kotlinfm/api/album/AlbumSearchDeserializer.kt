package cc.lynzie.kotlinfm.api.album

import cc.lynzie.kotlinfm.model.album.FMAlbumInfo
import cc.lynzie.kotlinfm.model.album.FMAlbumSearch
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class AlbumSearchDeserializer : JsonDeserializer<FMAlbumSearch> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): FMAlbumSearch? {
        return json?.let {
            val jsonObj = json.asJsonObject

            // Move albummatches.album up to albums
            jsonObj.add("albums", jsonObj.getAsJsonObject("albummatches")
                .getAsJsonArray("album"))
            jsonObj.remove("albummatches")

            return Gson().fromJson(jsonObj, FMAlbumSearch::class.java)
        }
    }

}