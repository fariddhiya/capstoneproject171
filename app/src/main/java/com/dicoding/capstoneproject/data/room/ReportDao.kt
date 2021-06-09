package com.dicoding.capstoneproject.data.room


import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.capstoneproject.fav.Bookmark


@Dao
interface ReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: Bookmark)


    @Query("SELECT * FROM Bookmark")
    fun getAllReport(): DataSource.Factory<Int, Bookmark>

    @Query("SELECT * FROM Bookmark WHERE  idLaporan = :idLaporan")
    suspend fun getReportById(idLaporan: Int)  : Bookmark

    @Delete
    suspend fun deleteFavourite(fav: Bookmark)
}