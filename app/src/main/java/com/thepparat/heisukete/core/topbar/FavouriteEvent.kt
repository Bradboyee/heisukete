package com.thepparat.heisukete.core.topbar

import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem

sealed class FavouriteEvent {
    object OnFavouriteClick : FavouriteEvent()
    data class SetKanjiQuizItem(val kanjiQuizItem: KanjiQuizItem) : FavouriteEvent()
    data class CheckKanjiIsSelected(val character: String) : FavouriteEvent()
}