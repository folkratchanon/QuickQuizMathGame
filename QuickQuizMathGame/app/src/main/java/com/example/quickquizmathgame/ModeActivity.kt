package com.example.quickquizmathgame


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ModeActivity : AppCompatActivity() {
    var btn_mode_back : Button? = null
    var btn_easy_mode : Button? = null
    var btn_medium_mode : Button? = null
    var btn_hard_mode : Button? = null
    var btn_difficul_mode : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode)
        init()
        btn_mode_back!!.setOnClickListener {
            var intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
        }
        btn_easy_mode!!.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn_medium_mode!!.setOnClickListener {
            var intent = Intent(this, Medium::class.java)
            startActivity(intent)
        }
        btn_hard_mode!!.setOnClickListener {
            var intent = Intent(this, Hard::class.java)
            startActivity(intent)
        }
        btn_difficul_mode!!.setOnClickListener {
            var intent = Intent(this, Difficult::class.java)
            startActivity(intent)
        }



    }
    fun init(){
        btn_mode_back = findViewById(R.id.btn_mode_back)
        btn_easy_mode = findViewById(R.id.btn_easy_mode)
        btn_medium_mode = findViewById(R.id.btn_medium_mode)
        btn_hard_mode = findViewById(R.id.btn_hard_mode)
        btn_difficul_mode = findViewById(R.id.btn_difficult_mode)
    }
}