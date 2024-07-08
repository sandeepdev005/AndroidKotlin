package com.singlepoint.androidkotlin.designpattrens

/***
 *
 * Facade Design Pattern
 *  - simplified interface to a complex subsystem
 *  - Hides the complexity of the system and provides an easy-to-use interface for the client.
 *
 *  Example
 *   Imagine you have a home entertainment system with various components like TV, Sound System, Games console
 *   This Facade pattern will simplify the operation of Turning On and Off
 * */

class Tv{
    fun turnOn(){
        println("TV is turned on")
    }

    fun turnOff(){
        println("TV is turned off")
    }
}


class SoundSystems{
    fun turnOn(){
        println("SoundSystems is turned on")
    }

    fun turnOff(){
        println("SoundSystems is turned off")
    }
}


class GameConsole{
    fun turnOn(){
        println("GameConsole is turned on")
    }

    fun turnOff(){
        println("GameConsole is turned off")
    }
}

//Create an interface that will be simplified to manage subsystems

class HomeEntertainmentSystem(
    private val tv : Tv,
    private val soundSystems: SoundSystems,
    private val gameConsole: GameConsole
    ){

    fun turnOn(){
        tv.turnOn()
        soundSystems.turnOn()
        gameConsole.turnOn()
    }

    fun turnOff(){
        tv.turnOff()
        soundSystems.turnOff()
        gameConsole.turnOff()
    }

}

fun main(){
    val tv = Tv()
    val soundSystems = SoundSystems()
    val gameConsole = GameConsole()
    val homeEntertainmentSystem  = HomeEntertainmentSystem(tv, soundSystems, gameConsole)
    homeEntertainmentSystem.turnOn()
    homeEntertainmentSystem.turnOff()
}



