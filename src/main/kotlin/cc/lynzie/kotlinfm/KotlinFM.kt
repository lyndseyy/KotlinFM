package cc.lynzie.kotlinfm

import cc.lynzie.kotlinfm.api.TrackAPI
import cc.lynzie.kotlinfm.connection.RestClient

class KotlinFM(private val apiKey: String) {

    private val restClient = RestClient(apiKey)

    val track = TrackAPI(restClient)

}