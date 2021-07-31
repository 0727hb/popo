package com.anroid.bottommenu

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper2(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABADE_VER) {

    companion object{
        private val DATABADE_VER=1
        private val DATABASE_NAME="SAMPLEKOTL.db"

        //테이블
        private val TABLE_NAME="Person"
        private val COOL_ALIAS="Alias"
        private val COOL_TITLE="Title"
        private val COOL_TYPE="Type"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COOL_ALIAS TEXT PRIMARY KEY, $COOL_TITLE TEXT,$COOL_TYPE TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD

    val allPerson:List<Person>
    get(){
        val istPersons = ArrayList<Person>()
        val selectQueryHandler = "SELECT * FROM $TABLE_NAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQueryHandler,null)
        if(cursor.moveToFirst())
        {
            do{
                val person = Person()
                person.alias = cursor.getString(cursor.getColumnIndex(COOL_ALIAS))
                person.title = cursor.getString(cursor.getColumnIndex(COOL_TITLE))
                person.type = cursor.getString(cursor.getColumnIndex(COOL_TYPE))

                istPersons.add(person)
            }while (cursor.moveToNext())
        }
        db.close()
        return istPersons
    }

    fun addPerson(person: Person)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COOL_ALIAS,person.alias)
        values.put(COOL_TITLE,person.title)
        values.put(COOL_TYPE,person.type)

        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    fun updatePerson(person: Person):Int
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COOL_ALIAS,person.alias)
        values.put(COOL_TITLE,person.title)
        values.put(COOL_TYPE,person.type)

        return db.update(TABLE_NAME,values,"$COOL_ALIAS=?", arrayOf(person.alias.toString()))
    }

    fun deletePerson(person: Person)
    {
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COOL_ALIAS=?", arrayOf(person.alias.toString()))
        db.close()
    }



}