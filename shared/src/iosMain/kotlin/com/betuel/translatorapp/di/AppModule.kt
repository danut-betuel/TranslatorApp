package com.betuel.translatorapp.di

import com.betuel.translatorapp.database.TranslateDatabase
import com.betuel.translatorapp.translate.data.history.SqlDelightHistoryDataSource
import com.betuel.translatorapp.translate.data.local.DatabaseDriverFactory
import com.betuel.translatorapp.translate.data.remote.HttpClientFactory
import com.betuel.translatorapp.translate.data.translate.KtorTranslateClient
import com.betuel.translatorapp.translate.domain.Translate
import com.betuel.translatorapp.translate.domain.history.HistoryDataSource
import com.betuel.translatorapp.translate.domain.translate.TranslateClient

class AppModule {

    val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            db = TranslateDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    private val translateClient: TranslateClient by lazy {
        KtorTranslateClient(
            HttpClientFactory().create()
        )
    }

    val translateUseCase: Translate by lazy {
        Translate(translateClient, historyDataSource)
    }
}