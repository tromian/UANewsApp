package com.tromian.uanewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tromian.uanewsapp.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article : Article) : Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}