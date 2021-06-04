package com.dicoding.capstoneproject.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.capstoneproject.data.room.ReportDao
import com.dicoding.capstoneproject.fav.Bookmark
import com.dicoding.capstoneproject.repo.repointer.BookmarkRepository
import javax.inject.Inject
import javax.inject.Named

class BookmarkRepo @Inject constructor(
    @Named("reportDao") private val reportDao: ReportDao
    ): @Named("bookmarkRepo") BookmarkRepository{


    override suspend fun insertBookmark(bookmark: Bookmark) {
        reportDao.insertBookmark(bookmark)
    }

    override fun getAllData(): LiveData<PagedList<Bookmark>> {
        val config =PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(50)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(reportDao.getAllReport(), config).build()
    }


    override suspend fun isBookmark(idBook: Int): Bookmark = reportDao.getReportById(idBook)

    override suspend fun deleteBookmark(bookmark: Bookmark) {
        reportDao.deleteFavourite(bookmark)
    }
}