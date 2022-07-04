package ir.vahidmohammadisan.tossco.ui.toss

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.vahidmohammadisan.common.ANIMATION
import ir.vahidmohammadisan.common.vo.Resource
import ir.vahidmohammadisan.domain.model.Wallet
import ir.vahidmohammadisan.tossco.R
import ir.vahidmohammadisan.tossco.databinding.TossFragmentBinding
import ir.vahidmohammadisan.tossco.ui.wallet.WalletViewModel
import timber.log.Timber


@AndroidEntryPoint
class TosscoFragment : Fragment() {

    lateinit var binding: TossFragmentBinding

    private val tosscoViewModel by viewModels<TosscoViewModel>()
    private val walletViewModel by viewModels<WalletViewModel>()

    private lateinit var address: String
    private lateinit var selectedWallet: Wallet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TossFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        walletViewModel.getWallet()
        tosscoViewModel.getCorrectGuess()
        walletViewModel.getWalletLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Timber.tag("get wallet loading")
                }
                is Resource.Error -> {
                    Timber.tag("get wallet error ${it.message}")
                }
                is Resource.Success -> {
                    it.data?.let { Items ->
                        address = Items[0].address
                        selectedWallet = Items[0]
                    }
                    tosscoViewModel.loadContract(privateKey = selectedWallet.privateKey)
                }
            }
        }

        tosscoViewModel.loadContractLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Timber.tag("load contract Loading")
                }
                is Resource.Error -> {
                    Timber.tag("load contract error ${it.message}")
                }
                is Resource.Success -> {
                    it.data?.let { it ->
                        if (it) {
                            Timber.tag("contract loaded")
                            tosscoViewModel.getWalletBalance(address)
                        } else
                            Timber.tag("failed contract loding")
                    }
                }
            }
        })

        tosscoViewModel.walletBalanceLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Timber.tag("wallet balance loading")
                }
                is Resource.Error -> {
                    Timber.tag("wallet balance error ${it.message}")
                }
                is Resource.Success -> {
                    it.data?.let { it ->
                        Log.w("wallet balance", it.toString())
                        Timber.tag("wallet balance $it")
                    }
                }
            }
        })

        tosscoViewModel.takeGuessLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Timber.tag("take guess loading")
                }
                is Resource.Error -> {
                    Timber.tag("take guess error ${it.message}")
                }
                is Resource.Success -> {
                    it.data?.let { it ->
                        if (it)
                            tosscoViewModel.saveCorrectGuess()
                        Log.w("take guess Result", it.toString())
                        Timber.tag("take guess Result $it")
                    }
                }
            }
        })

        tosscoViewModel.saveCorrectGuessLiveData.observe(viewLifecycleOwner) {
            binding.correctGuess.text = it.toString()

            if (it == 10) {
                binding.paper.visibility = View.VISIBLE
                binding.animationView.visibility = View.GONE
                binding.wallet.visibility = View.VISIBLE
                Handler().postDelayed({
                    tosscoViewModel.resetCorrectGuess()
                }, 10000)
            }
        }

        tosscoViewModel.getCorrectGuessLiveData.observe(viewLifecycleOwner) {
            binding.correctGuess.text = it.toString()
        }

        tosscoViewModel.resetGuessLiveData.observe(viewLifecycleOwner) {
            binding.paper.visibility = View.GONE
            binding.animationView.visibility = View.VISIBLE
            binding.wallet.visibility = View.GONE
            binding.correctGuess.text = "0"
        }

        binding.animationView.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.tosscoFragment)
                findNavController().navigate(R.id.action_tosscoFragment_to_guessDialogFragment)
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(ANIMATION)
            ?.observe(viewLifecycleOwner) {
                binding.animationView.playAnimation()
                tosscoViewModel.takeGuess(it)
            }

    }

}