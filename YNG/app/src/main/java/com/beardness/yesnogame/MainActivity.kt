package com.beardness.yesnogame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView

class MainActivity : AppCompatActivity() {

  var count:Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val text: TextView = findViewById(R.id.text)
    text.text = "Hello Kotlin!"
    val l = object : OnClickListener {
      override fun onClick(v: View?) {
        if (v != null) {
          findViewById<TextView>(v.id)
          text.text = "Clicked $count"
          text.setTextSize(1, 42F)
          count++
        }
      }
    }
    text.setOnClickListener(l)

  }
}