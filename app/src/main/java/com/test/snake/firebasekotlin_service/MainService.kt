package com.test.snake.firebasekotlin_service

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.DataSnapshot
import com.firebase.client.ValueEventListener

class MainService : Service() {

    // 1. Firebase 변수를 선언
    private var myFirebaseRef: Firebase? = null

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            // 2. 개발자가 만든 Firebase setting 멤버함수
            setUpFireBase()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun setUpFireBase() {
        initFireBase()
        makeFireBase()
    }

    // 4.
    private fun makeFireBase() {
        myFirebaseRef!!.child("url").setValue("http://vintageappmaker.com")
        myFirebaseRef!!.child("url").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sMessage = snapshot.value as String

                // 한글은되냐
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(sMessage))
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)

            }

            override fun onCancelled(error: FirebaseError) {}
        })
    }

    // 3.
    private fun initFireBase() {
        // Firebase가 이렇게 하라고 한다.
        Firebase.setAndroidContext(this)

        // 박모씨 아들의 파이어베이스 계정입니다.
        //myFirebaseRef = new Firebase("https://glowing-torch-2311.firebaseio.com/");
        myFirebaseRef = Firebase("https://testandroid-d79e4.firebaseio.com/")
    }
}
