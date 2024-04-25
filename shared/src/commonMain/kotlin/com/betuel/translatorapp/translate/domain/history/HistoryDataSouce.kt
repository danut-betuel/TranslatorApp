package com.betuel.translatorapp.translate.domain.history

import com.betuel.translatorapp.core.domain.util.CommonFlow

interface HistoryDataSource {
    fun getHistory() : CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
}