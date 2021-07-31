package com.anroid.bottommenu

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import com.anroid.bottommenu.Person
import com.anroid.bottommenu.MainActivity2

class ListPersonAdapter(internal val activity: Activity,
    internal val istPerson: List<Person>,
    internal val edt_alias:EditText,
    internal val edt_title:EditText,
    internal val edt_type:EditText):BaseAdapter(){

        internal var inflater:LayoutInflater

        init{
            inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View
        rowView = inflater.inflate(R.layout.review_contents, null)

        rowView.textContent.text = istPerson[position].title.toString()
        rowView.textgenre.text = istPerson[position].type.toString()

        //이벤트
        rowView.setOnClickListener {
            edt_title.setText(rowView.textContent.text.toString())
            edt_type.setText(rowView.textgenre.text.toString())
        }
        return rowView

    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        return istPerson[position]
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }
    }

