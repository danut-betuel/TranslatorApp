package com.betuel.translatorapp.translate.data.remote

import com.betuel.translatorapp.core.domain.language.Language
import com.betuel.translatorapp.translate.domain.translate.TranslateClient

class FakeTranslateClient : TranslateClient {

    var translatedText = "test translation"

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        return translatedText
    }
}