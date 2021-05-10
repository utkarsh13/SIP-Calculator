package com.example.sipcalculator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sipcalculator.R
import com.example.sipcalculator.model.InputDataModel
import com.example.sipcalculator.model.TopupType
import com.example.sipcalculator.theme.SipCalculatorTheme
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
                SipCalculatorTheme(darkTheme = false) {
                    Column {

                        val isLight = remember { mutableStateOf(false) }
                        TopAppBar(
                            title = { Text("SIP Calculator") },
                            elevation = 16.dp,
                            actions = {
                                IconButton(onClick = {
                                    isLight.value = !isLight.value
                                }) {
                                    Icon(
                                        if (isLight.value) Icons.Filled.Brightness5 else Icons.Filled.Brightness4,
                                        "Change Theme",
                                        tint = Color.White
                                    )
                                }
                                IconButton(onClick = {
                                    viewModel.resetAllFields()
                                }) {
                                    Icon(Icons.Filled.Autorenew, "Reset", tint = Color.White)
                                }
                            }
                        )

                        SipInputComposable(viewModel) {
                            openResultFragment()
                        }
                    }
                }
            }
        }
    }

    private fun openResultFragment() {
        val resultsFragment = SipResultFragment.newInstance(
            InputDataModel(
                viewModel.totalYears.value.toInt(),
                viewModel.monthlyAmount.value.toDouble(),
                viewModel.expectedAnnualReturn.value.toDouble(),
                if (viewModel.isLumpsumSelected.value) viewModel.lumpsumAmount.value.toDouble() else 0.0,
                if (viewModel.isInflationSelected.value) viewModel.inflationRate.value.toDouble() else 0.0,
                if (viewModel.isTopupSelected.value && viewModel.topupType.value == TopupType.AMOUNT) viewModel.topupValue.value.toDouble() else 0.0,
                if (viewModel.isTopupSelected.value && viewModel.topupType.value == TopupType.PERCENTAGE) viewModel.topupValue.value.toDouble() else 0.0
            )

        )
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, resultsFragment)
            .addToBackStack(null)
            .commit()
    }

}