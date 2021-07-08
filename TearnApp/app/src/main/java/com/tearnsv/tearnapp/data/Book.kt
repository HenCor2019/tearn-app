package com.tearnsv.tearnapp.data

import android.media.ThumbnailUtils

data class Book(
    val items: List<ItemsBook>
)

data class ItemsBook(
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    val publishedDate: String?,
    val previewLink: String,
    val imageLinks: ImageLink?
)

data class ImageLink(
    val thumbnail: String
)

