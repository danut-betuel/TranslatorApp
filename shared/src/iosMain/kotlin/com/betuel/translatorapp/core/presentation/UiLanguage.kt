package com.betuel.translatorapp.core.presentation

import com.betuel.translatorapp.core.domain.language.Language

actual class UiLanguage(
    actual val language: Language,
    val imageName: String,
) {
    actual companion object {
        actual fun byCode(langCode: String): UiLanguage {
            return allLanguages.find { it.language.langCode == langCode }
                ?: throw IllegalArgumentException("Invalid or unsupported language code")
        }

        actual val allLanguages: List<UiLanguage>
            get() = Language.entries.map { language ->
                UiLanguage(
                    language = language,
                    imageName = language.langName.lowercase()
                )
            }.sortedBy { it.language.langName }
    }
}