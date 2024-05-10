package task.syed.catgalleryapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import task.syed.catgalleryapp.domain.repository.CatRepository
import task.syed.catgalleryapp.domain.vo.Resource
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val catRepository: CatRepository) : ViewModel() {

    private val _catState: MutableState<CatGalleryState> = mutableStateOf(value = CatGalleryState.Idle)
    val catState: MutableState<CatGalleryState> get() = _catState

    fun onEvent(actionIntent: CatIntent) {
        viewModelScope.launch {
            when (actionIntent) {
                CatIntent.GetCats -> getCats()
            }
        }
    }


    private suspend fun getCats() {
        _catState.value = CatGalleryState.Loading
        when (val resource = catRepository.getCats()) {
            is Resource.Error -> _catState.value = CatGalleryState.Error(resource.error)
            is Resource.Result -> _catState.value = CatGalleryState.Result(resource.data)
        }
    }
}