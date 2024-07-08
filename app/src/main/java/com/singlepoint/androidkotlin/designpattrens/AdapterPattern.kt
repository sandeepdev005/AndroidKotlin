package com.singlepoint.androidkotlin.designpattrens

import androidx.compose.ui.text.toLowerCase
import java.io.PrintStream

/****
 * Adaptor Design Pattern
 *  It helps  two incompatible interfaces to work together
 *  It acts as a translator between two entities, allowing them to interact seamlessly without needing to change their existing code.
 * */



//Client expect to use it
interface MediaPlayer{
    fun play(audioType : String, fileName : String)
}

//Has a different interface but you want to use with the mediaPlayer
class AdvanceMediaPlayer{
    fun playMp3(fileName: String){
        println("Playing Mp3 file Name : ${fileName}")
    }

    fun playMp4(fileName: String){
        println("Playing Mp4 file Name : ${fileName}")
    }
}

//Creating an interface or adapter two provide compatiability between two differnet components
class MediaAdapter(private val advanceMediaPlayer: AdvanceMediaPlayer) : MediaPlayer{
    override fun play(audioType: String, fileName: String) {
       when(audioType.lowercase()){
          MP3 -> advanceMediaPlayer.playMp3(fileName)
          MP4 -> advanceMediaPlayer.playMp4(fileName)
           else-> println("Invalid")
       }
    }

    companion object {
        const val MP3 ="mp3"
        const val MP4="mp4"
    }

}


fun main(){
    val advanceMediaPlayer = AdvanceMediaPlayer()
    val mediaAdapter = MediaAdapter(advanceMediaPlayer)
    mediaAdapter.play(MediaAdapter.MP3,"song.mp3")
    mediaAdapter.play(MediaAdapter.MP4,"video.mp4")
    mediaAdapter.play("avi","movie.avi")

}