package com.devsoulify.devsoulifybackend.spotify.dto

data class SpotifyCategoriesResponse(
    val categories: Categories
)

data class Categories(
    val items: List<Category>
)

data class Category(
    val id: String,
    val name: String,
    val icons: List<Icon>
)

data class Icon(
    val url: String,
    val height: Int?,
    val width: Int?
)
