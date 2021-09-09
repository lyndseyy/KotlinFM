package cc.lynzie.kotlinfm.model.track

import cc.lynzie.kotlinfm.model.generic.FMImage
import com.google.gson.annotations.SerializedName

data class FMTrackAlbum(
    val artist: String,
    @SerializedName("image") val images: List<FMImage>,
    val title: String,
    val url: String
)
