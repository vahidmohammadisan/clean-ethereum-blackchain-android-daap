package ir.vahidmohammadisan.tossco.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.model.Wallet
import ir.vahidmohammadisan.domain.usecase.*
import kotlinx.coroutines.flow.Flow

@Module
@InstallIn(ViewModelComponent::class)
abstract class WalletModule {

    @Binds
    @ViewModelScoped
    abstract fun bindCreateWalletUseCase(
        createWalletUseCase: CreateWalletUseCase
    ): UseCase<String, Flow<Resource<Any>>>

    @Binds
    @ViewModelScoped
    abstract fun bindImportWalletUseCase(
        importWalletUseCase: ImportWalletUseCase
    ): UseCase<String, Flow<Resource<Any>>>

    @Binds
    @ViewModelScoped
    abstract fun bindSaveWalletUseCase(
        saveWalletUseCase: SaveWalletUseCase
    ): UseCase<Wallet, Flow<Resource<Any>>>

    @Binds
    @ViewModelScoped
    abstract fun bindGetWalletUseCase(
        getWalletUseCase: GetWalletUseCase
    ): UseCase<Unit, Flow<Resource<List<Wallet>>>>

    @Binds
    @ViewModelScoped
    abstract fun bindGetWalletBalanceUseCase(
        getWalletBalanceUseCase: GetWalletBalanceUseCase
    ): UseCase<String, Flow<Resource<String>>>

    @Binds
    @ViewModelScoped
    abstract fun bindLoadContractUseCase(
        loadContractUseCase: LoadContractUseCase
    ): UseCase<String, Flow<Resource<Boolean>>>

    @Binds
    @ViewModelScoped
    abstract fun bindTakeGuessUseCase(
        takeGuessUseCase: takeGuessUseCase
    ): UseCase<Boolean, Flow<Resource<Boolean>>>

}