package com.betuel.translatorapp.android.translate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.betuel.translatorapp.core.presentation.UiLanguage


@Composable
fun LanguageDropDownItem(
    language: UiLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        text = {
            Text(text = language.language.langName)
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = language.drawableRes),
                contentDescription = language.language.langName,
                modifier = Modifier.size(40.dp)
            )
        },
        onClick = onClick,
        modifier = modifier
    )
}