package cc.lynzie.kotlinfm

import cc.lynzie.kotlinfm.api.track.TrackAPI
import cc.lynzie.kotlinfm.connection.RestClient

class KotlinFM(apiKey: String) {

    private val restClient = RestClient(apiKey)

    val track = TrackAPI(restClient)

}