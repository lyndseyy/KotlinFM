package cc.lynzie.kotlinfm.api

import cc.lynzie.kotlinfm.connection.RestClient
import cc.lynzie.kotlinfm.model.track.FMTrackInfo
import com.google.gson.Gson
import io.ktor.client.request.*

class TrackAPI(val restClient: RestClient) {

    val gson = Gson()

    /**
     * Gets information from the Last.fm API and returns it as [FMTrackInfo]
     *
     * @param track Required, the track name to search for.
     * @param author Required, the author of the track being searched for.
     * @param username Optional, if a username is provided information about how many times
     * the user has played the songs, or whether they have the song loved will be returned
     * @param autocorrect Optional, if true the Last.fm API will take a misspelled artist/track
     * value and turn it into the correct one.
     */
    suspend fun getTrackInfo(
        artist: String,
        track: String,
        username: String = "",
        autocorrect: Boolean = false
    ): FMTrackInfo {
        val json = restClient.getRawJson("track.getInfo") {
            parameter("artist", artist)
            parameter("track", track)
            if (username.isNotEmpty()) parameter("username", username)
            if (autocorrect) parameter("autocorrect", autocorrect)
        }

        return gson.fromJson(json, FMTrackInfo::class.java)
    }

}