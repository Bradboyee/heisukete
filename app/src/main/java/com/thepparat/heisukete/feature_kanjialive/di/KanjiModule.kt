package com.thepparat.heisukete.feature_kanjialive.di

import com.thepparat.heisukete.BuildConfig
import com.thepparat.heisukete.core.database.KanjiDatabase
import com.thepparat.heisukete.feature_kanjialive.data.dao.KanjiDetailDao
import com.thepparat.heisukete.feature_kanjialive.data.dao.KanjiGradeDao
import com.thepparat.heisukete.feature_kanjialive.data.dao.KanjiItemDao
import com.thepparat.heisukete.feature_kanjialive.data.remote.KanjiAliveApi
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.KanjiQuizItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KanjiModule {
    @Provides
    @Singleton
    fun provideKanjiAliveApi(): KanjiAliveApi {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            .create(KanjiAliveApi::class.java)
    }


    @Provides
    @Singleton
    fun provideKanjiByGradeDao(db: KanjiDatabase): KanjiGradeDao {
        return db.kanjiGradeDao()
    }

    @Provides
    @Singleton
    fun provideKanjiDetailDao(db: KanjiDatabase): KanjiDetailDao {
        return db.kanjiDetailDao()
    }

    @Provides
    @Singleton
    fun provideKanjiItemDao(db: KanjiDatabase): KanjiItemDao {
        return db.kanjiItemDao()
    }

    @Provides
    @Singleton
    fun providesKanjiQuizItemDao(db: KanjiDatabase): KanjiQuizItemDao {
        return db.kanjiQuizItemDao()
    }
}