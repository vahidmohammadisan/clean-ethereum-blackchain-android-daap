package ir.vahidmohammadisan.tossco.ui.wallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.model.Wallet
import ir.vahidmohammadisan.domain.usecase.CreateWalletUseCase
import ir.vahidmohammadisan.domain.usecase.GetWalletUseCase
import ir.vahidmohammadisan.domain.usecase.ImportWalletUseCase
import ir.vahidmohammadisan.domain.usecase.SaveWalletUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.web3j.crypto.Credentials
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val createWalletUseCase: CreateWalletUseCase,
    private val importWalletUseCase: ImportWalletUseCase,
    private val saveWalletUseCase: SaveWalletUseCase,
    private val getWalletUseCase: GetWalletUseCase
) :
    ViewModel() {

    val createWalletLiveData = MutableLiveData<Resource<Credentials>>()
    val importWalletLiveData = MutableLiveData<Resource<Credentials>>()
    val saveWalletLiveData = MutableLiveData<Resource<String>>()
    val getWalletLiveData = MutableLiveData<Resource<List<Wallet>>>()

    fun createWallet(password: String) {
        createWalletUseCase.execute(password).onEach {
            when (it) {
                is Resource.Loading -> {
                    createWalletLiveData.value = Resource.Loading()
                }
                is Resource.Error -> {
                    createWalletLiveData.value = Resource.Error(it.message!!)
                }
                is Resource.Success -> {
                    createWalletLiveData.value = Resource.Success(it.data as Credentials)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun importWallet(mnemonic: String) {
        importWalletUseCase.execute(mnemonic).onEach {
            when (it) {
                is Resource.Loading -> {
                    importWalletLiveData.value = Resource.Loading()
                }
                is Resource.Error -> {
                    importWalletLiveData.value = Resource.Error(it.message!!)
                }
                is Resource.Success -> {
                    importWalletLiveData.value = Resource.Success(it.data as Credentials)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveWallet(walletName: String, credentials: Credentials, address: String) {
        val publicKey: String = credentials.ecKeyPair.publicKey.toString()
        val privateKey: String = credentials.ecKeyPair.privateKey.toString()
        saveWalletUseCase.execute(
            wallet = Wallet(
                walletName = walletName,
                publicKey = publicKey,
                privateKey = privateKey,
                address = address
            )
        ).onEach {
            when (it) {
                is Resource.Loading -> {
                    saveWalletLiveData.value = Resource.Loading()
                }
                is Resource.Error -> {
                    saveWalletLiveData.value = Resource.Error(it.message!!)
                }
                is Resource.Success -> {
                    saveWalletLiveData.value = Resource.Success("")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getWallet() {
        getWalletUseCase.execute(Unit).onEach {
            when (it) {
                is Resource.Loading -> {
                    getWalletLiveData.value = Resource.Loading()
                }
                is Resource.Error -> {
                    getWalletLiveData.value = Resource.Error(it.message!!)
                }
                is Resource.Success -> {
                    getWalletLiveData.value = Resource.Success(it.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

}