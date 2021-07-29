package com.anroid.bottommenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RankActivity : AppCompatActivity() {
    private var Flag : String = "random"
    private var category: String = "* RANKING"

    lateinit var textView_Rank : TextView
    lateinit var btn_Back : FloatingActionButton
    lateinit var btn_popularity : Button
    lateinit var btn_rating : Button
    lateinit var btn_random : Button
    lateinit var listView : ListView
    lateinit var myHelper : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        myHelper = DBHelper(this, "CONTENT", null, 1)

        textView_Rank = findViewById<TextView>(R.id.textView_Rank)
        btn_Back = findViewById<FloatingActionButton>(R.id.btn_back)
        btn_popularity = findViewById(R.id.btn_popularity)
        btn_rating = findViewById(R.id.btn_rating)
        btn_random = findViewById(R.id.btn_random)
        listView = findViewById(R.id.listView)

        btn_Back.setOnClickListener{
            onBackPressed()
        }

        btn_popularity.setOnClickListener {
            Flag = "popularity"
        }
        btn_rating.setOnClickListener {
            Flag = "rating"
        }
        btn_random.setOnClickListener {
            Flag = "random"
        }

        if(intent.hasExtra("RankCategory")) {
            category = intent.getStringExtra("RankCategory").toString()
            textView_Rank.text = category

            if(category == "MOVIE RANK"){

                var movieList = myHelper.MovieRank(Flag)
                val Adapter = rankingAdapter(this, movieList)
                listView.adapter = Adapter

            }else if(category == "BOOK RANK"){

                var bookList = myHelper.BookRank(Flag)
                val Adapter = rankingAdapter(this, bookList)
                listView.adapter = Adapter

            }else if(category == "MUSIC RANK"){

                var musicList = myHelper.MusicRank(Flag)
                val Adapter = rankingAdapter(this, musicList)
                listView.adapter = Adapter

            }
        }
    }
}