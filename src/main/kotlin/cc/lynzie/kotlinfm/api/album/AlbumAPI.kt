package cc.lynzie.kotlinfm.api.album

import cc.lynzie.kotlinfm.connection.RestClient
import cc.lynzie.kotlinfm.model.album.FMAlbumInfo
import cc.lynzie.kotlinfm.model.album.FMAlbumSearch
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.ktor.client.request.*

class AlbumAPI(private val restClient: RestClient) {

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(FMAlbumInfo::class.java, AlbumInfoDeserializer())
        .registerTypeAdapter(FMAlbumSearch::class.java, AlbumSearchDeserializer())
        .create()

    /**
     * Fetches album information from the Last.fm API, and returns it in an
     * [FMAlbumInfo] object.
     *
     * @param album Required, the name of the album to search for.
     * @param artist Required, the author of the album being searched for.
     * @param mbid Optional, the MusicBrainz ID for the album
     * @param autocorrect Optional, if true the Last.fm API will take a misspelled artist/track
     * value and turn it into the correct one, defaults to false.
     * @param username Optional, if supplied then information about how many times the user has
     * listened to the album will be returned within the response.
     * @param lang Optional, if specified then the Biography will be returned as the language
     * specified by the ISO 639 alpha-2 code, defaults to "eng".
     */
    suspend fun getAlbumInfo(
        album: String,
        artist: String,
        mbid: String = "",
        autocorrect: Boolean = false,
        username: String = "",
        lang: String = ""
    ) : FMAlbumInfo {
        val json = restClient.getRawJson("album.getInfo") {
            parameter("album", album)
            parameter("artist", artist)
            if (mbid.isNotEmpty()) parameter("mbid", mbid)
            if (autocorrect) parameter("autocorrect", 1)
            if (username.isNotEmpty()) parameter("username", username)
            if (lang.isNotEmpty()) parameter("lang", lang)
        }

        return gson.fromJson(json, FMAlbumInfo::class.java)
    }

    /**
     * Fetches albums from the Last.fm API, with the name supplied and returns them in
     * an [FMAlbumSearch] object.
     *
     * @param album Required, the name of the album to search for
     * @param limit Optional, how many albums limit the search to, defaults to 30
     * @param page The page to start at, defaults to 1
     */
    suspend fun searchAlbums(album: String, limit: Int = 30, page: Int = 1): FMAlbumSearch {
        val json = restClient.getRawJson("album.search") {
            parameter("album", album)
            parameter("limit", limit)
            parameter("page", page)
        }

        return gson.fromJson(json, FMAlbumSearch::class.java)
    }

}