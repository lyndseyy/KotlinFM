package cc.lynzie.kotlinfm.model.track

import cc.lynzie.kotlinfm.model.generic.FMImage
import com.google.gson.annotations.SerializedName

data class FMTrackSearchInfo(
    val artist: String,
    @SerializedName("image") val images: List<FMImage>,
    val listeners: Int,
    val name: String,
    val url: String
)