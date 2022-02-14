package ir.vahidmohammadisan.domain.usecase

import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadContractUseCase @Inject constructor(private val repository: Repository) :
    UseCase<String, @JvmSuppressWildcards Flow<Resource<Boolean>>> {
    override fun execute(privateKey: String): Flow<Resource<Boolean>> {
        return repository.loadContract(privateKey)
    }
}