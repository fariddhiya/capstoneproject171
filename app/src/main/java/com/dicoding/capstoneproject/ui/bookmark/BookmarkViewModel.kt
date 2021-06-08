package com.dicoding.capstoneproject.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dicoding.capstoneproject.utils.Resource
import com.dicoding.capstoneproject.fav.Bookmark
import com.dicoding.capstoneproject.repo.repointer.BookmarkRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class BookmarkViewModel @Inject constructor(
    @Named("bookmarkRepo")
private val bookmarkRepository: BookmarkRepository) : ViewModel() {

    val isFav = MutableLiveData<Bookmark>()

    private val _insertBookmarkStatus = MutableLiveData<Resource<Bookmark>>()
    val insertBookmarkInsert: LiveData<Resource<Bookmark>> = _insertBookmarkStatus

    private val _deleteBookmarkStatus = MutableLiveData<Resource<Bookmark>>()
    val deleteBookmarkStatus: LiveData<Resource<Bookmark>> = _deleteBookmarkStatus

    fun getBookmarks() : LiveData<PagedList<Bookmark>> = bookmarkRepository.getAllData()

    fun isBookmark(idBook: Int) = viewModelScope.launch {
        isFav.postValue(bookmarkRepository.isBookmark(idBook))
    }

    fun deleteFromBookmark(bookmark: Bookmark) = viewModelScope.launch {
        bookmarkRepository.deleteBookmark(bookmark)
        _deleteBookmarkStatus.postValue(Resource.Success(bookmark, "Report removed from bookmark"))
    }

    fun insertToBookmark(bookmark: Bookmark) = viewModelScope.launch {
        bookmarkRepository.insertBookmark(bookmark)
        _insertBookmarkStatus.postValue(Resource.Success(bookmark, "Report added to Bookmark"))
    }


}