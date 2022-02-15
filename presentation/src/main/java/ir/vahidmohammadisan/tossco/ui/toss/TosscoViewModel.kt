package ir.vahidmohammadisan.tossco.ui.toss

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.usecase.GetWalletBalanceUseCase
import ir.vahidmohammadisan.domain.usecase.LoadContractUseCase
import ir.vahidmohammadisan.domain.usecase.takeGuessUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TosscoViewModel @Inject constructor(
    private val getWalletBalanceUseCase: GetWalletBalanceUseCase,
    private val loadContractUseCase: LoadContractUseCase,
    private val takeGuessUseCase: takeGuessUseCase
) : ViewModel() {

    val walletBalanceLiveData = MutableLiveData<Resource<String>>()
    val loadContractLiveData = MutableLiveData<Resource<Boolean>>()
    val takeGuessLiveData = MutableLiveData<Resource<Boolean>>()

    fun getWalletBalance(address: String) {
        getWalletBalanceUseCase.execute(address).onEach {
            when (it) {
                is Resource.Loading -> {
                    walletBalanceLiveData.value = Resource.Loading()
                }
                is Resource.Error -> {
                    walletBalanceLiveData.value = Resource.Error(it.message!!)
                }
                is Resource.Success -> {
                    walletBalanceLiveData.value = Resource.Success(it.data.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadContract(privateKey: String) {
        loadContractUseCase.execute(privateKey).onEach {
            when (it) {
                is Resource.Loading -> {
                    loadContractLiveData.value = Resource.Loading()
                }
                is Resource.Error -> {
                    loadContractLiveData.value = Resource.Error(it.message!!)
                }
                is Resource.Success -> {
                    loadContractLiveData.value = Resource.Success(it.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun takeGuess(guess: Boolean) {
        takeGuessUseCase.execute(guess).onEach {
            when (it) {
                is Resource.Loading -> {
                    takeGuessLiveData.value = Resource.Loading()
                }
                is Resource.Error -> {
                    takeGuessLiveData.value = Resource.Error(it.message!!)
                }
                is Resource.Success -> {
                    takeGuessLiveData.value = Resource.Success(it.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

}