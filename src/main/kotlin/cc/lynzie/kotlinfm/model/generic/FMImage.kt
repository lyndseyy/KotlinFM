package cc.lynzie.kotlinfm.model.generic

import com.google.gson.annotations.SerializedName

data class FMImage(val size: String, @SerializedName("#text") val url: String)