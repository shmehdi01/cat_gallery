package task.syed.catgalleryapp.data

import retrofit2.http.GET
import retrofit2.http.Query
import task.syed.catgalleryapp.domain.models.Cat

interface ApiService {

    @GET("images/search")
    suspend fun getCats(@Query("limit") limit: Int): List<Cat>
}