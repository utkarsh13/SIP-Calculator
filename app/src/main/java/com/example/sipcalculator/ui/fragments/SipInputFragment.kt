package com.example.sipcalculator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sipcalculator.R
import com.example.sipcalculator.model.InputDataModel
import com.example.sipcalculator.ui.MainActivity
import com.example.sipcalculator.ui.composables.SipInputComposable
import com.example.sipcalculator.viewmodels.SipInputViewModel

class SipInputFragment : Fragment() {

    lateinit var viewModel: SipInputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as? MainActivity)?.supportActionBar?.title = "SIP Calculator"

        viewModel = ViewModelProvider(requireActivity()).get(SipInputViewModel::class.java)
        return ComposeView(requireContext()).apply {
            setContent {
                SipInputComposable(viewModel) {
                    val resultsFragment = SipResultFragment.newInstance(
                        InputDataModel(
                            viewModel.totalYears.value.toInt(),
                            viewModel.monthlyAmount.value.toInt(),
                            viewModel.expectedAnnualReturn.value.toDouble(),
                            if (viewModel.isLumpsumChecked.value) viewModel.lumpsumAmount.value.toInt() else 0,
                            if (viewModel.isInflationChecked.value) viewModel.inflationRate.value.toDouble() else 0.0
                        )

                    )
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, resultsFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

}