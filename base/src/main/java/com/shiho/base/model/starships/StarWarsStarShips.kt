package com.shiho.base.model.starships


import com.google.gson.annotations.SerializedName

data class StarWarsStarShips(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<Result?>?
)