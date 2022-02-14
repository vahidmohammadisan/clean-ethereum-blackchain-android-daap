package ir.vahidmohammadisan.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vahidmohammadisan.data.local.AppDatabase
import ir.vahidmohammadisan.data.local.WalletDao

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideWalletDao(database: AppDatabase): WalletDao {
        return database.walletDao()
    }
}