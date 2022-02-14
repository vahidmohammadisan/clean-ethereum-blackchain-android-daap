package ir.vahidmohammadisan.domain.usecase

import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWalletBalanceUseCase @Inject constructor(private val repository: Repository) :
    UseCase<String, @JvmSuppressWildcards Flow<Resource<String>>> {
    override fun execute(address: String): Flow<Resource<String>> {
        return repository.getWalletBalance(address)
    }
}