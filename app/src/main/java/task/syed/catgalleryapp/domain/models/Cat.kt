package task.syed.catgalleryapp.domain.models

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val imageUrl: String
)
