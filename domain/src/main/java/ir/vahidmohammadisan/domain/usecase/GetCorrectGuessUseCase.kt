package ir.vahidmohammadisan.domain.usecase

import ir.vahidmohammadisan.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCorrectGuessUseCase @Inject constructor(private val repository: Repository) :
    UseCase<Unit, @JvmSuppressWildcards Flow<Int>> {
    override fun execute(params: Unit): Flow<Int> {
        return repository.getCorrectGuess()
    }
}