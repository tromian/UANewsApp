package com.tromian.uanewsapp.repository

import com.tromian.uanewsapp.api.RetrofitInstance
import com.tromian.uanewsapp.db.ArticleDatabase
import com.tromian.uanewsapp.models.Article

class NewsRepository (
    private val db : ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun insert(article : Article) = db.getArticleDao().insert(article)

    suspend fun delete(article: Article) = db.getArticleDao().delete(article)

    fun gelAlSavedNews() = db.getArticleDao().getAllArticles()


}