package ir.vahidmohammadisan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.vahidmohammadisan.data.model.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallet(walletEntity: WalletEntity)

    @Query("SELECT * FROM WalletEntity")
    fun getAllWallets(): Flow<List<WalletEntity>>
}