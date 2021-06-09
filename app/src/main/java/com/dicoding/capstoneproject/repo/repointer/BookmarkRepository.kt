package com.dicoding.capstoneproject.repo.repointer

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.capstoneproject.fav.Bookmark

interface BookmarkRepository {

    suspend fun insertBookmark(bookmark: Bookmark)
    fun getAllData():LiveData<PagedList<Bookmark>>
    suspend fun isBookmark(idBook: Int): Bookmark
    suspend fun deleteBookmark(bookmark : Bookmark)
}