package ir.vahidmohammadisan.domain.repository

import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.model.Wallet
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun createWallet(password: String): Flow<Resource<Any>>
    fun importWallet(mnemonic: String): Flow<Resource<Any>>
    fun saveWallet(wallet: Wallet): Flow<Resource<Any>>
    fun getWallet(): Flow<Resource<List<Wallet>>>
    fun getWalletBalance(address: String): Flow<Resource<String>>
    fun loadContract(privateKey: String): Flow<Resource<Boolean>>
    fun takeGuess(guess: Boolean): Flow<Resource<Boolean>>
    fun saveCorrectGuess(): Flow<Int>
    fun getCorrectGuess(): Flow<Int>
    fun resetGuess(): Flow<Int>
}