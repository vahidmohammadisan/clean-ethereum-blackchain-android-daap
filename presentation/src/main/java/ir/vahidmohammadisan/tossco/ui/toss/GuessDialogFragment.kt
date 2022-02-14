package ir.vahidmohammadisan.tossco.ui.toss

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import ir.vahidmohammadisan.tossco.R
import ir.vahidmohammadisan.tossco.databinding.GuessDialogFragmentBinding

@AndroidEntryPoint
class GuessDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: GuessDialogFragmentBinding

    private val tosscoViewModel by viewModels<TosscoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GuessDialogFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.trueButton.setOnClickListener {
            tosscoViewModel.takeGuess(true)
            handleUI()
        }

        binding.falseButton.setOnClickListener {
            tosscoViewModel.takeGuess(false)
            handleUI()
        }

    }

    private fun handleUI() {
        tosscoViewModel.changeAnimationState(true)
        if (findNavController().currentDestination?.id == R.id.guessDialogFragment)
            findNavController().navigate(R.id.tosscoFragment)
        dismiss()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onResume()
    }
}