package com.betuel.translatorapp.di

import com.betuel.translatorapp.database.TranslateDatabase
import com.betuel.translatorapp.translate.data.history.SqlDelightHistoryDataSource
import com.betuel.translatorapp.translate.data.local.DatabaseDriverFactory
import com.betuel.translatorapp.translate.data.remote.HttpClientFactory
import com.betuel.translatorapp.translate.data.translate.KtorTranslateClient
import com.betuel.translatorapp.translate.domain.Translate
import com.betuel.translatorapp.translate.domain.history.HistoryDataSource
import com.betuel.translatorapp.translate.domain.translate.TranslateClient
import com.betuel.translatorapp.voice_to_text.domain.VoiceToTextParser

interface AppModule {
    val historyDataSource: HistoryDataSource
    val translateClient: TranslateClient
    val translateUseCase: Translate
    val voiceParser: VoiceToTextParser
}

class AppModuleImpl(override val voiceParser: VoiceToTextParser) : AppModule {

    override val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            db = TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    override val translateClient: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    override val translateUseCase: Translate by lazy {
        Translate(translateClient, historyDataSource)
    }
}