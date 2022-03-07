package ir.vahidmohammadisan.tossco.ui.wallet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.tossco.R
import ir.vahidmohammadisan.tossco.databinding.WalletFragmentBinding
import org.web3j.crypto.Credentials
import timber.log.Timber

@AndroidEntryPoint
class WalletFragment : Fragment() {

    lateinit var binding: WalletFragmentBinding

    private val viewModel by viewModels<WalletViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WalletFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWallet()
        viewModel.getWalletLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Timber.tag("get wallet Loading")
                }
                is Resource.Error -> {
                    Timber.tag("get wallet error ${it.message}")
                }
                is Resource.Success -> {
                    it.data?.let { Items ->
                        if (Items.isNotEmpty())
                            if (findNavController().currentDestination?.id == R.id.walletFragment)
                                findNavController().navigate(R.id.action_walletFragment_to_tosscoFragment)
                    }
                }
            }
        }

        binding.createWalletBtn.setOnClickListener {
            viewModel.createWallet("")
            viewModel.createWalletLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        Timber.tag("create wallet Loading")
                    }
                    is Resource.Error -> {
                        Timber.tag("create wallet error ${it.message}")
                    }
                    is Resource.Success -> {
                        Timber.tag("address ${it.data?.address}")
                        Log.w("address,", it.data?.address.toString())
                        it.data?.let { Credentials ->
                            Log.w("Address",it.data?.address.toString())
                            Timber.tag("private key ${Credentials.ecKeyPair.privateKey}")
                            Timber.tag("public key ${Credentials.ecKeyPair.publicKey}")
                            saveWallet(
                                credentials = Credentials,
                                address = it.data?.address.toString()
                            )
                        }
                    }
                }
            }
        }

        binding.importWalletBtn.setOnClickListener {
            //galaxy valid wire answer subject bunker win east apology drive inquiry loan
            viewModel.importWallet("")
            viewModel.importWalletLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        Timber.tag("import wallet Loading")
                    }
                    is Resource.Error -> {
                        Timber.tag("import wallet error ${it.message}")
                    }
                    is Resource.Success -> {
                        Timber.tag("address ${it.data?.address}")
                        it.data?.let { Credentials ->
                            Timber.tag("private key ${Credentials.ecKeyPair.privateKey}")
                            Timber.tag("public key ${Credentials.ecKeyPair.privateKey}")
                            saveWallet(credentials = Credentials, it.data?.address.toString())
                        }
                    }
                }
            }
        }

        viewModel.saveWalletLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Timber.tag("save wallet saving")
                }
                is Resource.Error -> {
                    Timber.tag("save wallet error ${it.message}")
                }
                is Resource.Success -> {
                    Timber.tag("wallet saved")
                    navigateToTossco()
                }
            }
        }

    }

    private fun saveWallet(credentials: Credentials, address: String) {
        viewModel.saveWallet("Random_Wallet_Name", credentials, address)
    }

    private fun navigateToTossco() {
        if (findNavController().currentDestination?.id == R.id.walletFragment)
            findNavController().navigate(R.id.action_walletFragment_to_tosscoFragment)
    }

}