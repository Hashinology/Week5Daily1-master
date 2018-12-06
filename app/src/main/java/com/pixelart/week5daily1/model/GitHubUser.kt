package com.pixelart.week5daily1.model

import com.google.gson.annotations.SerializedName

data class GitHubUser (
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<Item>
)