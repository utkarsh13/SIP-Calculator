package com.example.sipcalculator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sipcalculator.ui.MainActivity
import com.example.sipcalculator.ui.composables.SipResultComposable
import com.example.sipcalculator.viewmodels.SipResultViewModel

class SipResultFragment : Fragment() {

    companion object {
        private const val ARG_YEARS = "years"
        private const val ARG_MONTHLY_AMOUNT = "monthly_amount"
        private const val ARG_EXPECTED_RETURN = "expected_return"
        private const val ARG_LUMPSUM = "lumpsum"

        @JvmStatic
        fun newInstance(years: Int, monthlyAmount: Int, expectedReturn: Double, lumpsum: Int?) =
            SipResultFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_YEARS, years)
                    putInt(ARG_MONTHLY_AMOUNT, monthlyAmount)
                    putDouble(ARG_EXPECTED_RETURN, expectedReturn)
                    lumpsum?.let { putInt(ARG_LUMPSUM, it) }
                }
            }
    }

    lateinit var viewModel: SipResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as? MainActivity)?.supportActionBar?.title = "SIP Projected Returns"

        viewModel = ViewModelProvider(requireActivity()).get(SipResultViewModel::class.java)

        arguments?.let {
            viewModel.initList(
                it.getInt(ARG_YEARS),
                it.getInt(ARG_MONTHLY_AMOUNT),
                it.getDouble(ARG_EXPECTED_RETURN),
                it.getInt(ARG_LUMPSUM)
            )
        }
        return ComposeView(requireContext()).apply {
            setContent {
                SipResultComposable(viewModel.list.value)
            }
        }
    }

}