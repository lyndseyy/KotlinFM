package cc.lynzie.kotlinfm.model.album

import cc.lynzie.kotlinfm.model.generic.FMImage
import com.google.gson.annotations.SerializedName

data class FMAlbumSearchInfo(
    val artist: String,
    @SerializedName("image") val images: List<FMImage>,
    val name: String,
    val url: String
)