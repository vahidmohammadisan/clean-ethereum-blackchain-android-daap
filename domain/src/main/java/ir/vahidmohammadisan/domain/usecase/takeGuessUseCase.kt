package ir.vahidmohammadisan.domain.usecase

import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class takeGuessUseCase @Inject constructor(private val repository: Repository) :
    UseCase<Boolean, @JvmSuppressWildcards Flow<Resource<Boolean>>> {
    override fun execute(guess: Boolean): Flow<Resource<Boolean>> {
        return repository.takeGuess(guess)
    }
}