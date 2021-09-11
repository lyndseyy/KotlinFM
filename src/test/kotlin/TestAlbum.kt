import cc.lynzie.kotlinfm.KotlinFM
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TestAlbum {
    private val kotlinFM = KotlinFM(System.getenv("KotlinFMKey"))

    @Test
    fun testAlbumInfo() = runBlocking {
        val album = kotlinFM.album.getAlbumInfo(
            "Random Access Memories",
            "Daft Punk",
            username = "lyndseeyy"
        )

        assertEquals(13, album.tracks.size)
        assertEquals(true, (album.userPlayCount ?: 0) >= 250)
        assertEquals(true, album.playcount >= 45500000)
    }

    @Test
    fun testAlbumSearch() = runBlocking {
        val search = kotlinFM.album.searchAlbums("OK Orchestra")

        assertEquals(true, search.albums.any { it.artist == "AJR" })
    }

}