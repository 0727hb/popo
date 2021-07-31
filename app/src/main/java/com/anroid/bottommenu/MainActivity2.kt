package com.anroid.bottommenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.EditText
import com.anroid.bottommenu.DBHelper2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import com.anroid.bottommenu.Person
import kotlinx.android.synthetic.main.fragment_review.*
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.fragment_review.btn_del as btn_del1
import kotlinx.android.synthetic.main.fragment_review.btn_rev as btn_rev1


class MainActivity2 : AppCompatActivity() {


    internal lateinit var db:DBHelper2
    internal var istPersons: List<Person> = ArrayList<Person>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mypage)

        db = DBHelper2(this)

        refreshData()

        //Event
        btn_add.setOnClickListener{
            val person = Person(
            Integer.parseInt(edt_alias.text.toString()) ,edt_type.text.toString(), edt_title.text.toString())
        db.addPerson(person)
        refreshData()
    }
         btn_rev.setOnClickListener {
            val person = Person(
                Integer.parseInt(edt_alias.text.toString()), edt_type.text.toString(), edt_title.text.toString()
            )
            db.updatePerson(person)
            refreshData()
        }

     btn_del.setOnClickListener {
            val person = Person(

                Integer.parseInt(edt_alias.text.toString()),
                edt_type.text.toString(),
                edt_title.text.toString()
            )
            db.deletePerson(person)
            refreshData()
        }
    }


    private fun refreshData(){
        istPersons = db.allPerson
        val adapter= ListPersonAdapter(
            this@MainActivity2, istPersons, edt_alias as EditText, edt_type as EditText, edt_title)
        list.adapter = adapter
        }

    }



