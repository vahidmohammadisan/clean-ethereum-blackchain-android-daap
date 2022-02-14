package ir.vahidmohammadisan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.vahidmohammadisan.domain.model.Wallet

@Entity
data class WalletEntity(
    @PrimaryKey
    val id: Int? = null,
    val publicKey: String,
    val privateKey: String,
    val walletName: String,
    val walletAddress: String,
)

fun WalletEntity.toWallet(
    publicKey: String,
    privateKey: String,
    walletName: String,
    walletAddress: String
): Wallet {
    return Wallet(
        publicKey = publicKey,
        privateKey = privateKey,
        walletName = walletName,
        address = walletAddress
    )
}
