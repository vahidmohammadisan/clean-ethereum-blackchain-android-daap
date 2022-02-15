package ir.vahidmohammadisan.tossco.ui.toss

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import ir.vahidmohammadisan.common.ANIMATION
import ir.vahidmohammadisan.tossco.R
import ir.vahidmohammadisan.tossco.databinding.GuessDialogFragmentBinding

@AndroidEntryPoint
class GuessDialogFragment : BottomSheetDialogFragment() {

    lateinit var binding: GuessDialogFragmentBinding
    private var GUESS = false

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
            GUESS = true
            handleUI()
        }

        binding.falseButton.setOnClickListener {
            GUESS = false
            handleUI()
        }

    }

    private fun handleUI() {
        if (findNavController().currentDestination?.id == R.id.guessDialogFragment) {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(ANIMATION, GUESS)
        }
        dismiss()
    }
}