package ir.vahidmohammadisan.tossco.ui.toss

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
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
            tosscoViewModel.animation(true)
            dismiss()
        }

        binding.falseButton.setOnClickListener {
            tosscoViewModel.takeGuess(true)
            tosscoViewModel.animation(true)
            dismiss()
        }

    }
}