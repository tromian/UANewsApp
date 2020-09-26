package com.tromian.uanewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tromian.uanewsapp.R
import com.tromian.uanewsapp.ui.NewsActivity
import com.tromian.uanewsapp.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            if (article.url != null){
                loadUrl(article.url)
            }
        }

        fab.setOnClickListener {

            if (article.url != null){
                viewModel.saveArticle(article)
                Snackbar.make(view, "Article saved", Snackbar.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this.context,"Can't save this article :( ", Toast.LENGTH_SHORT).show()
            }

        }

    }
}