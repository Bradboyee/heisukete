package com.thepparat.heisukete.space_repeat_feature.data.entity.data.local

import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem

fun KanjiQuizItemEntity.toKanjiQuizItem(): KanjiQuizItem {
    return KanjiQuizItem(
        grade = grade,
        character = character,
        meaning = meaning
    )
}

fun KanjiQuizItem.toKanjiQuizItemEntity() : KanjiQuizItemEntity{
    return KanjiQuizItemEntity(
        grade = grade,
        character = character,
        meaning = meaning
    )
}