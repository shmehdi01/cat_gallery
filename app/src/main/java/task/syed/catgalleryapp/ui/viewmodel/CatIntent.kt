package task.syed.catgalleryapp.ui.viewmodel

import task.syed.catgalleryapp.domain.models.Cat

sealed interface CatIntent {
    data object GetCats : CatIntent
}

sealed interface CatGalleryState {
    data object Idle : CatGalleryState
    data object Loading : CatGalleryState
    data class Result(val cats: List<Cat>) : CatGalleryState
    data class Error(val error: String) : CatGalleryState
}