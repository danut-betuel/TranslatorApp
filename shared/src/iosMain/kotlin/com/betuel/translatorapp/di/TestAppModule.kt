package com.betuel.translatorapp.di

import com.betuel.translatorapp.testing.FakeHistoryDataSource
import com.betuel.translatorapp.testing.FakeTranslateClient
import com.betuel.translatorapp.testing.FakeVoiceToTextParser
import com.betuel.translatorapp.translate.domain.Translate
import com.betuel.translatorapp.translate.domain.history.HistoryDataSource
import com.betuel.translatorapp.translate.domain.translate.TranslateClient
import com.betuel.translatorapp.voice_to_text.domain.VoiceToTextParser

class TestAppModule : AppModule {
    override val historyDataSource: HistoryDataSource = FakeHistoryDataSource()
    override val translateClient: TranslateClient = FakeTranslateClient()
    override val translateUseCase: Translate = Translate(translateClient, historyDataSource)
    override val voiceParser: VoiceToTextParser = FakeVoiceToTextParser()
}