package cc.lynzie.kotlinfm

import cc.lynzie.kotlinfm.connection.RestClient

object KotlinFM {

    private lateinit var apiKey: String
    internal lateinit var restClient: RestClient

    /**
     * Sets the [apiKey] that will be used in all calls
     * to the last.fm API, and recreates the [restClient]
     */
    fun setKey(apiKey: String) {
        this.apiKey = apiKey
        this.restClient = RestClient(this.apiKey)
    }

}