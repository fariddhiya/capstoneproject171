package com.dicoding.capstoneproject.di

import android.content.Context
import androidx.room.Room
import com.dicoding.capstoneproject.data.ApiService
import com.dicoding.capstoneproject.data.room.ReportDao
import com.dicoding.capstoneproject.data.room.ReportDatabase
import com.dicoding.capstoneproject.repo.BookmarkRepo
import com.dicoding.capstoneproject.repo.repointer.BookmarkRepository
import com.dicoding.capstoneproject.repo.MainRepository
import com.dicoding.capstoneproject.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constant.BASE_URL

    @Singleton
    @Provides
    fun provideMainRepository(
        api: ApiService
    ) = MainRepository(api)

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ReportDatabase::class.java,
        "reportFavDatabase.db"

    ).build()

    @Singleton
    @Provides
    @Named("reportDao")
    fun provideDao(db: ReportDatabase) = db.getReportDao()

    @Singleton
    @Provides
    @Named("bookmarkRepo")
    fun provideBookmarkRepository(
        @Named("reportDao")
        reportDao : ReportDao
    ) = BookmarkRepo(reportDao) as BookmarkRepository
}