package cc.lynzie.kotlinfm.connection

import com.google.gson.JsonObject
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import mu.KotlinLogging

/**
 * A simple HTTPClient wrapper, meant to be a class that can be transferred across
 * many projects without needing too much of the code changed.
 *
 * @author Lyndsey Winter
 * @since 0.0.1
 */
class RestClient(val apiKey: String) {
    val issuesLink = "https://github.com/lyndseyy/KotlinFM/issues/new"
    val apiBase = "https://ws.audioscrobbler.com/2.0/"

    val logger = KotlinLogging.logger("KotlinFM HTTP Client")

    suspend inline fun getRawJson(
        route: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): JsonObject? =
        try {
            val json: JsonObject = httpClient.get(apiBase) {
                // Add the two always* required parameters to the URL
                parameter("api_key", apiKey)
                parameter("method", route)
                parameter("format", "json")

                // Add the user supplied settings before sending off our request
                this.block()
            }

            json.get(json.keySet().first()).asJsonObject
        } catch (ex: Throwable) {
            logger.error(ex) { "Your request couldn't be handled correctly! Please report this back to a developer at $issuesLink!" }
            null
        }

    val httpClient = HttpClient(OkHttp) {
        engine {
            addNetworkInterceptor(LimitHandler())
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(UserAgent) {
            agent = "KotlinFM / DEV"
        }
    }

}