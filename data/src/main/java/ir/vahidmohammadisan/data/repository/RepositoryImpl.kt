package ir.vahidmohammadisan.data.repository

import android.util.Log
import ir.vahidmohammadisan.common.CORRECT_TIME
import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.data.contract.TossCo
import ir.vahidmohammadisan.data.local.SharedPreferencesManager
import ir.vahidmohammadisan.data.local.WalletDao
import ir.vahidmohammadisan.data.model.WalletEntity
import ir.vahidmohammadisan.data.model.toWallet
import ir.vahidmohammadisan.domain.model.Wallet
import ir.vahidmohammadisan.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import java.math.BigInteger
import java.security.SecureRandom
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferencesManager,
    private val walletDao: WalletDao,
    private val tossCo: TossCo
) : Repository {

    override fun createWallet(password: String): Flow<Resource<Any>> = flow {

        emit(Resource.Loading<Any>())

        try {
            val random = SecureRandom()
            val bytes = ByteArray(16)
            random.nextBytes(bytes)
            val mnemonic = MnemonicUtils.generateMnemonic(bytes)
            val seed = MnemonicUtils.generateSeed(mnemonic, null)
            val masterKeypair = Bip32ECKeyPair.generateKeyPair(seed)
            val bip44 = keyGeneration(masterKeypair)
            val credentials = Credentials.create(bip44)

            emit(Resource.Success<Any>(data = credentials))

        } catch (e: Exception) {
            emit(Resource.Error<Any>(e.localizedMessage ?: "unknown error"))
        }
    }

    override fun importWallet(mnemonic: String): Flow<Resource<Any>> = flow {

        emit(Resource.Loading<Any>())
        try {

            val password = ""
            val masterKeypair =
                Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(mnemonic, password))

            val derivationPath =
                intArrayOf(
                    44 or Bip32ECKeyPair.HARDENED_BIT,
                    60 or Bip32ECKeyPair.HARDENED_BIT,
                    0 or Bip32ECKeyPair.HARDENED_BIT,
                    0,
                    0
                )

            val derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, derivationPath)
            val credentials = Credentials.create(derivedKeyPair)

            emit(Resource.Success<Any>(data = credentials))
        } catch (e: Exception) {
            emit(Resource.Error<Any>(e.localizedMessage ?: "unknown error"))
        }
    }

    override fun saveWallet(wallet: Wallet): Flow<Resource<Any>> = channelFlow {

        send(Resource.Loading<Any>())

        try {
            walletDao.insertWallet(
                WalletEntity(
                    publicKey = wallet.publicKey,
                    privateKey = wallet.privateKey,
                    walletName = wallet.walletName,
                    walletAddress = wallet.address
                )
            )
            send(Resource.Success<Any>(data = Unit))

        } catch (e: Exception) {
            send(Resource.Error<Any>(e.localizedMessage ?: "unknown error"))
        }
    }

    override fun getWallet(): Flow<Resource<List<Wallet>>> = channelFlow {
        walletDao.getAllWallets().collect {
            send(Resource.Success<List<Wallet>>(it.map {
                it.toWallet(it.publicKey, it.privateKey, it.walletName, it.walletAddress)
            }))
        }
    }

    override fun loadContract(privateKey: String): Flow<Resource<Boolean>> = channelFlow {

        send(Resource.Loading<Boolean>())

        try {

            launch(Dispatchers.Default) {

                val isValid = tossCo.isValid

                withContext(Dispatchers.Main) {
                    send(Resource.Success<Boolean>(data = isValid))
                }

            }

        } catch (e: Exception) {
            send(Resource.Error<Boolean>(e.localizedMessage ?: "unknown error"))
        }
    }

    override fun getWalletBalance(address: String): Flow<Resource<String>> = channelFlow {

        send(Resource.Loading<String>())

        try {

            launch(Dispatchers.Default) {
                val balance: BigInteger? = tossCo.getBalance(address).send()
                withContext(Dispatchers.Main) {
                    send(Resource.Success<String>(data = balance.toString()))
                }
            }

        } catch (e: Exception) {
            send(Resource.Error<String>(e.localizedMessage ?: "unknown error"))
        }
    }

    override fun takeGuess(guess: Boolean): Flow<Resource<Boolean>> = channelFlow {
        send(Resource.Loading<Boolean>())
        /*  sharedPreferences.getSecureShared().edit().apply() {
              putString("vahid", "anita")
          }.apply()
          Log.w("BBBB", sharedPreferences.getSecureShared().getString("vahid", "_anita_").toString())*/
        try {

            launch(Dispatchers.Default) {
                Log.w("random:", (0..99).random().toString())
                val result: Boolean = tossCo.guess((0..99999).random().toBigInteger(), guess).send()
                withContext(Dispatchers.Main) {
                    send(Resource.Success<Boolean>(data = result))
                }
            }

        } catch (e: Exception) {
            send(Resource.Error<Boolean>(e.localizedMessage ?: "unknown error"))
        }
    }

    override fun saveCorrectGuess(): Flow<Int> = channelFlow {
        sharedPreferences.getSecureShared().edit().apply() {
            putInt(
                CORRECT_TIME,
                sharedPreferences.getSecureShared().getInt(CORRECT_TIME, 0) + 1
            )
        }.apply()

        send(sharedPreferences.getSecureShared().getInt(CORRECT_TIME, 0))
    }

    override fun getCorrectGuess(): Flow<Int> = channelFlow {
        send(sharedPreferences.getSecureShared().getInt(CORRECT_TIME, 0))
    }

    override fun resetGuess(): Flow<Int> = channelFlow {
        sharedPreferences.getSecureShared().edit().apply() {
            putInt(
                CORRECT_TIME,
                0
            )
        }.apply()
        send(sharedPreferences.getSecureShared().getInt(CORRECT_TIME, 0))
    }

    private fun keyGeneration(master: Bip32ECKeyPair): Bip32ECKeyPair {
        val path = intArrayOf(
            44 or Bip32ECKeyPair.HARDENED_BIT,
            60 or Bip32ECKeyPair.HARDENED_BIT,
            0 or Bip32ECKeyPair.HARDENED_BIT,
            0,
            0
        )
        return Bip32ECKeyPair.deriveKeyPair(master, path)
    }

}