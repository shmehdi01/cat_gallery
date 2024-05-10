package task.syed.catgalleryapp.domain.repository

import task.syed.catgalleryapp.domain.models.Cat
import task.syed.catgalleryapp.domain.vo.Resource

interface CatRepository {
    suspend fun getCats(): Resource<List<Cat>>
}