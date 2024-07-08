package com.singlepoint.androidkotlin.designpattrens

/***
 * It is a behavioural design pattern
 *
 * The Strategy Design Pattern allows you to choose different methods or strategies to perform a task without changing the code.
 * It lets you switch algorithms or behaviors at runtime.
 *
 * Let me explain an example - Imagine you have a program that can perform different kind of text formatting: UpperCase, lowercase, and capitalization each word.
 * You want to be able to switch between these formatting methods easily.
 *
 * **/


interface TextFormat{
    fun format(string: String): String
}

class UpperCaseTextFormat : TextFormat{
    override fun format(string: String): String {
        return string.uppercase()
    }
}

class LowerCaseTextFormat: TextFormat{
    override fun format(string: String): String {
        return string.lowercase()
    }
}

class CapitalizeFormat: TextFormat{
    override fun format(string: String): String {
        return string.split(" ").joinToString(" ") {it.capitalize()}
    }
}

// Apply Strategy here
class TextEditor(private var textFormat: TextFormat){
   fun publish(text : String){
       println(textFormat.format(text))
   }
}

fun main(){
    //Without changing the code - at run time we are changing the text formatting methods
    TextEditor(UpperCaseTextFormat()).publish("I am UpperCase")
    TextEditor(LowerCaseTextFormat()).publish("I am Lowercase")
    TextEditor(CapitalizeFormat()).publish("Please make a capital letter of every 1st character of word ")
}