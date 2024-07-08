package com.singlepoint.androidkotlin.designpattrens


//Adapter
//SIngleton
//Stratergy
//Facade


/***
Builder Design pattern:

1. It is a creational design pattern.
2. Help to construct a complex object step by step
3. When object creation requires various configuration or involves multiple steps

Note:
Data class parameter can be created using both val and var
although data class is designed for holding immutable data unless you have a strong reason to make property as mutable.

***/


data class Car(val make: String , val model: String, val year :Int?, val engineType : String?)

class CarBuilder{
    private var make : String =""
    private var model : String =""
    private var year : Int? = null
    private var engineType : String? = null

    fun make(make: String) = apply { this.make = make }
    fun model(model: String) = apply { this.model = model }
    fun year(year: Int) = apply { this.year = year }
    fun engineType(engineType: String)= apply { this.engineType = engineType }

    fun build() = Car(make , model,year,engineType)
}

fun main(){
    val bmwCar =  CarBuilder().make("BMW").model("I10").year(2022).engineType("Hybrid").build()
    val benzeCar = CarBuilder().make("G Wagon").model("SUV").year(2024).build()

    println(bmwCar.toString())
    println(benzeCar.toString())
}