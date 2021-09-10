package cc.lynzie.kotlinfm.api

import cc.lynzie.kotlinfm.api.track.TrackSearchDeserializer
import cc.lynzie.kotlinfm.connection.RestClient
import cc.lynzie.kotlinfm.model.track.FMSimilarTracks
import cc.lynzie.kotlinfm.model.track.FMTrackCorrection
import cc.lynzie.kotlinfm.model.track.FMTrackInfo
import cc.lynzie.kotlinfm.model.track.FMTrackSearch
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.ktor.client.request.*

class TrackAPI(private val restClient: RestClient) {

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(FMTrackSearch::class.java, TrackSearchDeserializer())
        .create()

    /**
     * Gets the correction information from the Last.fm API and returns it as [FMTrackCorrection]
     *
     * @param artist Required, the author of the track being searched for.
     * @param track Required, the track name to search for.
     */
    suspend fun getTrackCorrection(
        artist: String,
        track: String
    ): FMTrackCorrection {
        val json = restClient.getRawJson("track.getCorrection") {
            parameter("artist", artist)
            parameter("track", track)
        }

        return gson.fromJson(json, FMTrackCorrection::class.java)
    }

    /**
     * Gets information from the Last.fm API and returns it as [FMTrackInfo]
     *
     * @param track Required, the track name to search for.
     * @param artist Required, the author of the track being searched for.
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
            if (autocorrect) parameter("autocorrect", 1)
        }

        return gson.fromJson(json, FMTrackInfo::class.java)
    }

    /**
     * Gets similar tracks from the Last.fm API for the track specified, returned as a [FMSimilarTracks]
     *
     * @param artist Required, the artist of the track.
     * @param track Required, the name of the track.
     * @param autocorrect Optional, whether to autocorrect the [artist] & [track] specified.
     * @param limit The number of tracks to return, defaults to 100.
     */
    suspend fun getSimilarTracks(
        artist: String,
        track: String,
        autocorrect: Boolean = false,
        limit: Int = 100
    ): FMSimilarTracks {
        val json = restClient.getRawJson("track.getSimilar") {
            parameter("artist", artist)
            parameter("track", track)
            if (autocorrect) parameter("autocorrect", 1)
            parameter("limit", limit)
        }

        return gson.fromJson(json, FMSimilarTracks::class.java)
    }

    /**
     * Searches for a track within Last.fm's API, by taking the song name and returning results
     * matching in a [FMTrackSearch] object.
     *
     * @param track Required, the name of the song to search for
     * @param limit Optional, How many tracks to return, defaults to 30
     * @param page Optional, What page to start at, defaults to 1
     * @param artist Optional, will filter results to include a specific artist.
     */
    suspend fun searchTracks(
        track: String,
        limit: Int = 30,
        page: Int = 1,
        artist: String = ""
    ): FMTrackSearch {
        val json = restClient.getRawJson("track.search") {
            parameter("track", track)
            parameter("limit", limit)
            parameter("page", page)
            if (artist.isNotEmpty()) parameter("artist", artist)
        }

        return gson.fromJson(json, FMTrackSearch::class.java)
    }

}