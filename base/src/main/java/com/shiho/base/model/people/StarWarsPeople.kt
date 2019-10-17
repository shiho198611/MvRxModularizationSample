package com.shiho.base.model.people


import com.google.gson.annotations.SerializedName

data class StarWarsPeople(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<Result?>?
)