package com.example.sipcalculator.ui.fragments

import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sipcalculator.R
import com.example.sipcalculator.model.InputDataModel
import com.example.sipcalculator.ui.MainActivity
import com.example.sipcalculator.ui.composables.SipResultComposable
import com.example.sipcalculator.viewmodels.SipResultViewModel

class SipResultFragment : Fragment() {

    companion object {
        private const val ARG_INPUTDATA = "INPUTDATA"

        @JvmStatic
        fun newInstance(inputData: InputDataModel) =
            SipResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_INPUTDATA, inputData)
                }
            }
    }

    lateinit var viewModel: SipResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(SipResultViewModel::class.java)
        arguments?.let {
            viewModel.initList(it.getParcelable(ARG_INPUTDATA)!!)
        }

        return ComposeView(requireContext()).apply {
            setContent {
                Column {

                    TopAppBar(
                        title = { Text("SIP Projected Returns") },
                        elevation = 16.dp,
                        navigationIcon = {
                            IconButton(onClick = {
                                activity?.supportFragmentManager?.popBackStack()
                            }) {
                                Icon(imageVector = Icons.Filled.ArrowBack, "Back")
                            }
                        }
                    )

                    SipResultComposable(viewModel.list.value)
                }
            }
        }
    }

}

@Preview
@Composable
fun previewTopBar() {
    TopAppBar(
        title = { Text("SIP Projected Returns") },
        elevation = 16.dp,
        navigationIcon = {
            IconButton(onClick = {

            }){
                Icon(imageVector = Icons.Filled.ArrowBack, "Back")
            }
        }
    )
}