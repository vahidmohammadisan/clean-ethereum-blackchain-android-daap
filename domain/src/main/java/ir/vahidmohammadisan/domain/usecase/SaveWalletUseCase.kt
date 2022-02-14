package ir.vahidmohammadisan.domain.usecase

import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.model.Wallet
import ir.vahidmohammadisan.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveWalletUseCase @Inject constructor(private val repository: Repository) :
    UseCase<Wallet, @JvmSuppressWildcards Flow<Resource<Any>>> {
    override fun execute(wallet: Wallet): Flow<Resource<Any>> {
        return repository.saveWallet(wallet)
    }
}