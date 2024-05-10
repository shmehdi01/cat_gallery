package task.syed.catgalleryapp.data.repository

import task.syed.catgalleryapp.data.ApiService
import task.syed.catgalleryapp.domain.models.Cat
import task.syed.catgalleryapp.domain.repository.CatRepository
import task.syed.catgalleryapp.domain.vo.Resource
import java.lang.Exception
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(private val apiService: ApiService): CatRepository {

    override suspend fun getCats(): Resource<List<Cat>> {
        return try {
            val response = apiService.getCats(limit = 10)
            Resource.Result(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error")
        }
    }


}