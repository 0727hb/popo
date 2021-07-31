package com.anroid.bottommenu

 class Person {

     var alias:String?=null
     var title:String?=null
     var type:String?=null

     constructor(){}

     constructor(alias: Int, title:String, type:String){
         this.alias= alias.toString()
         this.title=title
         this.type=type
     }

 }