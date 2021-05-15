package com.example.sipcalculator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sipcalculator.R
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
                        viewModel.totalYears.value.toInt(),
                        viewModel.monthlyAmount.value.toInt(),
                        viewModel.expectedAnnualReturn.value.toDouble()
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