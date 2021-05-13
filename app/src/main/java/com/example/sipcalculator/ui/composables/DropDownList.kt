package com.example.sipcalculator.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@SuppressLint("DefaultLocale")
@Composable
fun <T: Enum<T>>DropDownList(
    isDropdownOpen: MutableState<Boolean> = mutableStateOf(false),
    list: List<Enum<T>>,
    onClick: (Int) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.wrapContentSize(),
        expanded = isDropdownOpen.value,
        onDismissRequest = { isDropdownOpen.value = false },
    ) {
        list.forEachIndexed { index, item ->
            DropdownMenuItem(
                modifier = Modifier.wrapContentSize(),
                onClick = {
                    isDropdownOpen.value = false
                    onClick(index)
                }
            ) {
                Text(item.name.toLowerCase().capitalize(), modifier = Modifier.wrapContentSize(), textAlign = TextAlign.Start)
            }
        }
    }
}