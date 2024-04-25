package com.betuel.translatorapp.translate.domain

import com.betuel.translatorapp.core.domain.language.Language
import com.betuel.translatorapp.core.domain.util.Resource
import com.betuel.translatorapp.translate.domain.history.HistoryDataSource
import com.betuel.translatorapp.translate.domain.history.HistoryItem
import com.betuel.translatorapp.translate.domain.translate.TranslateClient
import com.betuel.translatorapp.translate.domain.translate.TranslateException

class Translate(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {
    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Resource<String> {
        return try {
            val translatedText = client.translate(fromLanguage, fromText, toLanguage)

            historyDataSource.insertHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translatedText
                )
            )

            Resource.Success(translatedText)
        } catch (e: TranslateException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}