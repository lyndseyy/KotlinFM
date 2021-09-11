package cc.lynzie.kotlinfm.model.album

import cc.lynzie.kotlinfm.model.track.FMTrackArtist

data class FMAlbumTrack(
    val artist: FMTrackArtist,
    val duration: Int,
    val name: String,
    val url: String
)