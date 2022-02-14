package ir.vahidmohammadisan.domain.usecase

import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.model.Wallet
import ir.vahidmohammadisan.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWalletUseCase @Inject constructor(private val repository: Repository) :
    UseCase<Unit, @JvmSuppressWildcards Flow<Resource<List<Wallet>>>> {
    override fun execute(params: Unit): Flow<Resource<List<Wallet>>> {
        return repository.getWallet()
    }
}