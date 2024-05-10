package task.syed.catgalleryapp.config.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import task.syed.catgalleryapp.data.repository.CatRepositoryImpl
import task.syed.catgalleryapp.domain.repository.CatRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideCatRepository(catRepositoryImpl: CatRepositoryImpl): CatRepository
}