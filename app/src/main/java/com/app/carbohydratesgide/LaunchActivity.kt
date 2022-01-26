package com.app.carbohydratesgide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.carbohydratesgide.back.Internet
import com.app.carbohydratesgide.back.LinkMaker
import com.app.carbohydratesgide.back.SharedPrefs

class LaunchActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = SharedPrefs(this)

        init()
    }

    private fun init(){
        val read = prefs.read()!!
        when {
            read.startsWith("http") -> opn(read)
            Internet.isOnline(this) -> opnLnk(LinkMaker.link)
            else -> start(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    private fun opnLnk(s: String) {
        prefs.write(s)
        opn(s)
    }

    private fun opn(s: String) {
        val intent = Intent(this, WwActivity::class.java)
        intent.putExtra("url", s)
        start(intent)
    }

    private fun start(intent: Intent){
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {    }
}