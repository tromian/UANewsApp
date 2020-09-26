package com.tromian.uanewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tromian.uanewsapp.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao() : ArticleDao

    companion object{

        @Volatile
        private var instanse : ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instanse ?: synchronized(LOCK){
            instanse ?: createDatabase(context).also { instanse = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()


    }

}