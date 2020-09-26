package com.tromian.uanewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tromian.uanewsapp.models.Article
import com.tromian.uanewsapp.models.NewsResponse
import com.tromian.uanewsapp.repository.NewsRepository
import com.tromian.uanewsapp.utill.Constants
import com.tromian.uanewsapp.utill.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1

    init {
        getBreakingNews(Constants.COUNTRY_CODE)
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse (response: Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse (response: Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insert(article)
    }

    fun getSavedNews() = newsRepository.gelAlSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.delete(article)
    }

}