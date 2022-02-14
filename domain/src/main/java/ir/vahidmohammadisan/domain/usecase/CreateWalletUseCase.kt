package ir.vahidmohammadisan.domain.usecase

import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateWalletUseCase @Inject constructor(private val repository: Repository) :
    UseCase<String, @JvmSuppressWildcards Flow<Resource<Any>>> {
    override fun execute(params: String): Flow<Resource<Any>> {
        return repository.createWallet(params)
    }
}