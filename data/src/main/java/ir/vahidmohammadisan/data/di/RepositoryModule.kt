package ir.vahidmohammadisan.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vahidmohammadisan.data.repository.RepositoryImpl
import ir.vahidmohammadisan.domain.repository.Repository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

}