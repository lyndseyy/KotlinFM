package cc.lynzie.kotlinfm.model.track

import cc.lynzie.kotlinfm.model.generic.FMImage

data class FMSimilarTrack(
    val artist: FMTrackArtist,
    val duration: Int,
    val images: List<FMImage>,
    val match: Double,
    val name: String,
    val playcount: Int,
    val url: String
)