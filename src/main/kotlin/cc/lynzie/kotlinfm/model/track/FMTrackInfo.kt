package cc.lynzie.kotlinfm.model.track

import com.google.gson.annotations.SerializedName

data class FMTrackInfo(
    val album: FMTrackAlbum,
    val artist: FMTrackArtist,
    val duration: Long,
    val listeners: Int,
    val name: String,
    val playcount: Int,
    val url: String,
    @SerializedName("userplaycount") val userPlays: Int?,
    private val userloved: Int?
) {
    val userLoved = userloved == 1
}