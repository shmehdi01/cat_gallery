package task.syed.catgalleryapp.domain.vo

sealed class Resource<T> {
    data class Result<T>(val data: T): Resource<T>()
    data class Error<T>(val error: String): Resource<T>()
}