package com.test.snake.firebasekotlin_service

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 서비스를 실행시킨다.
        var intent = Intent(this, MainService::class.java)
        startService(intent)
    }
}
