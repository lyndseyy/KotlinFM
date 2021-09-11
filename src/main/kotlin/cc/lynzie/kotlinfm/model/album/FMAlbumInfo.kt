package cc.lynzie.kotlinfm.model.album

import cc.lynzie.kotlinfm.model.generic.FMImage
import cc.lynzie.kotlinfm.model.generic.FMTag
import com.google.gson.annotations.SerializedName

data class FMAlbumInfo(
    val artist: String,
    @SerializedName("image") val images: List<FMImage>,
    val listeners: Int,
    val name: String,
    val playcount: Int,
    val tags: List<FMTag>,
    val tracks: List<FMAlbumTrack>,
    val url: String,
    @SerializedName("userplaycount") val userPlayCount: Int?
)
