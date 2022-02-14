package ir.vahidmohammadisan.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vahidmohammadisan.common.ContractAddress
import ir.vahidmohammadisan.common.GasLimit
import ir.vahidmohammadisan.common.GasPrice
import ir.vahidmohammadisan.common.InfuraToken
import ir.vahidmohammadisan.data.contract.TossCo
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.infura.InfuraHttpService
import java.math.BigInteger

@Module
@InstallIn(SingletonComponent::class)
object Web3Module {

    @Provides
    fun provideWeb3(): Web3j {
        return Web3j.build(InfuraHttpService("https://rinkeby.infura.io/v3/${InfuraToken}"))
    }

    @Provides
    fun provideTossCo(): TossCo {
        val gasLimit: BigInteger = BigInteger.valueOf(GasLimit.toLong())
        val gasPrice: BigInteger = BigInteger.valueOf(GasPrice.toLong())
        val credentials =
            Credentials.create("105642515519938468974083437117095497201223008449694724482742142839756054013536")

        return TossCo.load(ContractAddress, provideWeb3(), credentials, gasLimit, gasPrice)
    }

}