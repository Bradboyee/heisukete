package com.thepparat.heisukete.space_repeat_feature.data.entity.data.local

import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem

fun KanjiQuizItemEntity.toKanjiQuizItem(): KanjiQuizItem {
    return KanjiQuizItem(
        grade = grade,
        character = character,
        meaning = meaning,
        create_dt = create_dt,
        update_dt = update_dt,
        repeat_level = repeat_level,
        do_date = do_date
    )
}

fun KanjiQuizItem.toKanjiQuizItemEntity(): KanjiQuizItemEntity {
    return KanjiQuizItemEntity(
        grade = grade,
        character = character,
        meaning = meaning,
        create_dt = create_dt,
        update_dt = update_dt,
        repeat_level = repeat_level,
        do_date = do_date
    )
}