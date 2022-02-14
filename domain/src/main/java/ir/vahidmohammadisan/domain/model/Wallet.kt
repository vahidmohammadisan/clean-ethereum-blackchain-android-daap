package ir.vahidmohammadisan.domain.model

data class Wallet(
    val walletName: String,
    val publicKey: String,
    val privateKey: String,
    val address: String
)
