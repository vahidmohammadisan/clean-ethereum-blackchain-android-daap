package ir.vahidmohammadisan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.vahidmohammadisan.data.model.WalletEntity

@Database(entities = [WalletEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
}
