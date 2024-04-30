package com.betuel.translatorapp.android.voice_to_text.di

import android.content.Context
import com.betuel.translatorapp.android.voice_to_text.data.AndroidVoiceToTextParser
import com.betuel.translatorapp.voice_to_text.domain.VoiceToTextParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object VoiceToTextModule {

    @Provides
    @ViewModelScoped
    fun provideVoiceToTextParser(context: Context): VoiceToTextParser {
        return AndroidVoiceToTextParser(context)
    }
}