package com.thepparat.heisukete.core.di

import android.content.Context
import androidx.room.Room
import com.thepparat.heisukete.core.database.KanjiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideKanjiAliveDatabase(@ApplicationContext context: Context): KanjiDatabase {
        return Room.databaseBuilder(context, KanjiDatabase::class.java, "kanji_database").build()
    }
}