package com.betuel.translatorapp.core.presentation

import com.betuel.translatorapp.core.domain.language.Language

expect class UiLanguage {
    val language: Language
    companion object {
        fun byCode(langCode : String) : UiLanguage
        val allLanguages: List<UiLanguage>
    }
}