package com.example.androidapptemplate.data.features.unsplash.api.response

import com.example.androidapptemplate.domain.features.webapi.unsplash.model.UnSplashPhoto
import com.squareup.moshi.Json

internal data class UnsplashResponse(
    val total: Int?, val totalPages: Int?,
    val results: List<Result>
) {
    data class Result(
        val id: String,
        val urls: Urls,
        val likes: Int,
        val user: User,
    ) {
        data class Urls(
            val full: String,
            val regular: String,
        )

        data class User(
            val username: String,
            @Json(name = "profile_image") val profileImage: ProfileImage
        ) {
            data class ProfileImage(val small: String?)
        }
    }
}

internal fun UnsplashResponse.Result.toModel(): UnSplashPhoto {
    return UnSplashPhoto(
        id = this.id,
        urls = UnSplashPhoto.UnSplashPhotoUrls(this.urls.full, this.urls.regular),
        likes = this.likes,
        user = UnSplashPhoto.UnSplashUser(
            username = this.user.username,
            profileImage = UnSplashPhoto.UnSplashUser.ProfileImage(small = this.user.profileImage.small)
        ),
    )
}