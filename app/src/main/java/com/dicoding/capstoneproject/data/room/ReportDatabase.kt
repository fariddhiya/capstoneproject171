package com.dicoding.capstoneproject.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.capstoneproject.fav.Bookmark

@Database(
    entities = [Bookmark::class],
    version = 1,
    exportSchema = false
)
abstract class ReportDatabase : RoomDatabase() {
    abstract fun getReportDao(): ReportDao


}