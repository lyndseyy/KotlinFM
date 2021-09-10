package cc.lynzie.kotlinfm.model.track

import com.google.gson.annotations.SerializedName

data class FMSimilarTracks(@SerializedName("track") val tracks: List<FMSimilarTrack>)