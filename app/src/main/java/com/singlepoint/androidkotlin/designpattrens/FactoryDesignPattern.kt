package com.singlepoint.androidkotlin.designpattrens


/***
  Factory :
 1.It is a type of creational pattern
 2.Factory design pattern is used when you want to create an object without specifying the exact class of object to be created.
   instead factory method decide which object to be created based on some parameters
***/



interface Shape{
    fun drawShape()
}

class Circle : Shape{
    override fun drawShape() {
        println("Inside circle ::draw() method")
    }
}

class Triangle : Shape{
    override fun drawShape() {
        println("Inside Triangle :: draw() method")
    }
}

class Oval : Shape{
    override fun drawShape() {
        println("Inside Oval :: draw() method")
    }
}

object ShapeFactory{
    const val CIRCLE = "circle"
    const val TRIANGLE ="TRIANGLE"
    const val OVAL ="oval"
    fun getShape(shapeType : String): Shape? {
        return when(shapeType){
            CIRCLE -> Circle()
            TRIANGLE -> Triangle()
            OVAL -> Oval()
            else -> null
        }
    }
}
fun main(){
    val circle = ShapeFactory.getShape(ShapeFactory.CIRCLE)
    println(circle?.drawShape())

    val triangle = ShapeFactory.getShape(ShapeFactory.TRIANGLE)
    println(triangle?.drawShape())
}