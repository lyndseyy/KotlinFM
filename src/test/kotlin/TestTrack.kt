import cc.lynzie.kotlinfm.KotlinFM
import kotlinx.coroutines.runBlocking
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class TestTrack {
    private val kotlinFM = KotlinFM(System.getenv("KotlinFMKey"))

    @Test
    fun testTrackInfo() = runBlocking {
        val trackInfo = kotlinFM.track.getTrackInfo(
            "Panic! at the Disco",
            "I Write Sins Not Tragedies",
            "lyndseeyy"
        )

        assertEquals("I Write Sins Not Tragedies", trackInfo.name)
        assertEquals("A Fever You Can't Sweat Out", trackInfo.album.title)
        assertEquals("Panic! at the Disco", trackInfo.artist.name)
        assertEquals(true, (trackInfo.userPlays ?: 0) >= 135)
    }

    @Test
    fun testSimilarTracks() = runBlocking {
        val similarTracks =
            kotlinFM.track.getSimilarTracks("Panic! at the Disco", "I Write Sins Not Tragedies")

        assertEquals(100, similarTracks.tracks.size)
    }

    @Test
    fun testTrackSearch() = runBlocking {
        val search = kotlinFM.track.searchTracks("Golden Days")

        assertEquals(true, search.tracks.any { it.artist == "Panic! at the Disco" })
    }

}