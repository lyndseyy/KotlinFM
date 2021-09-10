package cc.lynzie.kotlinfm.model.track

data class FMTrackCorrectionAttributes(
    private val artistcorrected: Int,
    val index: Int,
    private val trackcorrected: Int
) {
    val artistCorrected = artistcorrected == 1
    val trackCorrected = trackcorrected == 1
}